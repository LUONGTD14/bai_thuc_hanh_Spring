package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.ServiceEntity;
import com.linh.hotelbk.repository.IServiceRepository;
import com.linh.hotelbk.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService implements IServiceService {

    @Autowired
    private IServiceRepository serviceRepository;

    @Override
    public void save(ServiceEntity service) {
        serviceRepository.saveAndFlush(service);
    }

    @Override
    public ServiceEntity findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }
}
