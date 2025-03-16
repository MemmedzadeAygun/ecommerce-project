package az.developia.e_commerce_project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.e_commerce_project.entity.AuthorityEntity;
import az.developia.e_commerce_project.entity.UserDetailEntity;
import az.developia.e_commerce_project.entity.UserEntity;
import az.developia.e_commerce_project.exception.OurRuntimeException;
import az.developia.e_commerce_project.repository.AuthorityRepository;
import az.developia.e_commerce_project.repository.UserDetailRepsository;
import az.developia.e_commerce_project.repository.UserRepsository;
import az.developia.e_commerce_project.requestDto.UserDetailDto;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class UserRestController {
	
	@Autowired
	private UserDetailRepsository userDetailRepsository;
	
	@Autowired
	private UserRepsository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepsository;

	@PostMapping(path = "/user")
	public void createUserDetail(@RequestBody UserDetailDto dto) {
		
		Optional<UserEntity> findById = userRepository.findById(dto.getUsername());
		if (findById.isPresent()) {
			throw new OurRuntimeException(null, "bu istifadeci artiq movcuddur");
		}
		
		UserDetailEntity userDetail=new UserDetailEntity();
		userDetail.setId(dto.getId());
		userDetail.setName(dto.getName());
		userDetail.setSurname(dto.getSurname());
		userDetail.setUsername(dto.getUsername());
		userDetailRepsository.save(userDetail);
		
		UserEntity user=new UserEntity();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		userRepository.save(user);
		
		
		AuthorityEntity a=new AuthorityEntity();
		a.setUsername(dto.getUsername());
		a.setAuthority("ROLE_ADD_PRODUCT");
		authorityRepsository.save(a);
	}
	
	
	@GetMapping(path = "/login")
	public void login() {
		
	}
}
