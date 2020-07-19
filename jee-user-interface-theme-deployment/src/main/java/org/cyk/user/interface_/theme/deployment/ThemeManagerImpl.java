package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.ThemeManager;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model.Menu;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model.Tab;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model.TabMenu;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@CustomTheme
public class ThemeManagerImpl extends ThemeManager.AbstractImpl implements Serializable {

	private TabMenu tabMenu;
	
	@Override
	public TabMenu getLeftTabMenu(AbstractPageContainerManagedImpl page) {
		if(page instanceof CustomMainMenuUsingTabMenuPage) {
			if(tabMenu == null)
				tabMenu = TabMenu.build(TabMenu.ConfiguratorImpl.FIELD_TABS_NAMES,List.of("Tab01","SecondTab","The Third One")
					,TabMenu.FIELD_LISTENER,new TabMenu.Listener.AbstractImpl() {
					@Override
					public Map<Object, Object> getTabArguments(String name) {
						Map<Object, Object> arguments = super.getTabArguments(name);
						if("SecondTab".equals(name)) {
							arguments.put(Tab.FIELD_ICON, "fa fa-file");
							MenuModel model = new DefaultMenuModel();
							model.addElement(new DefaultMenuItem("Action01"));
							model.addElement(new DefaultMenuItem("Action02"));
							arguments.put(Tab.FIELD_MENU, Menu.build(Menu.FIELD_MODEL,model));
						}
						return arguments;
					}
				});
			return tabMenu;
		}
		return super.getLeftTabMenu(page);
	}
	
}