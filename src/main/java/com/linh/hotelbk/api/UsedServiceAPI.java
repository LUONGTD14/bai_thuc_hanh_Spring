package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.AddUsedServiceRequest;
import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.ServiceEntity;
import com.linh.hotelbk.entity.UsedServiceEntity;
import com.linh.hotelbk.service.IBookingFormService;
import com.linh.hotelbk.service.IServiceService;
import com.linh.hotelbk.service.IUsedServiceService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api(tags = "API")
public class UsedServiceAPI {

    private final IUsedServiceService usedServiceService;
    private final IServiceService serviceService;
    private final IBookingFormService bookingFormService;

    @PostMapping(path = "/api/usedService/add")
    public String addUsedService(@RequestBody AddUsedServiceRequest request){
        try {
            BookingFormEntity bookingForm = bookingFormService.findById(request.getBookingFormId());
            ServiceEntity service = serviceService.findById(request.getServiceId());
            UsedServiceEntity oldEntity = usedServiceService.findByBookingFormAndService(bookingForm, service);
            if (oldEntity != null){
                oldEntity.setQuantity(oldEntity.getQuantity()+ request.getQuantity());
                usedServiceService.save(oldEntity);
            }else{
                UsedServiceEntity usedService = UsedServiceEntity.builder()
                        .quantity(request.getQuantity())
                        .service(service)
                        .bookingForm(bookingForm)
                        .build();
                usedServiceService.save(usedService);
            }
            return "success";
        }catch (Exception e){
            return "error";
        }
    }

}
