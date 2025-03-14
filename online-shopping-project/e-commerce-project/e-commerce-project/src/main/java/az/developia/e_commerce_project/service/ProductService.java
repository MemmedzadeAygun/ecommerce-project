package az.developia.e_commerce_project.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.e_commerce_project.entity.ProductEntity;
import az.developia.e_commerce_project.exception.OurRuntimeException;
import az.developia.e_commerce_project.repository.ProductRepository;
import az.developia.e_commerce_project.requestDto.ProductRequestDto;

@Service
public class ProductService { 

	@Autowired
	private ProductRepository repository;
	
	
	public ProductEntity add(ProductEntity product) {
		return repository.save(product);
	}
	

	public List<ProductEntity> getAll() {
		return repository.findAll();
	}
	

	public ProductEntity getById(Integer id) {
		Optional<ProductEntity> byId = repository.findById(id);
		
		if (id==null || id==0) {
			throw new OurRuntimeException(null, "id mutleqdir");
		}
		if (byId.isPresent()) {
			return byId.get();
		}else {
			throw new OurRuntimeException(null, "id tapilmadi");
		}
		
	}


	public void update(ProductRequestDto dto) {
		if (dto.getId()==null || dto.getId()<=0) {
			throw new OurRuntimeException(null, "ID mütləqdir və 0-dan böyük olmalıdır");
		}
		
		ProductEntity existingProduct = repository.findById(dto.getId())
				.orElseThrow(() -> new OurRuntimeException(null, "id tapilmadi"));
		
		existingProduct.setBrand(dto.getBrand());
		existingProduct.setModel(dto.getModel());
		existingProduct.setCategory(dto.getCategory());
		existingProduct.setDescription(dto.getDescription());
		existingProduct.setPrice(dto.getPrice());
		existingProduct.setRating(dto.getRating());
		existingProduct.setImgUrl(dto.getImgUrl());
		
		repository.save(existingProduct);
	}


	public void delete(Integer id) {
		if (id==null || id<0) {
			throw new OurRuntimeException(null, "id mutleqdir");
		}
		
		Optional<ProductEntity> byId = repository.findById(id);
		if (byId.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new OurRuntimeException(null, "id tapilmadi");
		}
	}

}
