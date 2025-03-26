package az.developia.product_shopping.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.product_shopping.dto.ProductDto;
import az.developia.product_shopping.entity.CustomerEntity;
import az.developia.product_shopping.entity.ProductEntity;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.repository.CustomerRepository;
import az.developia.product_shopping.repository.ProductRepository;
import az.developia.product_shopping.response.ProductResponse;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	public void addProduct(ProductDto dto) {
		ProductEntity productEntity = new ProductEntity();
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		CustomerEntity userByUsername = customerRepository.getUserByUsername(username);
		Integer userId=userByUsername.getId();

		mapper.map(dto, productEntity);
		productEntity.setId(null);
		productEntity.setCustomerId(userId);
		
		repository.save(productEntity);
	}

	public List<ProductResponse> getAllProducts() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		CustomerEntity userByUsername = customerRepository.getUserByUsername(username);
		Integer userId=userByUsername.getId();
		
		List<ProductEntity> products = repository.findAllByCustomerId(userId);

		return products.stream().map(product -> mapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
	}

	public ProductResponse getByIdProduct(Integer id) {
		Optional<ProductEntity> byId = repository.findById(id);
		if (id == null || id == 0) {
			throw new OurRuntimeException(null, "id mutleqdir");
		}
		if (byId.isPresent()) {
			return mapper.map(byId.get(), ProductResponse.class);
		} else {
			throw new OurRuntimeException(null, "id not found");
		}

	}

	public void update(@Valid ProductDto dto) {
		Optional<ProductEntity> existingProduct = repository.findById(dto.getId());

		if (dto.getId() == null || dto.getId() <= 0) {
			throw new OurRuntimeException(null, "id mutleqdir ve 0-dan boyuk olmalidir!");
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		CustomerEntity userByUsername = customerRepository.getUserByUsername(username);
		Integer userId=userByUsername.getId();
		
		if (existingProduct.isPresent()) {
			ProductEntity destination = existingProduct.get();
			mapper.map(dto, destination);
			destination.setCustomerId(userId);
			repository.save(destination);
		} else {
			throw new OurRuntimeException(null, "id not found");
		}
	}

	public void delete(Integer id) {
		Optional<ProductEntity> byId = repository.findById(id);
		if (id == null || id <= 0) {
			throw new OurRuntimeException(null, "id mutleqdir ve 0-dan boyuk olmalidir!");
		}
		if (byId.isPresent()) {
			repository.deleteById(id);
			;
		} else {
			throw new OurRuntimeException(null, "id not found");
		}
	}

	public List<ProductResponse> getProducts() {
		
		List<ProductEntity> products = repository.findAll();

		return products.stream().map(product -> mapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
	}

	public List<ProductResponse> searchProducts(String query) {
		List<ProductEntity> products = repository.findAll();
		
		return products.stream()
				.filter(product->product.getModel().toLowerCase().contains(query.toLowerCase()))
				.map(product->mapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
	}

}
