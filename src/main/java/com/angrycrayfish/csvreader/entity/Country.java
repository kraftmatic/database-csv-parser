package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "country")
@Data
public class Country {

    @Id
    @Column(name = "CountryCode")
    private String countryCode;

    private float longitude;
    private float latitude;
    private int population;
}
