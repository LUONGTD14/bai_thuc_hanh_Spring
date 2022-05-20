package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
