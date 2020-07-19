package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named @ApplicationScoped
public class ThemeController implements Serializable {

	private ThemeManager manager;
	
	public ThemeManager getManager() {
		if(manager == null)
			manager = ThemeManager.getInstance();
		return manager;
	}
}
