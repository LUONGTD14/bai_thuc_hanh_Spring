package com.linh.hotelbk.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.hotelbk.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressId")
    private Long id;

    @Column(name = "CountryId", nullable = false)
    private Long countryId;

    @Column(name = "CityId", nullable = false)
    private Long cityId;

    @Column(name = "FullAddress", nullable = false)
    private String fullAddress;

    @OneToOne
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private UserEntity user;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateAt", nullable = false)
    private Date createAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "UpdateAt", nullable = false)
    private Date updateAt;

}
