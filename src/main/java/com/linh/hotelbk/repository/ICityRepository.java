package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.CityEntity;
import com.linh.hotelbk.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findByCountry(CountryEntity country);
}
