package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.ServiceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceTypeRepository extends JpaRepository<ServiceTypeEntity,Long> {
}
