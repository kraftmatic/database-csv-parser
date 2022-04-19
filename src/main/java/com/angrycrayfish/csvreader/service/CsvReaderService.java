package com.angrycrayfish.csvreader.service;

import com.angrycrayfish.csvreader.entity.*;
import com.angrycrayfish.csvreader.repository.EventRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class CsvReaderService {

    private EventRepository eventRepository;

    CsvReaderService(@NonNull EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @PostConstruct
    private void importCsvFiles(){

        //TODO: remove this test data and implement CSV reader

        Country country = new Country();
        country.setCountryCode("abc");
        country.setLatitude(123.123F);
        country.setLongitude(321.321F);
        country.setPopulation(123456);

        Location location = new Location();
        location.setLocationName("locationName");
        location.setLocationType("locationType");
        location.setCountry(country);

        Organization group = new Organization();
        group.setGroupName("groupName");
        group.setGroupSize(1234);
        group.setCountry(country);

        Actor actorOne = new Actor();
        actorOne.setActorName("actorOneName");
        actorOne.setActorType("actorType");
        actorOne.setCountry(country);
        actorOne.setGroup(group);

        Actor actorTwo = new Actor();
        actorTwo.setActorName("actorTwoName");
        actorTwo.setActorType("actorType");
        actorTwo.setCountry(country);
        actorTwo.setGroup(group);

        Source source = new Source();
        source.setSourceUrl("www.whatever.com");
        source.setPublisher("publisher");

        Event event = new Event();
        event.setEventDate(Date.valueOf(LocalDate.now()));
        event.setGoldsteinScale(3);
        event.setLocation(location);
        event.setActorOne(actorOne);
        event.setActorTwo(actorTwo);
        event.setSource(source);

        eventRepository.save(event);
    }
}
