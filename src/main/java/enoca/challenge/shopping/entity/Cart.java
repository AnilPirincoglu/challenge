package enoca.challenge.shopping.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart", schema = "enoca_challenge")
public class Cart extends BaseEntity {

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "cart_product",
            schema = "enoca_challenge",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public void addProduct(Product product) {
        if (products == null)
            products = new ArrayList<>();
        products.add(product);
    }
}