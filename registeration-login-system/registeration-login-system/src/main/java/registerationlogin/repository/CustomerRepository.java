package registerationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import registerationlogin.entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {

}
