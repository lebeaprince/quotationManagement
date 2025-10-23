package registerationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import registerationlogin.entity.Product;

public interface ProductRepository  extends JpaRepository<Product,Long>  {

}
