package com.linh.hotelbk.controller.admin;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.UsedServiceEntity;
import com.linh.hotelbk.service.IBookingFormService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/admin")
@Api(tags = "Controller")
@AllArgsConstructor
public class UsedServiceController {

    private final IBookingFormService bookingFormService;

    @GetMapping(path = "/used-service/{bookingId}")
    public ModelAndView getUsedServiceTable(@PathVariable Long bookingId){
        BookingFormEntity bookingForm = bookingFormService.findById(bookingId);
        ModelAndView mv = new ModelAndView("admin/usedServiceTable");
        mv.addObject("bookingForm", bookingForm);
        if(bookingForm != null)
            mv.addObject("usedServiceList", bookingForm.getUsedServices());
        else
            mv.addObject("usedServiceList", new ArrayList<UsedServiceEntity>());
        return mv;
    }
}
