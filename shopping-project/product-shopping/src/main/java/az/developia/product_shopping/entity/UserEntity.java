package az.developia.product_shopping.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
	@Id
	private String username;
	private String password;
	private Integer enabled;
	private String type;
	private String email;
}
