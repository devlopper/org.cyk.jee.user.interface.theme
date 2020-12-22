package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.log.LogHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.__kernel__.session.SessionHelper;
import org.cyk.utility.__kernel__.session.SessionManager;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.client.controller.component.menu.MenuBuilder;
import org.cyk.utility.client.controller.component.menu.MenuBuilderGetter;
import org.cyk.utility.client.controller.component.menu.MenuItem;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilder;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilders;
import org.cyk.utility.client.controller.component.tab.Tab;
import org.cyk.utility.client.controller.component.tab.Tabs;
import org.cyk.utility.client.controller.component.theme.Theme;
import org.cyk.utility.client.controller.component.window.Window;
import org.cyk.utility.client.controller.tag.Tag;
import org.cyk.utility.client.controller.tag.TagForm;
import org.cyk.utility.client.controller.web.ComponentHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractThemeImpl;
import org.cyk.utility.scope.ScopeSession;
import org.primefaces.model.menu.MenuModel;

import lombok.Getter;

public class DesktopDefault extends AbstractThemeImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter protected Tabs leftMenuTabs;	
	@Getter protected Map<Object,Tabs> menuMapKeys = new HashMap<>();
	@Getter protected MenuModel topMenu;
	
	@Override
	public Theme build() {
		Theme theme = super.build();
		Object request = getRequest();
		
		__addTagLinkStyleSheet__("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css");
		
		__addTagScriptResource__(request, "fr_FR.js","js");
		__addTagScriptResource__(request, "nanoscroller.js","js");
		__addTagScriptResource__(request, "layout.js","js");
		__addTagScriptResource__(request, "common.js","js");
		
		__addTagLinkResourceStyleSheet__(request, "nanoscroller.css","css");
		__addTagLinkResourceStyleSheet__(request, "animate.css","css");
		__addTagLinkResourceStyleSheet__(request, "main.css","css");
		__addTagLinkResourceStyleSheet__(request, "primeflex.css","primeflex");
		
		if(color == null)
			color = StringUtils.substringAfter(ConfigurationHelper.getValueAsString(VariableName.USER_INTERFACE_THEME_PRIMEFACES), "atlantis-");
		__addTagLinkResourceStyleSheet__(request, "layout-"+color+".css","css");
		
		Tag tag = __inject__(TagForm.class);
		tag.setIdentifier(__inject__(ComponentHelper.class).getGlobalFormComponentIdentifier());
		tag.getProperties().setEnctype("multipart/form-data");
		mapTags("content.form",tag);
		
		tag = __inject__(TagForm.class);
		tag.setIdentifier("menu-form");
		mapTags("menu.form",tag);
		return theme;
	}
	
	@Override
	protected void __buildMenu__(Object menuMapKey) {
		if(!Boolean.TRUE.equals(__isMenuBuildable__(menuMapKey)))
			return;		
		____buildMenu____(menuMapKey);
		menuMapKeys.put(menuMapKey, leftMenuTabs);
	}
	
	protected Boolean __isMenuBuildable__(Object menuMapKey) {
		Boolean userLoggedPrevious = ValueHelper.defaultToIfNull((Boolean) SessionHelper.getAttributeValue("userlogged"),Boolean.FALSE);
		Boolean userLoggedActual = ValueHelper.defaultToIfNull(SessionManager.getInstance().isUserLogged((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()),Boolean.FALSE);		
		if((!userLoggedPrevious && userLoggedActual) || (userLoggedPrevious && !userLoggedActual)) {
			LogHelper.logInfo(String.format("Building menu with key %s. previously logged : %s , currently logged : %s",menuMapKey,userLoggedPrevious,userLoggedActual), getClass());
			SessionHelper.setAttributeValue("userlogged", userLoggedActual);
			return Boolean.TRUE;
		}
		return menuMapKeys.get(menuMapKey) == null;
	}
	
	protected void ____buildMenu____(Object menuMapKey) {
		Object request = getRequest();
		MenuBuilder menuBuilder = MenuBuilderGetter.getInstance().get(menuMapKey,ScopeSession.class, request);
		if(menuBuilder == null)
			return;
		//TODO reduce build time to maximum 1 second
		leftMenuTabs = __inject__(Tabs.class);
		MenuItemBuilders oldMenuItemBuilders = menuBuilder.getItems();
		if(oldMenuItemBuilders != null) {
			for(MenuItemBuilder index : oldMenuItemBuilders.get()) {
				MenuItemBuilders items = null;
				if(index.getChildren()!=null) {
					items = __inject__(MenuItemBuilders.class);
					for(Object indexChild : index.getChildren())
						if(indexChild instanceof MenuItemBuilder)
							items.add((MenuItemBuilder)indexChild);
					index.getChildren().clear();
				}
				if(index.getRequest() == null)
					index.setRequest(request);
				MenuItem item = index.execute().getOutput();				
				Tab tab = __inject__(Tab.class);
				tab.setProperty(Properties.NAME, item.getCommandable().getName());
				tab.setProperty(Properties.ICON, item.getCommandable().getProperties().getIcon());
				if(items != null)
					tab.setProperty(Properties.MENU,__inject__(MenuBuilder.class).setItems(items).setRequest(request).execute().getOutput());
				leftMenuTabs.add(tab);
			}
		}
	}
	
	@Override
	protected String __getIdentifier__() {
		return ValueHelper.defaultToIfBlank(ConfigurationHelper.getValueAsString(VariableName.USER_INTERFACE_THEME_JSF_CONTRACT),__getIdentifierDefault__());
	}
	
	protected String __getIdentifierDefault__() {
		return "org.cyk.user.interface.theme.web.jsf.primefaces.atlantis.desktop.default";
	}
	
	@Override
	protected String __getTemplateIdentifier__() {
		return "/template/default.xhtml";
	}
	
	@Override
	public Theme process(Window window) {
		__north__(window);
		__center__(window);
		__south__(window);
		return this;
	}
	
	private void __north__(Window window) {
		
	}
	
	private void __center__(Window window) {
		mapViews("center",window.getView());
	}

	private void __south__(Window window) {
		
	}
	
	public String getRoles() {
		return "ROLES";
	}

	public Boolean getIsShowUserMenu() {
		return IS_SHOW_USER_MENU;
	}
	
	public ThemeManager getManager() {
		return ThemeManager.getInstance();
	}

	/**/
	
	public static Boolean IS_SHOW_USER_MENU = Boolean.TRUE;
}