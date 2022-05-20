package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.UpdateBookingFormRequest;
import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.service.IBookingFormService;
import com.linh.hotelbk.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@Api(tags = "API")
public class BookingAPI {

    private final IUserService userService;
    private final IBookingFormService bookingFormService;

    @GetMapping(path = "/api/list-booking-form")
    public List<Map<String, String>> listBookingFormByCurrentUser(){
        //Get curent log-in user
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, String>> result = new ArrayList<>();
        UserEntity currentUser = userService.getCurrentLoginUser();
        List<BookingFormEntity> bookingFormList = bookingFormService.findByUser(currentUser);
        for (BookingFormEntity form : bookingFormList){
            Map<String, String> item = new LinkedHashMap<>();
            item.put("id", form.getId()+"");
            item.put("roomName", form.getRoom().getRoomName());
            item.put("roomImage", form.getRoom().getImagePath());
            item.put("checkInDate", simpleDateFormat.format(form.getCheckInAt()));
            item.put("checkOutDate", simpleDateFormat.format(form.getCheckOutAt()));
            item.put("totalPrice", form.getRoom().getPrice() * form.getBookingDays()+" $");
            result.add(item);
        }
        return result;
    }

    @GetMapping(path = "/api/booking-form/getAll")
    public List<Map<String, String>> getAll(){
        //Get curent log-in user
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, String>> result = new ArrayList<>();
        List<BookingFormEntity> bookingFormList = bookingFormService.findAll();
        for (BookingFormEntity form : bookingFormList){
            Map<String, String> item = new LinkedHashMap<>();
            item.put("id", form.getId()+"");
            item.put("customerName", form.getCustomerName());
            item.put("phoneNumber", form.getPhoneNumber());
            item.put("email", form.getCustomerEmail());
            item.put("roomType", form.getRoom().getRoomType().getRoomTypeName());
            item.put("roomName", form.getRoom().getRoomName());
            item.put("roomPrice", form.getRoom().getCurrency());
            item.put("checkInDate", simpleDateFormat.format(form.getCheckInAt()));
            item.put("checkOutDate", simpleDateFormat.format(form.getCheckOutAt()));
            item.put("status", form.getCheckOutAt().before(new Date()) ? "Paid" : "Unpaid");
            result.add(item);
        }
        return result;
    }

    @PutMapping(path = "/api/update-booking-form")
    public Map<String, String> updateBookingForm(@RequestBody UpdateBookingFormRequest request){
        //Get booking form by Id
        Map<String, String> result = new LinkedHashMap<>();
        BookingFormEntity bookingForm = bookingFormService.findById(request.getBookingId());
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = simpleDateFormat.parse(request.getCheckInDate());
            Date checkOutDate = simpleDateFormat.parse(request.getCheckOutDate());
            bookingForm.setCheckInAt(checkInDate);
            bookingForm.setCheckOutAt(checkOutDate);
            // Reset booking days
            TimeUnit timeUnit = TimeUnit.DAYS;
            long bookingDays = timeUnit.convert(checkOutDate.getTime() - checkInDate.getTime(), TimeUnit.MILLISECONDS);
            bookingForm.setBookingDays((int) bookingDays);
            bookingForm = bookingFormService.save(bookingForm);
            result.put("success", "Cập nhật thành công !");
            return result;
        }catch (Exception e){
            result.put("error", "Không cập nhật thành công !");
            return result;
        }
    }

    @DeleteMapping(path = "/api/delete-booking-form/{id}")
    public Map<String, String> deleteBookingForm(@PathVariable("id") Long id){
        //Get booking form by Id
        Map<String, String> result = new LinkedHashMap<>();
        try {
            bookingFormService.delete(id);
            result.put("success", "Hủy đặt phòng thành công !");
            return result;
        }catch (Exception e){
            result.put("error", "Hủy đặt phòng không thành công !");
            return result;
        }
    }
}
