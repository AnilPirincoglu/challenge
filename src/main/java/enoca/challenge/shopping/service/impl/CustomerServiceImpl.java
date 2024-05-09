package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.CustomerResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Customer;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.CustomerRepository;
import enoca.challenge.shopping.service.CustomerService;
import enoca.challenge.shopping.util.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public CustomerResponse addCustomer(Customer customer) {
        checkEmail(customer);
        Cart cart = new Cart(0d,customer,new ArrayList<>());
        customer.setCart(cart);
        return CustomerConverter
                .customerToResponse(customerRepository.save(customer));
    }

    @Override
    public Customer findCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new GlobalException("Customer with given id is not exist : " + customerId,
                                HttpStatus.NOT_FOUND));
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(()->
                new GlobalException("Customer with given email is not exist : " + email
                        , HttpStatus.NOT_FOUND));
    }

    private void checkEmail(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail())
                .isPresent())
            throw new GlobalException("This email is already used : " + customer.getEmail()
                    , HttpStatus.BAD_REQUEST);
    }
}


