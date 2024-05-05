package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.CustomerResponse;
import enoca.challenge.shopping.entity.Customer;
import enoca.challenge.shopping.exceptions.GlobalException;
import enoca.challenge.shopping.repository.CustomerRepository;
import enoca.challenge.shopping.service.CustomerService;
import enoca.challenge.shopping.util.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse addCustomer(Customer customer) {
        isEmailUsed(customer);
        return CustomerConverter
                .customerToResponse(customerRepository.save(customer));
    }

    @Override
    public Customer findCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new GlobalException("Customer with given id is not exist : " + id,
                                HttpStatus.NOT_FOUND));
    }

    private void isEmailUsed(Customer customer) {//TODO 2.1
        if (customerRepository.findByEmail(customer.getEmail()).isPresent())
            throw new GlobalException("This email is already used : " + customer.getEmail()
                    , HttpStatus.BAD_REQUEST);
    }
}


