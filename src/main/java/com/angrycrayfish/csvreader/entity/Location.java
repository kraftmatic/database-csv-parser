package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int locationId;

    private String locationType;
    private String locationName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CountryCode", referencedColumnName = "CountryCode")
    private Country country;
}
