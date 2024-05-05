package enoca.challenge.shopping.repository;

import enoca.challenge.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
