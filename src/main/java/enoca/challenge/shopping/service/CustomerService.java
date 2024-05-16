package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.request.CustomerRequest;
import enoca.challenge.shopping.dto.response.CustomerResponse;
import enoca.challenge.shopping.entity.Customer;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);
    Customer findCustomer(Long customerId);

    Customer findByEmail(String email);
}
