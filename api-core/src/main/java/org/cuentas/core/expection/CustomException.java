package org.cuentas.core.expection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = -5239168663390336970L;
	private String customMessage;

}
