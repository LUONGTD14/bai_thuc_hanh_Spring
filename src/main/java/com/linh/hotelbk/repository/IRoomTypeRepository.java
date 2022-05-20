package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomTypeRepository extends JpaRepository<RoomTypeEntity, Long> {
}
