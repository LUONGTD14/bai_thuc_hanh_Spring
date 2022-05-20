package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.CountryEntity;
import com.linh.hotelbk.repository.ICountryRepository;
import com.linh.hotelbk.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public List<CountryEntity> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public CountryEntity findById(Long countryId) {
        return countryRepository.findById(countryId).orElse(null);
    }
}
