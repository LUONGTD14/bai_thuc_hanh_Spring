package com.linh.hotelbk.controller.admin;

import com.linh.hotelbk.dto.Mapper;
import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.service.IRoomService;
import com.linh.hotelbk.service.IRoomTypeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller(value = "Room Controller for Admin")
@RequestMapping(path = "/admin")
@Api(tags = "Controller")
@AllArgsConstructor
public class RoomController {

    private final IRoomTypeService roomTypeService;
    private final IRoomService roomService;

    @GetMapping(path = "/room")
    public ModelAndView getRoomPage(){
        ModelAndView mv = new ModelAndView("admin/phong");
        mv.addObject("roomList", roomService.findAll().stream().map(Mapper::convertToRoomDTO).collect(Collectors.toList()));
        return mv;
    }

    @GetMapping(path = "/add-room")
    public ModelAndView getAddRoomPage(){
        ModelAndView mv = new ModelAndView("admin/addRoom");
        mv.addObject("roomTypeList", roomTypeService.findAll());
        return mv;
    }

    @GetMapping(path = "/update-room/{id}")
    public ModelAndView getUpdateRoomPage(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("admin/updateRoom");
        RoomEntity room = roomService.findById(id);
        mv.addObject("room", room);
        mv.addObject("roomTypeList", roomTypeService.findAll());
        return mv;
    }
}
