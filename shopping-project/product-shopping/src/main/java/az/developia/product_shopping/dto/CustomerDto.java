package az.developia.product_shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	private Integer id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
}
