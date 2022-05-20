package com.linh.hotelbk.controller.admin;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.service.IBookingFormService;
import com.linh.hotelbk.service.IServiceTypeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/admin")
@Api(tags = "Controller")
public class ServiceController {

    private final IServiceTypeService serviceTypeService;
    private final IBookingFormService bookingFormService;

    @GetMapping(path = "/service")
    public ModelAndView getServicePage(){
        ModelAndView mv = new ModelAndView("admin/service");
        mv.addObject("serviceTypeList", serviceTypeService.findAll());
        mv.addObject("bookingRoomList", bookingFormService.findAll());
        return mv;
    }
}
