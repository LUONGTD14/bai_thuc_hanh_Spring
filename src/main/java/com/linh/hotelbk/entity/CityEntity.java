package com.linh.hotelbk.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CityId")
    private Long id;

    @Column(name = "CityName", nullable = false)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "CountryId", referencedColumnName = "CountryId")
    private CountryEntity country;
}
