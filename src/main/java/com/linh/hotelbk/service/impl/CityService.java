package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.entity.CityEntity;
import com.linh.hotelbk.entity.CountryEntity;
import com.linh.hotelbk.repository.ICityRepository;
import com.linh.hotelbk.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Override
    public List<CityEntity> findByCountry(CountryEntity country) {
        return cityRepository.findByCountry(country);
    }

    @Override
    public CityEntity findById(Long id) {
        return cityRepository.findById(id).get();
    }
}
