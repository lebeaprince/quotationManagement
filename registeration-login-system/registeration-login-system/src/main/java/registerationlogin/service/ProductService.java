package registerationlogin.service;

import java.util.List;

import registerationlogin.dto.ProductDTO;
import registerationlogin.entity.Product;

public interface ProductService {
    void saveProduct(ProductDTO userDto);

    Product findById(Long email);

    List<ProductDTO>findAllProducts();
}
