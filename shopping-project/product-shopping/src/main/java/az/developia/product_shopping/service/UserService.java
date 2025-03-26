package az.developia.product_shopping.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.product_shopping.entity.CustomerEntity;
import az.developia.product_shopping.entity.UserEntity;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.repository.CustomerRepository;
import az.developia.product_shopping.repository.UserRepository;
import az.developia.product_shopping.response.UserResponse;

@Service
public class UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	public UserResponse getUserDetails(String username) {
		Optional<UserEntity> userEntityOptional = userRepository.findById(username);
		Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUsername(username);

		if (userEntityOptional.isPresent() && customerEntityOptional.isPresent()) {
			UserEntity userEntity = userEntityOptional.get();
			CustomerEntity customerEntity = customerEntityOptional.get();

			return new UserResponse(customerEntity.getId(), customerEntity.getName(), customerEntity.getSurname(),
					userEntity.getUsername(), userEntity.getEmail());
		} else {
			throw new OurRuntimeException(null, "User not found");
		}
	}

}
