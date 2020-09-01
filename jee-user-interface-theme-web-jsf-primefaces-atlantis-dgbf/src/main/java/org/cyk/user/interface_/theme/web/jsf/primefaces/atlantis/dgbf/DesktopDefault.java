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
import org.cyk.utility.__kernel__.session.SessionHelper;
import org.cyk.utility.__kernel__.user.interface_.UserInterfaceEventListener;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.__kernel__.variable.VariableHelper;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.bean.Property;
import org.cyk.utility.client.controller.component.menu.Menu;
import org.cyk.utility.client.controller.component.tab.Tab;
import org.cyk.utility.client.controller.component.tab.Tabs;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuElement;

import ci.gouv.dgbf.sib.menu.generator.MenuGenerator;
import ci.gouv.dgbf.sib.menu.generator.api.service.ActeurApiService;
import ci.gouv.dgbf.sib.menu.generator.domain.MenuTab;

public class DesktopDefault extends org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.DesktopDefault implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void ____buildMenu____(Object menuMapKey) {
		if(Boolean.TRUE.equals(DYNAMIC_MENU)) {
			LogHelper.logInfo("Generating dynamic menu identified by "+MENU_IDENTIFIER, getClass());
			List<MenuTab> menuTabs = __inject__(MenuGenerator.class).generateServiceMenu(MENU_IDENTIFIER,SessionHelper.getUserName());
			if(CollectionHelper.isNotEmpty(menuTabs)) {
				MenuTab menuTab = CollectionHelper.getFirst(menuTabs);
				if(menuTab != null) {
					if(CollectionHelper.isNotEmpty(menuTab.getMenuModel().getElements())) {
						MenuElement menuElement = menuTab.getMenuModel().getElements().get(0);
						if(menuElement instanceof DefaultMenuItem) {
							DefaultMenuItem menuItem = (DefaultMenuItem) menuElement;
							menuItem.setOutcome("indexView");
							menuItem.setCommand(null);
							menuItem.setUrl(null);						
						}
					}
				}
			}
			leftMenuTabs = __build__(menuTabs);		
			
			MenuTab topMenuTab = CollectionHelper.getFirst(DependencyInjection.inject(MenuGenerator.class).generateAccountMenu());
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
	
	public static void initialize(Class<?> themeManagerQualifierClass,Class<?> userInterfaceEventListenerQualifierClass) {
		LogHelper.logInfo("Initializing theme...", DesktopDefault.class);		
		DependencyInjection.setQualifierClassTo(ValueHelper.defaultToIfNull(themeManagerQualifierClass, DGBF.class), ThemeManager.class);
		VariableHelper.write(VariableName.SYSTEM_WEB_HOME_URL, ThemeManagerImpl.getSystemLink());		
		MENU_IDENTIFIER = ConfigurationHelper.getValueAsString(VariableName.USER_INTERFACE_THEME_MENU_IDENTIFIER);
		DYNAMIC_MENU = ConfigurationHelper.is(VariableName.USER_INTERFACE_THEME_MENU_IS_DYNAMIC);
		IS_SHOW_USER_MENU = ConfigurationHelper.is(VariableName.SECURITY_AUTHENTICATION_ENABLE);
		if(DYNAMIC_MENU) {
			DependencyInjection.setQualifierClassTo(ValueHelper.defaultToIfNull(userInterfaceEventListenerQualifierClass,DGBF.class), UserInterfaceEventListener.class);
			
			ActeurApiService.HOST = ci.gouv.dgbf.utility.Helper.getApiHost("acteur");
			ActeurApiService.PORT = ci.gouv.dgbf.utility.Helper.getApiPort("acteur");
		}else {
			//SYSTEM_LINK = "#";
		}
		LogHelper.logInfo("Theme initialized", DesktopDefault.class);
	}
	
	public static void initialize() {
		initialize(null, null);
	}
}