package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.repository.IBookingFormRepository;
import com.linh.hotelbk.service.IBookingFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingFormService implements IBookingFormService {

    @Autowired
    private IBookingFormRepository bookingFormRepository;

    @Override
    public BookingFormEntity save(BookingFormEntity bookingForm) {
        return bookingFormRepository.saveAndFlush(bookingForm);
    }

    @Override
    public List<BookingFormEntity> findByUser(UserEntity user) {
        return bookingFormRepository.findByUser(user);
    }

    @Override
    public BookingFormEntity findById(Long id) {
        return bookingFormRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        bookingFormRepository.deleteById(id);
    }

    @Override
    public List<BookingFormEntity> findAll() {
        return bookingFormRepository.findAll();
    }
}
