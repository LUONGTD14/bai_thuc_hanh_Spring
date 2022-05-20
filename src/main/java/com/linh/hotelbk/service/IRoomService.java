package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.RoomTypeEntity;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    RoomEntity save(RoomEntity room);

    Page<RoomEntity> findAll(int currentPage, int size,
                             RoomTypeEntity roomType, Integer price,
                             Date checkInDate, Date checkOutDate,
                             String sortBy, String sortDir);

    List<RoomEntity> findAll();

    RoomEntity findById(Long id);

    void delete(Long id);
}
