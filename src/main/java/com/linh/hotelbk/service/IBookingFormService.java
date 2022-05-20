package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.UserEntity;

import java.util.List;

public interface IBookingFormService {
    BookingFormEntity save(BookingFormEntity bookingForm);
    List<BookingFormEntity> findByUser(UserEntity user);
    BookingFormEntity findById(Long id);
    void delete(Long id);
    List<BookingFormEntity> findAll();
}
