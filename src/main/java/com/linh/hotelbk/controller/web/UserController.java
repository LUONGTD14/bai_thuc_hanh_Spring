package com.linh.hotelbk.controller.web;

import com.linh.hotelbk.dto.request.RegistryRequest;
import com.linh.hotelbk.dto.request.ResetPasswordRequest;
import com.linh.hotelbk.entity.CityEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.service.ICityService;
import com.linh.hotelbk.service.ICountryService;
import com.linh.hotelbk.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
@Slf4j
@Api(tags = "Controller")
public class UserController {

    private final IUserService userService;
    private final ICountryService countryService;
    private final ICityService cityService;

    @GetMapping(path = "/login")
    public ModelAndView getLoginPage(){
        ModelAndView mv = new ModelAndView("web/dang-nhap");
        return mv;
    }

    @GetMapping(path = "/registry")
    public ModelAndView getRegistryPage(){
        ModelAndView mv = new ModelAndView("web/dang-ki");
        RegistryRequest registryRequest = new RegistryRequest();
        mv.addObject("registryRequest", registryRequest);
        mv.addObject("countryList", countryService.findAll());
        return mv;
    }

    @PostMapping(path = "/registry")
    public ModelAndView registry(@ModelAttribute("registryRequest") RegistryRequest registryRequest,
                                 BindingResult bindingResult ){
        ModelAndView mv = null;
        if(userService.existsByEmail(registryRequest.getEmail())) {
            log.info("Email đã tồn tại !");
            bindingResult.addError(new FieldError("registryRequest", "email", "email đã tồn tại !"));
            mv = new ModelAndView("web/dang-ki");
            mv.addObject("registryRequest", registryRequest);
            mv.addObject("countryList", countryService.findAll());
        }else{
            try {
                log.info("Thực hiện đăng kí");
                UserEntity registriedUser = userService.registry(registryRequest);
                mv = new ModelAndView("redirect:/login");
                mv.addObject("user", registriedUser);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return mv;
    }

    @GetMapping(path = "/forget-password")
    public ModelAndView getForgetPassPage(){
        ModelAndView mv = new ModelAndView("web/forgetPass");
        return mv;
    }

    @GetMapping(path = "/change-password/{token}")
    public ModelAndView getChangePassPage(@PathVariable String token){
        ModelAndView mv = new ModelAndView("web/changePass");
        mv.addObject("token", token);
        return mv;
    }

    @GetMapping(path = "/profile")
    public ModelAndView getProfilePage(){
        ModelAndView mv = new ModelAndView("web/profile");
        UserEntity currentUser = userService.getCurrentLoginUser();
        mv.addObject("user", currentUser);
        mv.addObject("countryList", countryService.findAll());
        CityEntity city = cityService.findById(currentUser.getAddress().getCityId());
        mv.addObject("city", city);
        return mv;
    }

    @GetMapping(path = "/change-current-pass")
    public ModelAndView getChangeCurrentPassPage(){
        ModelAndView mv = new ModelAndView("web/changeCurrentPass");
        UserEntity currentUser = userService.getCurrentLoginUser();
        mv.addObject("user", currentUser);
        return mv;
    }
}
