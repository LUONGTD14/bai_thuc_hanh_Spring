package com.linh.hotelbk.api;

import com.linh.hotelbk.entity.CityEntity;
import com.linh.hotelbk.entity.CountryEntity;
import com.linh.hotelbk.service.ICityService;
import com.linh.hotelbk.service.ICountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Api(tags = "API")
public class CityAPI {

    private final ICountryService countryService;
    private final ICityService cityService;

    @GetMapping(path = "api/city/{id}")
    @ApiOperation(value = "get cities by country id", response = ArrayList.class)
    public List<CityEntity> getCityByCountryId(@PathVariable("id") Long countryId){
        CountryEntity country = countryService.findById(countryId);
        return cityService.findByCountry(country);
    }
}
