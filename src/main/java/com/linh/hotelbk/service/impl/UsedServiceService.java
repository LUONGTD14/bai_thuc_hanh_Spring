package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.ServiceEntity;
import com.linh.hotelbk.entity.UsedServiceEntity;
import com.linh.hotelbk.repository.IUsedServiceRepository;
import com.linh.hotelbk.service.IUsedServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsedServiceService implements IUsedServiceService {

    @Autowired
    private IUsedServiceRepository usedServiceRepository;

    @Override
    public void save(UsedServiceEntity usedService) {
        usedServiceRepository.saveAndFlush(usedService);
    }

    @Override
    public UsedServiceEntity findByBookingFormAndService(BookingFormEntity bookingForm, ServiceEntity service) {
        return usedServiceRepository.findByBookingFormAndService(bookingForm,service);
    }
}
