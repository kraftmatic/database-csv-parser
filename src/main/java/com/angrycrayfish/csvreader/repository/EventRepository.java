package com.angrycrayfish.csvreader.repository;

import com.angrycrayfish.csvreader.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
