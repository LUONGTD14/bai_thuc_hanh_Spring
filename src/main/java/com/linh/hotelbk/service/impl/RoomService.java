package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.RoomTypeEntity;
import com.linh.hotelbk.repository.IRoomRepository;
import com.linh.hotelbk.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository roomRepository;

    @Override
    public RoomEntity save(RoomEntity room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public Page<RoomEntity> findAll(int currentPage, int size, RoomTypeEntity roomType, Integer price, Date checkInDate, Date checkOutDate, String sortBy, String sortDir) {
        Sort sort = null;
        // Sort by
        if(sortBy.equals("CreateAt")) sort = Sort.by("createAt");
        else if(sortBy.equals("RoomName")) sort = Sort.by("roomName");
        else sort = Sort.by("price");
        // Sort direction
        sort = (sortDir.equals("asc")) ? sort.ascending() : sort.descending();
        // Create Pageable
        Pageable pageable = PageRequest.of(currentPage -1 , size, sort);
        return roomRepository.search(roomType, price, checkInDate, checkOutDate, pageable);
    }

    @Override
    public List<RoomEntity> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public RoomEntity findById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
