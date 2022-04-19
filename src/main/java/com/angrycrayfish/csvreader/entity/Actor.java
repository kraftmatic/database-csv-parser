package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int actorId;

    private String actorType;
    private String actorName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CountryCode", referencedColumnName = "CountryCode")
    private Country country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId", referencedColumnName = "groupId")
    private Organization group;
}
