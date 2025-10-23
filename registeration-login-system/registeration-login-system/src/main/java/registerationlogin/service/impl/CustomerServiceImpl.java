package registerationlogin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import registerationlogin.dto.CustomerDTO;
import registerationlogin.dto.ProductDTO;
import registerationlogin.entity.Customer;
import registerationlogin.entity.Product;
import registerationlogin.repository.CustomerRepository;
import registerationlogin.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	public CustomerRepository repo;

	public CustomerServiceImpl(CustomerRepository srepo){
		this.repo = srepo;
	}
	
	@Override
	public void saveCustomer(CustomerDTO userDto) {
		
		Customer cust = new Customer(userDto);
		repo.save(cust);
	}

	@Override
	public Customer findById(Long email) {
		
		return repo.getReferenceById(email);
	}

	@Override
	public List<CustomerDTO> findAllCustomers() {
		
		List<Customer>  pros= repo.findAll();
		List<CustomerDTO> proDto = new ArrayList<>();
		for(Customer qtt : pros){
			CustomerDTO dto = new CustomerDTO(qtt);
			proDto.add(dto);
		}
		return proDto;
	}

}
