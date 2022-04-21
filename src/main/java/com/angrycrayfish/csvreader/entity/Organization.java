package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "organization")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int groupId;

    private String groupName;
    private int groupSize;
    private String countryCode;

}
