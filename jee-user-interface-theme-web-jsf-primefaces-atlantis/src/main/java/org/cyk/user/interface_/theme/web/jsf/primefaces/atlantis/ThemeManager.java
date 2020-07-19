package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model.TabMenu;
import org.cyk.utility.__kernel__.Helper;
import org.cyk.utility.__kernel__.object.__static__.identifiable.AbstractObject;
import org.cyk.utility.__kernel__.value.Value;
import org.cyk.utility.client.controller.component.tab.Tab;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

public interface ThemeManager {

	TabMenu getLeftTabMenu(AbstractPageContainerManagedImpl page);
	Collection<Tab> getLeftMenuTabs(AbstractPageContainerManagedImpl page);
	
	String getApplicationName(AbstractPageContainerManagedImpl page);
	
	/**/
	
	public static abstract class AbstractImpl extends AbstractObject implements ThemeManager,Serializable {
		
		@Override
		public Collection<Tab> getLeftMenuTabs(AbstractPageContainerManagedImpl page) {
			if(page == null)
				return null;
			return __getLeftMenuTabs__(page);
		}
		
		protected Collection<Tab> __getLeftMenuTabs__(AbstractPageContainerManagedImpl page) {
			return ((DesktopDefault) page.getWindow().getTheme()).getLeftMenuTabs().get();
		}
		
		@Override
		public TabMenu getLeftTabMenu(AbstractPageContainerManagedImpl page) {
			if(page == null)
				return null;
			return __getLeftTabMenu__(page);
		}
		
		protected TabMenu __getLeftTabMenu__(AbstractPageContainerManagedImpl page) {
			return null;
		}
		
		@Override
		public String getApplicationName(AbstractPageContainerManagedImpl page) {
			if(page == null)
				return null;
			return __getApplicationName__(page);
		}
		
		protected String __getApplicationName__(AbstractPageContainerManagedImpl page) {
			return page.getWindow().getOutputStringText("applicationName").getValue();
		}
	}
	
	/**/
	
	static ThemeManager getInstance() {
		return Helper.getInstance(ThemeManager.class, INSTANCE);
	}
	
	Value INSTANCE = new Value();	
}