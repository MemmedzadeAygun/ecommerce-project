package az.developia.product_shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDto {

	private Integer id;
	private Integer productId;
	private Integer quantity;
	
	private Integer customerId;
}
