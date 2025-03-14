package az.developia.e_commerce_project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.e_commerce_project.entity.ProductEntity;
import az.developia.e_commerce_project.exception.OurRuntimeException;
import az.developia.e_commerce_project.requestDto.ProductRequestDto;
import az.developia.e_commerce_project.response.ProductResponse;
import az.developia.e_commerce_project.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/products")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@PostMapping(path = "/create")
	public ResponseEntity<ProductRequestDto> createProduct(@Valid @RequestBody ProductRequestDto dto,
			BindingResult result) throws OurRuntimeException {
		if (result.hasErrors()) {
			throw new OurRuntimeException(result, "melumatlarin tamligi pozulub");
		}
		ProductEntity product = new ProductEntity();
		product.setId(null);
		product.setBrand(dto.getBrand());
		product.setModel(dto.getModel());
		product.setCategory(dto.getCategory());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setRating(dto.getRating());
		product.setImgUrl(dto.getImgUrl());

		productService.add(product);

		return new ResponseEntity<ProductRequestDto>(HttpStatus.CREATED);
	}

	@GetMapping(path = "/getAll")
	public List<ProductResponse> getProducts() {
		List<ProductEntity> products = productService.getAll();

		return products.stream()
				.map(product -> ProductResponse.builder()
						.id(product.getId())
						.brand(product.getBrand())
						.model(product.getModel())
						.category(product.getCategory())
						.description(product.getDescription())
						.price(product.getPrice())
						.rating(product.getRating())
						.imgUrl(product.getImgUrl()).build()
					)
				.collect(Collectors.toList());
	}
	
	@GetMapping(path = "/{id}")
	public ProductResponse getById(@PathVariable Integer id) {
		ProductEntity byId = productService.getById(id);
		return ProductResponse.builder()
				.id(byId.getId())
				.brand(byId.getBrand())
				.model(byId.getModel())
				.category(byId.getCategory())
				.description(byId.getDescription())
				.price(byId.getPrice())
				.rating(byId.getRating())
				.imgUrl(byId.getImgUrl())
				.build();
	}
	
	
	@PutMapping
	public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductRequestDto dto,
			BindingResult result) throws OurRuntimeException {
		if (result.hasErrors()) {
			throw new OurRuntimeException(result, "Melumatlarin tamligi pozulub");
		}
		productService.update(dto);
		
		return ResponseEntity.ok("Product update successfully");
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		productService.delete(id);
		
		return ResponseEntity.ok("product delete successfully");
	}
}
