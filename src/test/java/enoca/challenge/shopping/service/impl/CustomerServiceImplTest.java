package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.request.CustomerRequest;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Customer;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.CustomerRepository;
import enoca.challenge.shopping.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private CustomerService customerService;
    private Customer customer;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
        customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("testname");
        customer.setLastName("testlastname");
    }

    @DisplayName("Add Customer Successfully")
    @Test
    void addCustomerSuccess() {

        given(customerRepository.findByEmail("test@test.com")).willReturn(Optional.empty());
        Cart cart = new Cart(0d,customer,new ArrayList<>());;
        customer.setCart(cart);
        given(customerRepository.save(customer)).willReturn(customer);
        customerRepository.save(customer);
        verify(customerRepository).save(customer);
    }

    @DisplayName("Add Customer Failure")
    @Test
    void addCustomerFailure() {

        given(customerRepository.findByEmail("test@test.com")).willReturn(Optional.of(customer));
        assertThatThrownBy(()->customerService.addCustomer(new CustomerRequest(customer.getFirstName(), customer.getLastName(), customer.getEmail())))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("This email is already used : " + customer.getEmail());
        verify(customerRepository,never()).save(customer);

    }

    @DisplayName("Find Customer Successfully")
    @Test
    void findCustomerSuccess() {
        given(customerRepository.findById(customer.getId())).willReturn(Optional.of(customer));
        Customer byId = customerService.findCustomer(customer.getId());
        assertNotNull(byId);
        assertEquals(byId, customer);
    }

    @DisplayName("Find Customer Failure")
    @Test
    void findCustomerFailure() {
        given(customerRepository.findById(customer.getId())).willReturn(Optional.empty());
        assertThatThrownBy(()->customerService.findCustomer(customer.getId()))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("Customer with given id is not exist : " + customer.getId());
    }

    @DisplayName("Find By Email Successfully")
    @Test
    void findByEmailSuccess() {
        given(customerRepository.findByEmail(customer.getEmail())).willReturn(Optional.of(customer));
        Customer byEmail = customerService.findByEmail(customer.getEmail());
        assertNotNull(byEmail);
        assertEquals(byEmail, customer);
    }

    @DisplayName("Find By Email Failure")
    @Test
    void findByEmailFailure() {
        given(customerRepository.findByEmail(customer.getEmail())).willReturn(Optional.empty());
        assertThatThrownBy(()->customerService.findByEmail(customer.getEmail()))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("Customer with given email is not exist : " + customer.getEmail());
    }
}