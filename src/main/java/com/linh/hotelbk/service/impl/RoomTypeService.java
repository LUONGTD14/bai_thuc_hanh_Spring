package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.RoomTypeEntity;
import com.linh.hotelbk.repository.IRoomTypeRepository;
import com.linh.hotelbk.service.IRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService implements IRoomTypeService {

    @Autowired
    private IRoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomTypeEntity> findAll() {
        return roomTypeRepository.findAll();
    }

    @Override
    public void save(RoomTypeEntity roomType) {
        roomTypeRepository.save(roomType);
    }

    @Override
    public RoomTypeEntity findById(Long id) {
        return roomTypeRepository.findById(id).orElse(null);
    }
}
