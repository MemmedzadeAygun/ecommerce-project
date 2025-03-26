package az.developia.product_shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.product_shopping.entity.CustomerEntity;
import az.developia.product_shopping.entity.UserEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

	Optional<CustomerEntity> findByUsername(String username);

	CustomerEntity getUserByUsername(String username);

}
