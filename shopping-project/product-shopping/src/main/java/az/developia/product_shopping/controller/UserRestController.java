package az.developia.product_shopping.controller;

import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.product_shopping.dto.CustomerDto;
import az.developia.product_shopping.entity.AuthorityEntity;
import az.developia.product_shopping.entity.CustomerEntity;
import az.developia.product_shopping.entity.UserEntity;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.repository.AuthorityRepository;
import az.developia.product_shopping.repository.CustomerRepository;
import az.developia.product_shopping.repository.UserRepository;
import az.developia.product_shopping.response.UserResponse;
import az.developia.product_shopping.service.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private UserService service;

	@PostMapping(path = "/customer")
	public void createCustomer(@RequestBody CustomerDto dto) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (userRepository.findById(dto.getUsername()).isPresent()) {
			throw new OurRuntimeException(null, "bu username artiq movcuddur");
		}

		CustomerEntity customer = new CustomerEntity();
		customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setSurname(dto.getSurname());
		customer.setUsername(dto.getUsername());
		customerRepository.save(customer);

		UserEntity user = new UserEntity();
		user.setUsername(dto.getUsername());
		String raw = dto.getPassword();
		String password = "{bcrypt}" + encoder.encode(raw);
		user.setPassword(password);
		user.setEnabled(1);
		user.setEmail(dto.getEmail());
		user.setType("customer");
		userRepository.save(user);
		
		List<String> authorities = Arrays.asList(
				"ROLE_ADD_PRODUCT",
				"ROLE_GET_PRODUCT",
				"ROLE_UPDATE_PRODUCT",
				"ROLE_DELETE_PRODUCT"
		);

		for (String auth : authorities) {
			AuthorityEntity a1 = new AuthorityEntity();
			a1.setUsername(dto.getUsername());
			a1.setAuthority(auth);
			authorityRepository.save(a1);
		}
		
	}

	@GetMapping(path = "/me")
	public ResponseEntity<UserResponse> getMyProfile(@RequestHeader("Authorization") String authHeader) {
		String base64Credentials = authHeader.substring("Basic ".length());
		byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		String credentials = new String(credDecoded, StandardCharsets.UTF_8);

		final String[] values = credentials.split(":", 2);
		String username = values[0];

		UserResponse userDetails = service.getUserDetails(username);
		return ResponseEntity.ok(userDetails);

	}

	@GetMapping(path = "/login")
	public void login() {

	}
}
