package com.angrycrayfish.csvreader.repository;

import com.angrycrayfish.csvreader.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor findActorByActorName(String actorName);
}
