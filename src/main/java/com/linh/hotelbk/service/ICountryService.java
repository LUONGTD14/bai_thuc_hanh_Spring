package com.linh.hotelbk.service;

import com.linh.hotelbk.entity.CountryEntity;

import java.util.List;

public interface ICountryService {
    List<CountryEntity> findAll();
    CountryEntity findById(Long countryId);
}
