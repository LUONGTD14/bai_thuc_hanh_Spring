package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.RoomTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface IRoomRepository extends JpaRepository<RoomEntity, Long> {


    @Query("SELECT r FROM RoomEntity r WHERE (:roomType is null OR r.roomType = :roomType) "+
           "AND (:price is null OR r.price = :price) "+
           "AND (:checkInDate is null OR r.availableFrom <= :checkInDate) "+
           "AND (:checkOutDate is null OR r.availableFrom <= :checkOutDate)")
    Page<RoomEntity> search(@Param("roomType")RoomTypeEntity roomType,
                            @Param("price") Integer price,
                            @Param("checkInDate") Date checkInDate,
                            @Param("checkOutDate") Date checkOutDate,
                            Pageable pageable);
}
