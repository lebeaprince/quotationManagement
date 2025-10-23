package registerationlogin.service;

import java.util.List;

import registerationlogin.dto.CustomerDTO;
import registerationlogin.entity.Customer;

public interface CustomerService {
    void saveCustomer(CustomerDTO userDto);

    Customer findById(Long email);

    List<CustomerDTO>findAllCustomers();
}
