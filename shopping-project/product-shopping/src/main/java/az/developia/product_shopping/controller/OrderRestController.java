package az.developia.product_shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.product_shopping.dto.OrderRequestDto;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.service.OrderService;

@RestController
@RequestMapping(path = "/orders")
@CrossOrigin(origins = "*")
public class OrderRestController {
	
	@Autowired
	private OrderService service;

	@PostMapping(path = "/add")
	public ResponseEntity<String> order(@Valid @RequestBody OrderRequestDto dto, BindingResult br){
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "Melumatlarin tamliginda problem var");
		}
		service.addToOrder(dto);
		return ResponseEntity.ok("Order was complated successfully");
	}
}
