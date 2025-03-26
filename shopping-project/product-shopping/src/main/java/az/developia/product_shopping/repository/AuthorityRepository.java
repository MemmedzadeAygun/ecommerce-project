package az.developia.product_shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.product_shopping.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

}
