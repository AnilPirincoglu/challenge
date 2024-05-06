package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.CustomerResponse;
import enoca.challenge.shopping.entity.Customer;

public interface CustomerService {
    CustomerResponse addCustomer(Customer customer);
    Customer findCustomer(Long id);

    Customer findByEmail(String email);
}
