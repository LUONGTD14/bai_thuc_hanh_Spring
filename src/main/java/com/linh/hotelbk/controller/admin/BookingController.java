package com.linh.hotelbk.controller.admin;

import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.service.IRoomService;
import com.linh.hotelbk.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "Booking Controller for Admin")
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class BookingController {

    private final IRoomService roomService;
    private final IUserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "/selected-room-booking/{roomId}")
    public ModelAndView getSelectedRoomBookingForm(@PathVariable Long roomId){
        ModelAndView mv = new ModelAndView("admin/room-type-booking-form");
        RoomEntity room = roomService.findById(roomId);
        UserEntity currentUser = userService.getCurrentLoginUser();
        mv.addObject("room", room);
        return mv;
    }
}
