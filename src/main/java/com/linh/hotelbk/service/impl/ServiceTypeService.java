package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.ServiceTypeEntity;
import com.linh.hotelbk.repository.IServiceTypeRepository;
import com.linh.hotelbk.service.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ServiceTypeService implements IServiceTypeService {

    @Autowired
    private IServiceTypeRepository serviceTypeRepository;

    @Override
    public void save(ServiceTypeEntity serviceType) {
        serviceTypeRepository.saveAndFlush(serviceType);
    }

    @Override
    public ServiceTypeEntity findById(Long id) {
        return serviceTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<ServiceTypeEntity> findAll() {
        return serviceTypeRepository.findAll();
    }


}
