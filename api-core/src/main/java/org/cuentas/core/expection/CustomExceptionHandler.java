package org.cuentas.core.expection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ CustomException.class })
	public ResponseEntity<CustomInformation> error(CustomException ex) {
		return new ResponseEntity<CustomInformation>(
				new CustomInformation(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getCustomMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
