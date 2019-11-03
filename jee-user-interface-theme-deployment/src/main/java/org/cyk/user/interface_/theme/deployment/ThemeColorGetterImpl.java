package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;

import org.cyk.utility.client.controller.component.window.WindowContainerManaged;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractThemeColorGetterImpl;

@CustomTheme
public class ThemeColorGetterImpl extends AbstractThemeColorGetterImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Object __getByWindowContainerManaged__(Class<? extends WindowContainerManaged> klass) {
		if(CustomThemeColorBluePage.class.equals(klass))
			return "blue";
		if(CustomThemeColorOrangePage.class.equals(klass))
			return "orange";
		if(CustomThemeColorGreenPage.class.equals(klass))
			return "green";
		return super.__getByWindowContainerManaged__(klass);
	}
	
}
