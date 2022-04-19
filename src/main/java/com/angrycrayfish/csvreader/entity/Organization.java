package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "organization")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;

    private String groupName;
    private int groupSize;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CountryCode", referencedColumnName = "CountryCode")
    private Country country;
}
