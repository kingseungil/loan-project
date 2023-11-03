package com.zb.loanproject.repository;

import com.zb.loanproject.domain.Organization;
import com.zb.loanproject.type.OrganizationEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizatonRespository extends JpaRepository<Organization, Long> {

    Optional<Organization> findByOrgEnum(OrganizationEnum organizationEnum);
}
