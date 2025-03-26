package az.developia.product_shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.product_shopping.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
