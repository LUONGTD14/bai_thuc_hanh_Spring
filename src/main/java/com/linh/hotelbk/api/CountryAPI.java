package com.linh.hotelbk.api;

import com.linh.hotelbk.entity.CountryEntity;
import com.linh.hotelbk.service.ICountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "API")
public class CountryAPI {

    @Autowired
    private ICountryService countryService;

    @GetMapping(path = "api/get-all-country")
    @ApiOperation(value = "get all Country in databse", response = ArrayList.class)
    public List<CountryEntity> getAllCountry(){
        return countryService.findAll();
    }
}
