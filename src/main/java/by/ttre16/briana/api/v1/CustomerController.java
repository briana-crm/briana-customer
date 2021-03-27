package by.ttre16.briana.api.v1;

import by.ttre16.briana.entity.Customer;
import by.ttre16.briana.exception.IllegalRequestDataException;
import by.ttre16.briana.service.CustomerService;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@RefreshScope
public class CustomerController {
    private final CustomerService customerService;
    private final ApplicationInfoManager applicationInfoManager;

    @Value("${testSecretValue:default}")
    private String secret;

    @GetMapping("/hello")
    public InstanceInfo hello() {
        return applicationInfoManager.getInfo();
    }

    @GetMapping("/value")
    public String value() {
        return secret;
    }

    @GetMapping
    public List<Customer> getAll(@RequestParam Long organizationId,
                                 @RequestParam Integer page,
                                 @RequestParam Integer pageSize) {
        return customerService.getAll(organizationId, PageRequest.of(page, pageSize));
    }

    @GetMapping("/{id}")
    public Customer getOne(@RequestParam Long organizationId, @PathVariable Long id) {
        return customerService.getOne(id, organizationId);
    }

    @PostMapping
    public Customer save(@RequestParam Long organizationId,
                         @RequestParam Long publisherId,
                         @RequestBody Customer customer) {
        return customerService.save(customer, organizationId, publisherId);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,
                       @RequestParam Long organizationId,
                       @RequestParam Long authenticatedId,
                       @RequestBody Customer customer) {
        if (id.equals(customer.getId())) {
            customerService.update(customer, organizationId, authenticatedId);
        } else {
            throw new IllegalRequestDataException();
        }
    }
}
