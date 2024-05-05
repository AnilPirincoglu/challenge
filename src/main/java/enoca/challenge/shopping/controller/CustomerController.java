package enoca.challenge.shopping.controller;

import enoca.challenge.shopping.dto.CustomerResponse;
import enoca.challenge.shopping.entity.Customer;
import enoca.challenge.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public CustomerResponse addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
}
