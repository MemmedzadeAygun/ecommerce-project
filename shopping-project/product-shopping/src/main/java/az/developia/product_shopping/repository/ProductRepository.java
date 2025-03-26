package az.developia.product_shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.product_shopping.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

	List<ProductEntity> findAllByCustomerId(Integer userId);


}
