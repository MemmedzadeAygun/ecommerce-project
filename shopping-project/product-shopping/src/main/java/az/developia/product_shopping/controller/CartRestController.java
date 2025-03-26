package az.developia.product_shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.product_shopping.dto.CartRequestDto;
import az.developia.product_shopping.response.CartResponse;
import az.developia.product_shopping.service.CartService;

@RestController
@RequestMapping(path = "/cards")
@CrossOrigin(origins = "*")
public class CartRestController {
	
	@Autowired
	private CartService service;

	@PostMapping(path = "/add")
	public ResponseEntity<String> addToCart(@RequestBody CartRequestDto dto){
		service.addToCart(dto);
		
		return ResponseEntity.ok("Product add to cart successfully");
	}
	
	@GetMapping(path = "/getCards")
	public List<CartResponse> getAllCards(){
		return service.getCards();
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<String> updateCart(@RequestBody CartRequestDto dto){
		service.update(dto);
		return ResponseEntity.ok("Cart update successfully");
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteFromCart(@PathVariable Integer id) {
		service.delete(id);
	}
}
