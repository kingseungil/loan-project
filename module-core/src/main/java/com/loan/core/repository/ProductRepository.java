package com.loan.core.repository;

import com.loan.core.entity.Organization;
import com.loan.core.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByOrganization(Organization organization);
}
