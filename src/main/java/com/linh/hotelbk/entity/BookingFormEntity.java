package com.linh.hotelbk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.hotelbk.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_booking_form")
public class BookingFormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingFormId")
    private Long id;

    @Column(name = "CustomerName", nullable = false)
    private String customerName;

    @Column(name = "PhoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "CustomerEmail", nullable = false)
    private String customerEmail;

    @Column(name = "BookingDays", nullable = false)
    private Integer bookingDays;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @JsonIgnore
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "RoomId", referencedColumnName = "RoomId")
    @JsonIgnore
    private RoomEntity room;

    @OneToMany(mappedBy = "bookingForm", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UsedServiceEntity> usedServices;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CheckInAt")
    private Date checkInAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CheckOutAt")
    private Date checkOutAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateAt", nullable = false)
    private Date createAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "UpdateAt")
    private Date updateAt;
}
