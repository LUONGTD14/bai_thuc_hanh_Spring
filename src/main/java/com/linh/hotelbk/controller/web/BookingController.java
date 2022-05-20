package com.linh.hotelbk.controller.web;

import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.service.IRoomService;
import com.linh.hotelbk.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@Api(tags = "Controller")
public class BookingController {

    private final IRoomService roomService;
    private final IUserService userService;

    @GetMapping(path = "/selected-room-booking/{roomId}")
    public ModelAndView getSelectedRoomBookingForm(@PathVariable Long roomId){
        ModelAndView mv = new ModelAndView("web/room-type-booking-form");
        RoomEntity room = roomService.findById(roomId);
        UserEntity currentUser = userService.getCurrentLoginUser();
        mv.addObject("room", room);
        return mv;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "/show-booking-form")
    public ModelAndView getGeneralBookingForm(){
        ModelAndView mv = new ModelAndView("web/show-booking-form");
        return mv;
    }
}
