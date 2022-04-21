package com.angrycrayfish.csvreader.repository;

import com.angrycrayfish.csvreader.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<Source, Integer> {
    Source findSourceBySourceUrl(String sourceUrl);
}
