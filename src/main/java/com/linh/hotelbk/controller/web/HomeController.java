package com.linh.hotelbk.controller.web;

import com.linh.hotelbk.service.IRoomTypeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "Home Controller for Client")
@AllArgsConstructor
@Api(tags = "Controller")
public class HomeController {

    private final IRoomTypeService roomTypeService;

    @GetMapping(path = "/trang-chu")
    public ModelAndView getHomePage(){
        ModelAndView mv = new ModelAndView("web/trang-chu");
        mv.addObject("roomTypeList", roomTypeService.findAll());
        return mv;
    }
}
