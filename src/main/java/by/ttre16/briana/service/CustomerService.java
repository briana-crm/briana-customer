package by.ttre16.briana.service;

import by.ttre16.briana.entity.Customer;
import by.ttre16.briana.exception.EntityNotFoundException;
import by.ttre16.briana.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getOne(Long id, Long organizationId) {
        return customerRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Customer> getAll(Long organizationId, Pageable pageable) {
        return customerRepository.findAllByOrganizationId(organizationId, pageable).getContent();
    }

    @Transactional
    public Customer save(Customer customer, Long organizationId, Long publisherId) {
        Customer saved = this.save(customer, organizationId);
        pushCreateNotification(publisherId, saved.getId());
        return saved;
    }

    @Transactional
    public void update(Customer customer, Long organizationId, Long publisherId) {
        if (customerRepository.existsByIdAndOrganizationId(customer.getId(), organizationId)) {
            this.save(customer, organizationId);
            this.pushUpdateNotification(publisherId, customer.getId());
        } else {
            throw new EntityNotFoundException();
        }
    }

    private Customer save(Customer customer, Long organizationId) {
        customer.setOrganizationId(organizationId);
        return customerRepository.save(customer);
    }

    private void pushCreateNotification(Long customerId, Long publisherId) {
        this.pushNotification(customerId, publisherId, "customer:create");
    }

    private void pushUpdateNotification(Long customerId, Long publisherId) {
        this.pushNotification(customerId, publisherId, "customer:update");
    }

    private void pushNotification(Long customerId, Long publisherId, String type) { }
}
