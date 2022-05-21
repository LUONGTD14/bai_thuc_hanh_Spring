package com.linh.hotelbk.dto;

import com.linh.hotelbk.dto.request.RegistryRequest;
import com.linh.hotelbk.dto.response.RoomDTO;
import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.utils.enums.RoomStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mapper {

    public static UserEntity convertToUserEntity(RegistryRequest req){
        UserEntity user = UserEntity.builder()
                .email(req.getEmail())
                .fullName(req.getFullname())
                .phoneNumber(req.getPhone())
                .age(20)
                .gender("Nam")
                .isActive(true)
                .createAt(new Date())
                .updateAt(new Date())
                .build();
        return user;
    }

    public static RoomDTO convertToRoomDTO(RoomEntity room){
        RoomDTO roomDTO = RoomDTO.builder()
                .roomId(room.getId())
                .roomName(room.getRoomName())
                .roomImg(room.getImagePath())
                .price(room.getCurrency())
                .status(room.getAvailableFrom().before(new Date()) ? "AVAILABLE" : "RESERVED")
                .availableFrom(new SimpleDateFormat("yyyy-MM-dd").format(room.getAvailableFrom()))
                .build();

        return roomDTO;
    }
}

