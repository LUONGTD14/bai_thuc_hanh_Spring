package com.linh.hotelbk.controller.web;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Api(tags = "Controller")
public class LoginController {
    @GetMapping(path = "/login")
    public ModelAndView getLoginPage(){
        ModelAndView mv = new ModelAndView("web/dang-nhap");
        return mv;
    }
}
