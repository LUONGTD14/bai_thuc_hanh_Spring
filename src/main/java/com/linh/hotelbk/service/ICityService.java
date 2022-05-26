package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.CityEntity;
import com.linh.hotelbk.entity.CountryEntity;

import java.util.List;

public interface ICityService {
    List<CityEntity> findByCountry(CountryEntity country);
    CityEntity findById(Long id);
}
