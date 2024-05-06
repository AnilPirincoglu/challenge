package enoca.challenge.shopping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "product", schema = "enoca_challenge")
public class Product extends BaseEntity {

    @NotBlank(message = "Name field can not be blank")
    @Size(min = 2, max = 30, message = "Name must be more then 2 and less then 30 characters")
    @Column(name = "name")
    private String name;

    @Positive(message = "Price must be more than 0")
    @Column(name = "price")
    private Double price;

    @Min(value = 1, message = "Stock must be more than 0")
    @Column(name = "stock_quantity")
    private int stockQuantity;

    @ManyToMany
    @JoinTable(name = "cart_product",
            schema = "enoca_challenge",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private List<Cart> carts;

    public void addCart(Cart cart) {
        if (carts == null)
            carts = new ArrayList<>();
        carts.add(cart);
    }

}
