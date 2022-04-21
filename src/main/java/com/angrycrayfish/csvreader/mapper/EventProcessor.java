package com.angrycrayfish.csvreader.mapper;

import com.angrycrayfish.csvreader.entity.*;
import com.angrycrayfish.csvreader.repository.*;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class EventProcessor {

    private SourceRepository sourceRepository;
    private CountryRepository countryRepository;
    private OrganizationRepository organizationRepository;
    private LocationRepository locationRepository;
    private ActorRepository actorRepository;
    private EventRepository eventRepository;

    public EventProcessor(@NonNull SourceRepository sourceRepository,
                          @NonNull CountryRepository countryRepository,
                          @NonNull OrganizationRepository organizationRepository,
                          @NonNull LocationRepository locationRepository,
                          @NonNull ActorRepository actorRepository,
                          @NonNull EventRepository eventRepository){
        this.sourceRepository = sourceRepository;
        this.countryRepository = countryRepository;
        this.organizationRepository = organizationRepository;
        this.locationRepository = locationRepository;
        this.actorRepository = actorRepository;
        this.eventRepository = eventRepository;
    }

    public void mapEntryToEvent(List<String> entry) {
        Event event = new Event();

        Source source = sourceRepository.findSourceBySourceUrl(entry.get(57));
        if (source == null) {
            source = new Source();
            source.setSourceUrl(entry.get(57));
            source.setSourceDate(convertDateToSqlDate(entry.get(56)));
            source = sourceRepository.save(source);
        }

        Country countryOne = countryRepository.findCountryByCountryCode(entry.get(7));
        if (countryOne == null) {
            countryOne = new Country();
            countryOne.setCountryCode(entry.get(7));
            countryOne.setLongitude(0.0f);
            countryOne.setLatitude(0.0f);
            countryOne.setPopulation(0);
            countryOne.setLanguage("");
            countryOne = countryRepository.save(countryOne);
        }

        Country countryTwo = countryRepository.findCountryByCountryCode(entry.get(17));
        if (countryTwo == null) {
            countryTwo = new Country();
            countryTwo.setCountryCode(entry.get(17));
            countryTwo.setLongitude(0.0f);
            countryTwo.setLatitude(0.0f);
            countryTwo.setPopulation(0);
            countryTwo.setLanguage("");
            countryTwo = countryRepository.save(countryTwo);
        }

        Organization groupOne = organizationRepository.findOrganizationByGroupName(entry.get(8));
        if (groupOne == null && entry.get(8) != null) {
            groupOne = new Organization();
            groupOne.setGroupName(entry.get(8));
            groupOne.setCountryCode("");
            groupOne.setGroupSize(0);
            groupOne = organizationRepository.save(groupOne);
        }

        Organization groupTwo = organizationRepository.findOrganizationByGroupName(entry.get(18));
        if (groupTwo == null && entry.get(18) != null) {
            groupTwo = new Organization();
            groupTwo.setGroupName(entry.get(18));
            groupTwo.setCountryCode("");
            groupTwo.setGroupSize(0);
            groupTwo = organizationRepository.save(groupTwo);
        }

        Location location = locationRepository.findLocationByLocationName(entry.get(50));
        if (location == null && entry.get(50) != null) {
            location = new Location();
            location.setLocationName(entry.get(50));
            location.setLocationType(entry.get(49));
            location.setCountryCode("");
            location = locationRepository.save(location);
        }

        Actor actorOne = actorRepository.findActorByActorName(entry.get(6));
        if (actorOne == null) {
            actorOne = new Actor();
            if (groupOne != null && groupOne.getGroupId() != 0) {
                actorOne.setGroupId(groupOne.getGroupId());
            }
            actorOne.setCountryCode(countryOne.getCountryCode());
            actorOne.setActorName(entry.get(6));
            actorOne.setActorType(entry.get(11));
            actorOne = actorRepository.save(actorOne);
        }

        Actor actorTwo = actorRepository.findActorByActorName(entry.get(16));
        if (actorTwo == null) {
            actorTwo = new Actor();
            if (groupTwo != null && groupTwo.getGroupId() != 0) {
                actorTwo.setGroupId(groupTwo.getGroupId());
            }
            actorTwo.setCountryCode(countryTwo.getCountryCode());
            actorTwo.setActorName(entry.get(16));
            actorTwo.setActorType(entry.get(21));
            actorTwo = actorRepository.save(actorTwo);
        }

        event.setEventDate(convertDateToSqlDate(entry.get(1)));
        if (StringUtils.isNotBlank(entry.get(30))) {
            event.setGoldsteinScale(Float.parseFloat(entry.get(30)));
        }
        event.setActorOneId(actorOne.getActorId());
        event.setActorTwoId(actorTwo.getActorId());
        event.setSourceId(source.getSourceId());
        if (location != null && location.getLocationId() != 0) {
            event.setLocationId(location.getLocationId());
        }

        eventRepository.save(event);
    }

    private Date convertDateToSqlDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Date(parsed.getTime());
    }
}
