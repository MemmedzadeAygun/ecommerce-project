package az.developia.product_shopping.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private CartEntity cart;
	private String firstName;
	private String lastName;
	private String state;
	private String city;
	private String address;
	private Integer zipCode;
	private String phone;
	private String email;
	private String cardNumber;
	private Integer expiryMonth;
	private Integer expiryYear;
	private Integer securityCode;
}
