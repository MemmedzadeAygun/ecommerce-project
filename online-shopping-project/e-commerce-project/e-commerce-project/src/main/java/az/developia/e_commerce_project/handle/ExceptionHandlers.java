package az.developia.e_commerce_project.handle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import az.developia.e_commerce_project.exception.OurRuntimeException;
import az.developia.e_commerce_project.response.ExceptionResponse;
import az.developia.e_commerce_project.response.ValidationResponse;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler
	public ExceptionResponse handle(OurRuntimeException exc) {
		ExceptionResponse response = new ExceptionResponse();

		BindingResult br = exc.getBr();

		if (br == null) {

		} else {
			List<FieldError> fieldErrors = br.getFieldErrors();
			List<ValidationResponse> validations = new ArrayList<ValidationResponse>();

			for (FieldError fieldError : fieldErrors) {
				ValidationResponse validation = new ValidationResponse();
				validation.setField(fieldError.getField());
				validation.setMessage(fieldError.getDefaultMessage());
				validations.add(validation);
			}

			response.setValidations(validations);
		}

		response.setMessage(exc.getMessage());

		return response;
	}
}
