package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis;

import java.io.Serializable;
import java.util.Collection;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model.TabMenu;
import org.cyk.utility.__kernel__.Helper;
import org.cyk.utility.__kernel__.object.__static__.identifiable.AbstractObject;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.__kernel__.value.Value;
import org.cyk.utility.client.controller.component.tab.Tab;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.primefaces.model.menu.MenuModel;

public interface ThemeManager {

	TabMenu getLeftTabMenu(AbstractPageContainerManagedImpl page);
	Collection<Tab> getLeftMenuTabs(AbstractPageContainerManagedImpl page);
	
	MenuModel getTopMenuModel(AbstractPageContainerManagedImpl page);
	
	Boolean getIsShowUser(AbstractPageContainerManagedImpl page);
	
	String getApplicationName(AbstractPageContainerManagedImpl page);
	String getApplicationOutcome(AbstractPageContainerManagedImpl page);
	String getUserNames(AbstractPageContainerManagedImpl page);
	
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
		public MenuModel getTopMenuModel(AbstractPageContainerManagedImpl page) {
			if(page == null)
				return null;
			return __getTopMenuModel__(page);
		}
		
		protected MenuModel __getTopMenuModel__(AbstractPageContainerManagedImpl page) {
			return ((DesktopDefault) page.getWindow().getTheme()).getTopMenu();
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
		
		@Override
		public String getApplicationOutcome(AbstractPageContainerManagedImpl page) {
			if(page == null)
				return null;
			return __getApplicationOutcome__(page);
		}
		
		protected String __getApplicationOutcome__(AbstractPageContainerManagedImpl page) {
			return "indexView";
		}
		
		@Override
		public Boolean getIsShowUser(AbstractPageContainerManagedImpl page) {
			if(page == null || page.getSession() == null || page.getSession().getUser() == null /*&& StringHelper.isNotBlank(page.getSession().getUser().)*/)
				return null;
			return __getIsShowUser__(page);
		}
		
		protected Boolean __getIsShowUser__(AbstractPageContainerManagedImpl page) {
			return ((DesktopDefault) page.getWindow().getTheme()).getIsShowUserMenu();
		}
		
		@Override
		public String getUserNames(AbstractPageContainerManagedImpl page) {
			if(page == null || page.getSession() == null || page.getSession().getUser() == null /*&& StringHelper.isNotBlank(page.getSession().getUser().)*/)
				return null;
			return __getUserNames__(page);
		}
		
		protected String __getUserNames__(AbstractPageContainerManagedImpl page) {
			if(StringHelper.isBlank(page.getSession().getUser().getNames()))
				return page.getSession().getUser().getName();
			return page.getSession().getUser().getNames();
		}
	}
	
	/**/
	
	static ThemeManager getInstance() {
		return Helper.getInstance(ThemeManager.class, INSTANCE);
	}
	
	Value INSTANCE = new Value();	
}