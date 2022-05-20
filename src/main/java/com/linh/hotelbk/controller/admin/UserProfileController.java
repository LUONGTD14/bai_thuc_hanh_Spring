package com.linh.hotelbk.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/admin")
@Api(tags = "Controller")
public class UserProfileController {

    @GetMapping(path = "/user-profile")
    public ModelAndView getUserProfilePage(){
        ModelAndView mv = new ModelAndView("admin/user-profile");
        return mv;
    }
}
