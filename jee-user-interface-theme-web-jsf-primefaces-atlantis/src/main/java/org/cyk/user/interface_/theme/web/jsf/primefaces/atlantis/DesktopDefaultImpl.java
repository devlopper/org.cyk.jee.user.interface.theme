package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.component.menu.MenuBuilder;
import org.cyk.utility.client.controller.component.menu.MenuBuilderMap;
import org.cyk.utility.client.controller.component.menu.MenuBuilderMapGetter;
import org.cyk.utility.client.controller.component.menu.MenuItem;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilder;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilders;
import org.cyk.utility.client.controller.component.tab.Tab;
import org.cyk.utility.client.controller.component.tab.Tabs;
import org.cyk.utility.client.controller.component.theme.Theme;
import org.cyk.utility.client.controller.component.window.Window;
import org.cyk.utility.client.controller.session.SessionAttributeEnumeration;
import org.cyk.utility.client.controller.session.SessionHelper;
import org.cyk.utility.client.controller.tag.Tag;
import org.cyk.utility.client.controller.tag.TagForm;
import org.cyk.utility.client.controller.web.ComponentHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractThemeImpl;
import org.cyk.utility.scope.ScopeSession;

public class DesktopDefaultImpl extends AbstractThemeImpl implements DesktopDefault,Serializable {
	private static final long serialVersionUID = 1L;

	private Tabs menuTabs;
	
	@Override
	public Theme build() {
		Theme theme = super.build();
		Object request = getRequest();
		
		__addTagLinkStyleSheet__("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css");
		
		__addTagScriptResource__(request, "fr_FR.js","js");
		__addTagScriptResource__(request, "nanoscroller.js","js");
		__addTagScriptResource__(request, "layout.js","js");
		__addTagScriptResource__(request, "common.js","js");
		
		__addTagLinkResourceStyleSheet__(request, "main.css","css");
		__addTagLinkResourceStyleSheet__(request, "colors/layout-"+StringUtils.substringAfter(__getConfigurationParameterValue__("primefaces.THEME", null), "atlantis-")+".css","css");
		
		Tag tag = __inject__(TagForm.class);
		tag.setIdentifier(__inject__(ComponentHelper.class).getGlobalFormComponentIdentifier());
		tag.getProperties().setEnctype("multipart/form-data");
		mapTags("content.form",tag);
		
		tag = __inject__(TagForm.class);
		tag.setIdentifier("menu-form");
		mapTags("menu.form",tag);
		
		MenuBuilderMap menuBuilderMap = (MenuBuilderMap) __inject__(SessionHelper.class).getAttributeValue(SessionAttributeEnumeration.MENU_BUILDER_MAP,request);
		if(menuBuilderMap == null)
			__inject__(SessionHelper.class).setAttributeValue(SessionAttributeEnumeration.MENU_BUILDER_MAP
					,menuBuilderMap = __inject__(MenuBuilderMapGetter.class).setRequest(request).execute().getOutput(),request);
					
		//TODO reduce build time to maximum 1 second
		MenuBuilder menuBuilder = menuBuilderMap.get(ScopeSession.class);
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
				getMenuTabs(Boolean.TRUE).add(tab);
			}
		}
		return theme;
	}
	
	@Override
	protected String __getIdentifier__() {
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
	
	@Override
	public Tabs getMenuTabs() {
		return menuTabs;
	}
	
	@Override
	public Tabs getMenuTabs(Boolean injectIfNull) {
		if(menuTabs == null && Boolean.TRUE.equals(injectIfNull))
			menuTabs = __inject__(Tabs.class);
		return menuTabs;
	}
	
	@Override
	public DesktopDefault setMenuTabs(Tabs menuTabs) {
		this.menuTabs = menuTabs;
		return this;
	}

	public Collection<String[]> getUserMenuUlLis() {
		return USER_MENU_UL_LIS;
	}
	
	public String getNames() {
		return "NOMS";
	}
	
	public String getRoles() {
		return "ROLES";
	}
	
	/**/
	
	public static final String FIELD_MENU_TABS = "menuTabs";
	
	public static final Collection<String[]> USER_MENU_UL_LIS = new ArrayList<>();
	public static void addUserMenuUlLi(String name,String url,String icon) {
		USER_MENU_UL_LIS.add(new String[] {name,url,icon});
	}
	static {
		addUserMenuUlLi("Me déconnecter", null,"fa fa-sign-out");
		addUserMenuUlLi("Modifier mon mot de passe", null,"fa fa-key");
		addUserMenuUlLi("Modifier mon profile", null,"fa fa-user");
		addUserMenuUlLi("Mes préférences", null,"fa fa-cog");
		addUserMenuUlLi("Mes visibilités", null,"fa fa-eye");
		addUserMenuUlLi("Demandes de compte utilisateur", null,"fa fa-file-text");
		addUserMenuUlLi("M'abonner à une publication", null,"fa fa-comments");
	}
}
