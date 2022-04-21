package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity(name = "country")
@Data
public class Country {

    @Id
    @Column(name = "CountryCode")
    private String countryCode;

    private float longitude;
    private float latitude;
    private int population;
    private String language;

}
