package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.ServiceEntity;

public interface IServiceService {
    void save(ServiceEntity service);
    ServiceEntity findById(Long id);
}
