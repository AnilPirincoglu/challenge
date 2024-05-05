package enoca.challenge.shopping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer", schema = "enoca_challenge")
public class Customer extends BaseEntity {

    @NotBlank(message = "Name field can not be blank")
    @Size(min = 2, max = 30, message = "Name must be more then 2 and less then 30 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name field can not be blank")
    @Size(min = 2, max = 30, message = "Last name must be more then 2 and less then 30 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email field can not be blank")
    @Size(max = 80, message = "Email must be more then 2 and less then 80 characters")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email is not valid")
    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orders;

    public void addOrder(Order order){
        if(orders==null)
            orders=new ArrayList<>();
        orders.add(order);
    }
}
