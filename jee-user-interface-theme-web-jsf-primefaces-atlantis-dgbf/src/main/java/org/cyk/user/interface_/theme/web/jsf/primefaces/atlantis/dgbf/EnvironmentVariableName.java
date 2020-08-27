package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum EnvironmentVariableName {

	ENVIRONMENT("SIIB_ENVIRONMENT")
	,NAME("SIIBC_NAME")
	
	;
	
	private String value;
}
