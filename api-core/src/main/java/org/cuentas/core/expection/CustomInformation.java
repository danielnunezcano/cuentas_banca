package org.cuentas.core.expection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomInformation {
	private Integer error;
	private String description;
}
