package az.developia.product_shopping.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.product_shopping.dto.OrderRequestDto;
import az.developia.product_shopping.entity.CartEntity;
import az.developia.product_shopping.entity.OrderEntity;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.repository.CartRepository;
import az.developia.product_shopping.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ModelMapper mapper;

	public void addToOrder(OrderRequestDto dto) {
		CartEntity cart = cartRepository.findById(dto.getCartId())
				.orElseThrow(() -> new OurRuntimeException(null, "cart not found"));
		
		OrderEntity order = new OrderEntity();
		order.setCart(cart);
		mapper.map(dto, order);
		orderRepository.save(order);
	}

}
