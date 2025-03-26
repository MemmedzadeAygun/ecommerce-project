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

import az.developia.product_shopping.dto.ContactRequestDto;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.service.ContactService;

@RestController
@RequestMapping(path = "/contact")
@CrossOrigin(origins = "*")
public class ContactRestController {
	
	@Autowired
	private ContactService service;

	@PostMapping
	public ResponseEntity<String> contactUs(@Valid @RequestBody ContactRequestDto dto, BindingResult br){
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "melumatlarin tamligi pozulub");
		}
		
		service.add(dto);
		return ResponseEntity.ok("Complated succesffully");
	}
}
