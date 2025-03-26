package az.developia.product_shopping.response;

import az.developia.product_shopping.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
	
	private Integer id;
	
	private ProductEntity product;
	
	private Integer quantity;
	
	private double subTotal;
}
