package az.developia.product_shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.product_shopping.dto.CartRequestDto;
import az.developia.product_shopping.entity.CartEntity;
import az.developia.product_shopping.entity.CustomerEntity;
import az.developia.product_shopping.entity.ProductEntity;
import az.developia.product_shopping.exception.OurRuntimeException;
import az.developia.product_shopping.repository.CartRepository;
import az.developia.product_shopping.repository.CustomerRepository;
import az.developia.product_shopping.repository.ProductRepository;
import az.developia.product_shopping.response.CartResponse;

@Service
public class CartService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	public void addToCart(CartRequestDto request) {
		ProductEntity product = productRepository.findById(request.getProductId())
				.orElseThrow(() -> new OurRuntimeException(null, "product not found"));
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		CustomerEntity userByUsername = customerRepository.getUserByUsername(username);
		Integer id = userByUsername.getId();
		

		Integer quantity = (request.getQuantity() == null || request.getQuantity() <= 0) ? 1 : request.getQuantity();

		CartEntity cart = new CartEntity();
		cart.setProduct(product);
		cart.setQuantity(quantity);

		Double subTotal = product.getPrice() * quantity;

		cart.setSubTotal(subTotal);
		cart.setCustomerId(id);
		cartRepository.save(cart);
	}
	

	public List<CartResponse> getCards() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		CustomerEntity userByUsername = customerRepository.getUserByUsername(username);
		Integer userId=userByUsername.getId();
		
		List<CartEntity> cards = cartRepository.findAllByCustomerId(userId);

		return cards.stream().map(card -> mapper.map(card, CartResponse.class)).collect(Collectors.toList());
	}
	

	public void update(CartRequestDto dto) {
		CartEntity cart = cartRepository.findById(dto.getId())
				.orElseThrow(() -> new OurRuntimeException(null, "Cart not found"));

		Integer quantity = (dto.getQuantity() == null || dto.getQuantity() <= 0) ? 1 : dto.getQuantity();
		
		cart.setQuantity(quantity);

		Double subTotal = cart.getProduct().getPrice() * quantity;

		cart.setSubTotal(subTotal);
		cartRepository.save(cart);
	}

	public void delete(Integer id) {
		if (id==null || id<=0) {
			throw new OurRuntimeException(null, "id absolute");
		}
		if (cartRepository.findById(id).isPresent()) {
			cartRepository.deleteById(id);
		}else {
			throw new OurRuntimeException(null, "id not found");
		}
	}

}
