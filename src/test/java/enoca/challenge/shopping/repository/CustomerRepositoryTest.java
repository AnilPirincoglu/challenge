package enoca.challenge.shopping.repository;

import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerRepositoryTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void setUp() {
            Customer customer = new Customer();
            customer.setEmail("test@test.com");
            customer.setFirstName("testname");
            customer.setLastName("testlastname");
            customerRepository.save(customer);
    }

    @AfterEach
    void afterEach() {
        customerRepository.delete(customerRepository.findByEmail("test@test.com").orElseThrow());
    }

    @DisplayName("findByEmail success")
    @Test
    void findByEmailSuccess() {
        Customer customer = customerRepository.findByEmail("test@test.com").orElse(null);
        assertNotNull(customer);
        assertEquals(customer.getFirstName(), "testname");
        assertEquals(customer.getLastName(), "testlastname");
    }

    @DisplayName("findByEmail failure")
    @Test
    void findByEmailFailure() {
        Customer customer = customerRepository.findByEmail("test@test").orElse(null);
        assertNull(customer);
    }
}