package az.developia.e_commerce_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.e_commerce_project.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
