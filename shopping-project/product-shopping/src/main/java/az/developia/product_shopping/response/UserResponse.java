package az.developia.product_shopping.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private Integer id;
	private String name;
	private String surname;
	private String username;
	private String email;
}
