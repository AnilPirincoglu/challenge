package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.request.CustomerRequest;
import enoca.challenge.shopping.dto.response.CustomerResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Customer;

import java.util.ArrayList;

public class CustomerConverter {
    public static CustomerResponse customerToResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getEmail());
    }

    public static Customer requestToCustomer(CustomerRequest customerRequest) {
        return new Customer(customerRequest.firstName(),
                customerRequest.lastName(),
                customerRequest.email(),
                new Cart(0d, null, new ArrayList<>()), new ArrayList<>());
    }
}
