package enoca.challenge.shopping.util;
import enoca.challenge.shopping.dto.CustomerResponse;
import enoca.challenge.shopping.entity.Customer;

public class CustomerConverter {
    public static CustomerResponse customerToResponse(Customer customer){
        return new CustomerResponse(customer.getFirstName(), customer.getEmail());
    }
}
