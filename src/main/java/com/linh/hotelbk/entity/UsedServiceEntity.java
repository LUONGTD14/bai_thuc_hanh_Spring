package com.linh.hotelbk.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_used_service")
public class UsedServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usedServiceId")
    private Long id;


    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ServiceId", referencedColumnName = "ServiceId")
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "BookingFormId", referencedColumnName = "BookingFormId")
    private BookingFormEntity bookingForm;
}
