package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.AddRoomRequest;
import com.linh.hotelbk.dto.request.BookRoomRequest;
import com.linh.hotelbk.dto.request.UpdateRoomRequest;
import com.linh.hotelbk.dto.response.EmailBookingDTO;
import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.RoomTypeEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.service.IBookingFormService;
import com.linh.hotelbk.service.IRoomService;
import com.linh.hotelbk.service.IRoomTypeService;
import com.linh.hotelbk.service.IUserService;
import com.linh.hotelbk.service.impl.SendMailService;
import com.linh.hotelbk.utils.UploadFileUtils;
import com.linh.hotelbk.utils.enums.RoomStatus;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "API")
public class RoomAPI {

    private final IRoomTypeService roomTypeService;
    private final IRoomService roomService;
    private final IUserService userService;
    private final IBookingFormService bookingFormService;
    @Value("${spring.mail.username}")
    private String mailFrom;
    private final SendMailService mailService;

    public RoomAPI(IRoomService roomService, IRoomTypeService roomTypeService, IUserService userService, IBookingFormService bookingFormService, SendMailService mailService){
        this.roomService = roomService;
        this.roomTypeService = roomTypeService;
        this.userService = userService;
        this.bookingFormService = bookingFormService;
        this.mailService = mailService;
    }

    @PostMapping(path = "/api/room/add-room")
    public String addNewRoom(AddRoomRequest request){
        // Lấy available Date từ string
        try {
            // Lấy available Date từ string
            Date availableFrom = new SimpleDateFormat("yyyy-MM-dd").parse(request.getAvailableFrom());

            RoomTypeEntity roomType = roomTypeService.findById(request.getRoomTypeId());
            // Tạo phòng
            RoomEntity room = RoomEntity.builder()
                    .roomName(request.getRoomName())
                    .roomType(roomType)
                    .availableFrom(availableFrom)
                    .floor(request.getFloor())
                    .price(request.getPrice())
                    .status(true)
                    .createAt(new Date())
                    .updateAt(new Date())
                    .build();

            //Lấy đường dẫn của ảnh và lưu ảnh
            String imageName = StringUtils.cleanPath(Objects.requireNonNull(request.getRoomImg().getOriginalFilename()));
            room.setImage(imageName);
            String uploadDir = "./image/room/"+roomType.getRoomTypeName();
            UploadFileUtils.saveFiles(request.getRoomImg(), imageName, uploadDir);
            room = roomService.save(room);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }

    @GetMapping(path = "/api/room/get-all")
    public List<Map<String, String>> getRooms(){
        List<Map<String, String>> result = new ArrayList<>();
        List<RoomEntity> rooms = roomService.findAll();
        for (RoomEntity i : rooms){
            Map<String, String> map = new LinkedHashMap<>();
            map.put("roomId", i.getId()+"");
            map.put("roomName", i.getRoomName());
            map.put("roomImage", i.getImagePath());
            map.put("roomPrice", i.getCurrency());
            map.put("roomStatus", i.getStatus()+"");
            result.add(map);
        }
        return result;
    }

    @PutMapping(path = "/api/room/update-room")
    public String updateRoom(UpdateRoomRequest request){
         try {
             RoomEntity room = roomService.findById(request.getRoomId());
             RoomTypeEntity roomType = roomTypeService.findById(request.getRoomTypeId());
             room.setRoomName(request.getRoomName());
             room.setFloor(request.getFloor());
             room.setPrice(request.getPrice());
             room.setAvailableFrom(new SimpleDateFormat("yyyy-MM-dd").parse(request.getAvailableFrom()));
             room.setRoomType(roomType);
             room.setUpdateAt(new Date());
             String imageName = StringUtils.cleanPath(Objects.requireNonNull(request.getRoomImg().getOriginalFilename()));
             if (!imageName.equals("")) {
                 room.setImage(imageName);
                 String uploadDir = "./image/room/"+roomType.getRoomTypeName();
                 UploadFileUtils.saveFiles(request.getRoomImg(), imageName, uploadDir);
             }
             roomService.save(room);
             return "success";
         }catch (Exception e){
             e.printStackTrace();
             return "error";
         }
    }

    @DeleteMapping(path = "/api/room/delete-room/{id}")
    public String deleteRoom(@PathVariable("id") Long id){
        // Lấy available Date từ string
        try {
            this.roomService.delete(id);
            System.out.println(id);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }

    @PostMapping(path = "/api/room/book-room")
    public Map<String, String> bookRoom(@RequestBody BookRoomRequest request) {
        Map<String, String> result = new LinkedHashMap<>();
        // Lấy available Date từ string
        try {
            // Lấy  checkInDate và checkOutDate từ string
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = simpleDateFormat.parse(request.getCheckInDate());
            Date checkOutDate = simpleDateFormat.parse(request.getCheckOutDate());

            RoomEntity bookedRoom = roomService.findById(request.getRoomId());
            if (checkInDate.before(bookedRoom.getAvailableFrom())){
                result.put("error", "Phòng "+bookedRoom.getRoomName()+" hiện tại đang được đặt");
                return result;
            }
            // Get Current User
            UserEntity customer =  userService.getCurrentLoginUser();
            // Compute Booking Days
            TimeUnit timeUnit = TimeUnit.DAYS;
            long bookingDays = timeUnit.convert(checkOutDate.getTime() - checkInDate.getTime(), TimeUnit.MILLISECONDS);
            // Create Booking Form
            BookingFormEntity bookingForm = BookingFormEntity.builder()
                    .customerName(request.getCustomerName())
                    .phoneNumber(request.getCustomerPhone())
                    .customerEmail(request.getCustomerEmail())
                    .bookingDays((int) bookingDays)
                    .user(customer).checkInAt(checkInDate)
                    .room(bookedRoom)
                    .checkOutAt(checkOutDate)
                    .createAt(new Date())
                    .updateAt(new Date())
                    .build();
            bookingForm = bookingFormService.save(bookingForm);
            bookedRoom.setStatus(false);
            // Reset Available for booked Room
            Calendar c = Calendar.getInstance();
            c.setTime(checkOutDate);
            c.add(Calendar.DATE, 1);
            bookedRoom.setAvailableFrom(c.getTime());
            roomService.save(bookedRoom);

            EmailBookingDTO emailBookingDTO = EmailBookingDTO.builder()
                            .from(mailFrom).to(bookingForm.getCustomerEmail())
                            .subject("MyHotel, Đặt phòng thành công !").build();
            EmailBookingDTO.ContentEmail contentEmail = EmailBookingDTO.ContentEmail
                    .builder()
                    .customerName(bookingForm.getCustomerName())
                    .customerEmail(bookingForm.getCustomerEmail())
                    .customerPhone(bookingForm.getPhoneNumber())
                    .roomName(bookedRoom.getRoomName())
                    .roomTypeName(bookedRoom.getRoomType().getRoomTypeName())
                    .checkInDate(simpleDateFormat.format(checkInDate))
                    .checkOutDate(simpleDateFormat.format(checkOutDate))
                    .roomPrice(bookedRoom.getCurrency())
                    .totalPrice(bookingDays * bookedRoom.getPrice()+" $")
                    .build();
            // Send mail
            mailService.sendBookingMail(emailBookingDTO, contentEmail, bookingForm.getId());

            result.put("success","Bạn đã đặt phòng "+bookingForm.getRoom().getRoomName()+" thành công. Vui lòng kiểm tra email xác nhận !");
            return result;
        } catch (Exception e) {
            result.clear();
            result.put("error", "Đặt phòng không thành công !");
            return result;
        }
    }
}
