package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountryRepository extends JpaRepository<CountryEntity, Long> {
}
