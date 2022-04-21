package com.angrycrayfish.csvreader.repository;

import com.angrycrayfish.csvreader.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findLocationByLocationName(String locationName);
}
