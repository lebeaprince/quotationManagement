package registerationlogin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import registerationlogin.dto.ProductDTO;
import registerationlogin.entity.Product;

import registerationlogin.repository.ProductRepository;
import registerationlogin.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository repo;

	public ProductServiceImpl(ProductRepository srepo){
		this.repo = srepo;
	}

	@Override
	public void saveProduct(ProductDTO userDto) {

		Product pro = new Product(userDto);
		this.repo.save(pro);
	}

	@Override
	public Product findById(Long email) {

		return this.repo.getReferenceById(email);
	}

	@Override
	public List<ProductDTO> findAllProducts() {

		List<Product>  pros= this.repo.findAll();
		List<ProductDTO> proDto = new ArrayList<>();
		for(Product qtt : pros){
			ProductDTO dto = new ProductDTO(qtt);
			proDto.add(dto);
		}
		return proDto;
	}
}
