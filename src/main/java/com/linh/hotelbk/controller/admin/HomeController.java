package com.linh.hotelbk.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "Home Controller for Admin")
@RequestMapping(path = "/admin")
@Api(tags = "Controller")
public class HomeController {

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/trang-chu")
    public ModelAndView getAdminHomePage(){
        ModelAndView mv = new ModelAndView("admin/trang-chu");
        return mv;
    }
}
