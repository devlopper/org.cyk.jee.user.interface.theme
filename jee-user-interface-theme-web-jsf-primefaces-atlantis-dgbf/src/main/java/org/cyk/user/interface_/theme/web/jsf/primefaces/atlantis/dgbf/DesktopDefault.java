package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf;

import java.io.Serializable;
import java.util.List;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.ThemeManager;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf.annotation.DGBF;
import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.log.LogHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.user.interface_.UserInterfaceEventListener;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.bean.Property;
import org.cyk.utility.client.controller.component.menu.Menu;
import org.cyk.utility.client.controller.component.tab.Tab;
import org.cyk.utility.client.controller.component.tab.Tabs;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuElement;

import ci.gouv.dgbf.sib.menu.generator.MenuGenerator;
import ci.gouv.dgbf.sib.menu.generator.domain.MenuTab;

public class DesktopDefault extends org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.DesktopDefault implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void ____buildMenu____(Object menuMapKey) {
		if(Boolean.TRUE.equals(DYNAMIC_MENU)) {
			LogHelper.logInfo("Generating dynamic menu identified by "+MENU_IDENTIFIER, getClass());
			leftMenuTabs = __build__(DependencyInjection.inject(MenuGenerator.class).generateServiceMenu(MENU_IDENTIFIER));		
			MenuTab topMenuTab = CollectionHelper.getFirst(DependencyInjection.inject(MenuGenerator.class).generateServiceMenu(MENU_OWNER_IDENTIFIER));
			if(topMenuTab != null) {
				topMenu = topMenuTab.getMenuModel();
				if(topMenu != null && CollectionHelper.isNotEmpty(topMenu.getElements())) {
					MenuElement menuElement = topMenu.getElements().get(0);
					if(menuElement instanceof DefaultMenuItem) {
						DefaultMenuItem menuItem = (DefaultMenuItem) menuElement;
						menuItem.setCommand("#{userInterfaceController.logout}");
						menuItem.setUrl(null);
						menuItem.setHref(null);
					}
				}
			}
		}else {
			super.____buildMenu____(menuMapKey);
			if(Boolean.TRUE.equals(IS_SHOW_USER_MENU)) {
				topMenu = new DefaultMenuModel();
				DefaultMenuItem menuItem = new DefaultMenuItem("Se déconnecter","fa fa-log-out");
				topMenu.addElement(menuItem);
				menuItem.setCommand("#{userInterfaceController.logout}");
				menuItem.setUrl(null);
				menuItem.setHref(null);
			}		
		}		
	}
	
	private Tabs __build__(List<MenuTab> tabs) {
		if(CollectionHelper.isEmpty(tabs))
			return null;
		Tabs __tabs__ = __inject__(Tabs.class);
		for(MenuTab index : tabs) {
			Tab tab = __inject__(Tab.class);
			tab.setProperty(Properties.NAME, index.getTitle());
			tab.setProperty(Properties.ICON, index.getIcon());
			if(index.getMenuModel() != null) {
				Menu menu = __inject__(Menu.class);
				menu.setTargetModel(__inject__(Property.class));
				menu.getTargetModel().setValue(index.getMenuModel());
				menu.getTargetModel().setIsDerived(Boolean.TRUE);
				tab.setProperty(Properties.MENU,menu);
			}
			__tabs__.add(tab);
		}
		return __tabs__;
	}
	
	@Override
	protected String __getIdentifierDefault__() {
		return "org.cyk.user.interface.theme.web.jsf.primefaces.atlantis.dgbf.desktop.default";
	}
	
	/**/
	
	/**/
	
	public static Boolean DYNAMIC_MENU = Boolean.TRUE;	
	//public static String SYSTEM_NAME = "SIIBC";
	public static String SYSTEM_NAME = "SIGOBE";
	public static String SYSTEM_LINK = "SYSTEM_WEB_HOME_URL";
	public static String MENU_IDENTIFIER = "MENU_IDENTIFIER";
	public static String MENU_OWNER_IDENTIFIER = "SIIBC-MYOWNER";
	
	public static void initialize() {
		LogHelper.logInfo("Initializing theme...", DesktopDefault.class);
		
		DependencyInjection.setQualifierClassTo(DGBF.class, ThemeManager.class);
				
		//SYSTEM_NAME = ConfigurationHelper.getValueAsString("SIIBC_NAME",null,null,"SIGOBE");
		MENU_IDENTIFIER = ConfigurationHelper.getValueAsString(VariableName.USER_INTERFACE_THEME_MENU_IDENTIFIER);
		DYNAMIC_MENU = ConfigurationHelper.is(VariableName.USER_INTERFACE_THEME_MENU_IS_DYNAMIC);
		IS_SHOW_USER_MENU = ConfigurationHelper.is(VariableName.SECURITY_AUTHENTICATION_ENABLE);
		//SYSTEM_LINK = ConfigurationHelper.getValueAsString(VariableName.SYSTEM_WEB_HOME_URL,null,null,"http://siib.dgbf.ci");
		if(DYNAMIC_MENU) {
			DependencyInjection.setQualifierClassTo(DGBF.class, UserInterfaceEventListener.class);
		}else {
			//SYSTEM_LINK = "#";
		}
		LogHelper.logInfo("Theme initialized", DesktopDefault.class);
	}
}