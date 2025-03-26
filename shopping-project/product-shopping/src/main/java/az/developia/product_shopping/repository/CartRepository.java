package az.developia.product_shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.product_shopping.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer>{

	List<CartEntity> findAllByCustomerId(Integer userId);

}
