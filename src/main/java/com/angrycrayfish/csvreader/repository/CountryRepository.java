package com.angrycrayfish.csvreader.repository;

import com.angrycrayfish.csvreader.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

    public Country findCountryByCountryCode(String countryCode);
}
