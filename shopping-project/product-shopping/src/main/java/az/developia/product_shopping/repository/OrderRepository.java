package az.developia.product_shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.product_shopping.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{

}
