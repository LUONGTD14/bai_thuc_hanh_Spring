package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.ServiceEntity;
import com.linh.hotelbk.entity.UsedServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUsedServiceRepository extends JpaRepository<UsedServiceEntity , Long> {
    @Query("SELECT u FROM UsedServiceEntity u WHERE u.bookingForm = :bookingForm AND u.service = :service")
   UsedServiceEntity findByBookingFormAndService(@Param("bookingForm") BookingFormEntity bookingForm,
                                                 @Param("service")ServiceEntity service);
}
