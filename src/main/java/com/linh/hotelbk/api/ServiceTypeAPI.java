package com.linh.hotelbk.api;

import com.linh.hotelbk.entity.ServiceTypeEntity;
import com.linh.hotelbk.service.IServiceTypeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(tags = "API")
public class ServiceTypeAPI {
    @Autowired
    private IServiceTypeService serviceTypeService;

    @PostMapping(path = "/api/serviceType/add/{name}")
    public String addServiceType(@PathVariable String name){
        try {
            ServiceTypeEntity serviceType = ServiceTypeEntity.builder()
                    .serviceTypeName(name)
                    .build();
            serviceTypeService.save(serviceType);
            return "success";
        }catch (Exception e){
            return "error";
        }

    }
}
