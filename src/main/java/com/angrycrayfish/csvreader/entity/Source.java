package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sourceId;

    private String sourceUrl;
    private String publisher;
}