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
    private int goldsteinScale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sourceId", referencedColumnName = "sourceId")
    private Source source;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actorOneId", referencedColumnName = "actorId")
    private Actor actorOne;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actorTwoId", referencedColumnName = "actorId")
    private Actor actorTwo;
}
