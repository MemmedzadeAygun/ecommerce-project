package az.developia.product_shopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.product_shopping.dto.ProductDto;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.response.ProductResponse;
import az.developia.product_shopping.service.ProductService;

@RestController
@RequestMapping(path = "/products")
@CrossOrigin(origins = "*")
public class ProductRestController {

	@Autowired
	private ProductService service;

	@PostMapping(path = "/add")
	@PreAuthorize("hasAuthority('ROLE_ADD_PRODUCT')")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDto dto, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "melumatlarin tamliginda problem var");
		}
		service.addProduct(dto);
		return ResponseEntity.ok("Product added successfully");
	}

	@GetMapping(path = "/getAll")
	@PreAuthorize("hasAuthority('ROLE_GET_PRODUCT')")
	public List<ProductResponse> getProducts() {
		return service.getAllProducts();
	}
	
	@GetMapping(path = "/getAllProduct")
	@PreAuthorize("hasAuthority('ROLE_GET_PRODUCT')")
	public List<ProductResponse> getAllProducts() {
		return service.getProducts();
	}
	
	@GetMapping(path = "/searchProduct")
	public List<ProductResponse> searchProducts(@RequestParam(name = "query") String query){
		return service.searchProducts(query);
	}

	@GetMapping(path = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_GET_PRODUCT')")
	public ProductResponse getById(@PathVariable Integer id) {
		return service.getByIdProduct(id);
	}

	@PutMapping(path = "/update")
	public void updateProduct(@Valid @RequestBody ProductDto dto, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "melumatlarin tamliginda problem var");
		}
		service.update(dto);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok("Product delete successfully!");
	}
}
