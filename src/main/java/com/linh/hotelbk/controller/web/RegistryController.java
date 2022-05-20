package com.linh.hotelbk.controller.web;

import com.linh.hotelbk.dto.request.RegistryRequest;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.service.ICountryService;
import com.linh.hotelbk.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@Slf4j
@Api(tags = "Controller")
public class RegistryController {

    private final ModelMapper modelMapper;
    private final IUserService userService;
    private final ICountryService countryService;

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
}
