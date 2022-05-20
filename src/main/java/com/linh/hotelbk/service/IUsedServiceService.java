package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.ServiceEntity;
import com.linh.hotelbk.entity.UsedServiceEntity;

public interface IUsedServiceService {
    void save(UsedServiceEntity usedService);
    UsedServiceEntity findByBookingFormAndService(BookingFormEntity bookingForm, ServiceEntity service);
}
