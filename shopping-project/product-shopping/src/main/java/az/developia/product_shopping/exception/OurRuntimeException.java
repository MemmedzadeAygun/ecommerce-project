package az.developia.product_shopping.exception;

import org.springframework.validation.BindingResult;

public class OurRuntimeException extends RuntimeException {

	BindingResult br;

	public OurRuntimeException(BindingResult br, String message) {
		super(message);
		this.br = br;
	}

	public BindingResult getBr() {
		return br;
	}
}
