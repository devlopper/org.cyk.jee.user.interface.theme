package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf;

import java.io.Serializable;
import java.util.List;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.bean.Property;
import org.cyk.utility.client.controller.component.menu.Menu;
import org.cyk.utility.client.controller.component.tab.Tab;
import org.cyk.utility.client.controller.component.tab.Tabs;

import ci.gouv.dgbf.sib.menu.generator.MenuGenerator;
import ci.gouv.dgbf.sib.menu.generator.domain.MenuTab;

public class DesktopDefault extends org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.DesktopDefault implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void ____buildMenu____(Object menuMapKey) {
		leftMenuTabs = __build__(DependencyInjection.inject(MenuGenerator.class).generateServiceMenu("SIIBC-ACTEUR"));		
		MenuTab topMenuTab = CollectionHelper.getFirst(DependencyInjection.inject(MenuGenerator.class).generateServiceMenu("SIIBC-MYOWNER"));
		if(topMenuTab != null)
			topMenu = topMenuTab.getMenuModel();
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
}
