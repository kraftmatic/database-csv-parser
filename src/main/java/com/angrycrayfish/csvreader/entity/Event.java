package com.angrycrayfish.csvreader.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eventId;

    private Date eventDate;
    private float goldsteinScale;
    private int locationId;
    private int sourceId;
    private int actorOneId;
    private int actorTwoId;
}
