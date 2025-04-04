package az.developia.product_shopping.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class AuthorityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String username;
	private String authority;
}
