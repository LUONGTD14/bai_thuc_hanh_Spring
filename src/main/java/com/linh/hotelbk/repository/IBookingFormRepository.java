package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookingFormRepository extends JpaRepository<BookingFormEntity, Long> {
    List<BookingFormEntity> findByUser(UserEntity user);

}
