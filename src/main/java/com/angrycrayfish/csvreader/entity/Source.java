package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sourceId;

    private String sourceUrl;
    private Date sourceDate;

}
