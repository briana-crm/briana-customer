package by.ttre16.briana.repository;

import by.ttre16.briana.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdAndOrganizationId(Long id, Long organizationId);

    Page<Customer> findAllByOrganizationId(Long organizationId, Pageable pageable);

    boolean existsByIdAndOrganizationId(Long id, Long organizationId);
}
