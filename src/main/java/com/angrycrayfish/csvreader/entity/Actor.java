package com.angrycrayfish.csvreader.entity;

import lombok.Data;
import org.springframework.boot.actuate.integration.IntegrationGraphEndpoint;

import javax.persistence.*;

@Entity
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int actorId;

    private String actorType;
    private String actorName;
    private Integer groupId;
    private String countryCode;

}
