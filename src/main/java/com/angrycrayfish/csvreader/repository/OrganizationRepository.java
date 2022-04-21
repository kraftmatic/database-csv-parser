package com.angrycrayfish.csvreader.repository;

import com.angrycrayfish.csvreader.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

    public Organization findOrganizationByGroupName(String groupName);
}
