package enoca.challenge.shopping.repository;

import enoca.challenge.shopping.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
