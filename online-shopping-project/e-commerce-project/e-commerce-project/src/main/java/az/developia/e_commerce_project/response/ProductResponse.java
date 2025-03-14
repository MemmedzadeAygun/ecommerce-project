package az.developia.e_commerce_project.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
	private Integer id;
	private String brand;
	private String model;
	private String category;
	private String description;
	private Double price;
	private Integer rating;
	private String imgUrl;
}
