package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.AddServiceRequest;
import com.linh.hotelbk.entity.ServiceEntity;
import com.linh.hotelbk.entity.ServiceTypeEntity;
import com.linh.hotelbk.service.IServiceService;
import com.linh.hotelbk.service.IServiceTypeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@Api(tags = "API")
public class ServiceAPI {

    private final IServiceService serviceService;
    private final IServiceTypeService serviceTypeService;

    @PostMapping(path = "/api/service/add")
    public String addService(@RequestBody AddServiceRequest request){
        try {
            ServiceTypeEntity serviceType = serviceTypeService.findById(request.getServiceTypeId());
            ServiceEntity service = ServiceEntity.builder()
                    .serviceName(request.getServiceName())
                    .price(request.getPrice())
                    .serviceType(serviceType)
                    .createAt(new Date())
                    .updateAt(new Date())
                    .build();
            serviceService.save(service);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }

    @GetMapping(path = "/api/service/{serviceTypeId}")
    public Set<ServiceEntity> loadByServiceType(@PathVariable Long serviceTypeId){
        ServiceTypeEntity serviceType = serviceTypeService.findById(serviceTypeId);
        return serviceType.getServices();
    }
}
