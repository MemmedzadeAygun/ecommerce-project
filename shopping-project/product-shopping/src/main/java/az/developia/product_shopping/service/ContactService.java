package az.developia.product_shopping.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.product_shopping.dto.ContactRequestDto;
import az.developia.product_shopping.entity.ContactEntity;
import az.developia.product_shopping.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public void add(ContactRequestDto dto) {
		ContactEntity entity = new ContactEntity();
		mapper.map(dto, entity);
		repository.save(entity);
	}

}
