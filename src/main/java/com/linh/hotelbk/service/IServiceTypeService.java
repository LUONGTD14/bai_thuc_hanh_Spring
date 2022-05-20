package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.ServiceTypeEntity;

import java.util.List;

public interface IServiceTypeService {
    void save(ServiceTypeEntity serviceType);
    ServiceTypeEntity findById(Long id);
    List<ServiceTypeEntity> findAll();
}
