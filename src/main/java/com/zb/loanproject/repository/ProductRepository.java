package com.zb.loanproject.repository;

import com.zb.loanproject.domain.Organization;
import com.zb.loanproject.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByOrganization(Organization organization);
}
