package az.developia.product_shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.product_shopping.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{

}
