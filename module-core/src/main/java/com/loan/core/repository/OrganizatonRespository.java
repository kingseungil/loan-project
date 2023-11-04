package com.loan.core.repository;

import com.loan.core.entity.Organization;
import com.loan.core.type.OrganizationEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizatonRespository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByOrgEnum(OrganizationEnum organizationEnum);
}
