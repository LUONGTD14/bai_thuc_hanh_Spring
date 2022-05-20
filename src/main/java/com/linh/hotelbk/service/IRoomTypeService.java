package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.RoomTypeEntity;

import java.util.List;

public interface IRoomTypeService {
    List<RoomTypeEntity> findAll();
    void save(RoomTypeEntity roomType);
    RoomTypeEntity findById(Long id);
}
