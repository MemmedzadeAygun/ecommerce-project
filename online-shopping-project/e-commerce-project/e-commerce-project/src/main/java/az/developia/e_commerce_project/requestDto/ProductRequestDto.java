package az.developia.e_commerce_project.requestDto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
	
	private Integer id;

	@NotBlank(message = "Brand is required")
	@Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
	private String brand;
	
	@NotBlank(message = "Model is required")
	@Size(min = 2, max = 100, message = "Brand must be between 2 and 100 characters")
	private String model;
	
	@NotBlank(message = "Category is required")
	@Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
	private String category;
	
	@NotBlank(message = "Description is required")
	@Size(min = 2, max = 50, message = "Description must be between 2 and 50 characters")
	private String description;
	
	@DecimalMin(value = "0.01", message = "Price must be greater than zero")
    @NotNull(message = "Price is required")
	private Double price;
	
	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating must be at most 5")
	private Integer rating;
	
	 @URL(message = "Image URL must be a valid URL")
	private String imgUrl;
}
