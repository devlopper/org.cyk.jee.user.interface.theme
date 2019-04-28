package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis;

import org.cyk.utility.client.controller.component.tab.Tabs;
import org.cyk.utility.client.controller.web.jsf.primefaces.Theme;

public interface DesktopDefault extends Theme {

	Tabs getMenuTabs();
	Tabs getMenuTabs(Boolean injectIfNull);
	DesktopDefault setMenuTabs(Tabs menuTabs);
	
}
