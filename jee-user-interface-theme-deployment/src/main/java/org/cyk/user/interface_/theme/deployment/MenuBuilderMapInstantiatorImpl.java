package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;
import java.security.Principal;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.component.menu.AbstractMenuBuilderMapInstantiatorImpl;
import org.cyk.utility.client.controller.component.menu.MenuBuilder;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilder;

@CustomTheme
public class MenuBuilderMapInstantiatorImpl extends AbstractMenuBuilderMapInstantiatorImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __instantiateSessionMenuBuilderItems__(Object key,MenuBuilder sessionMenuBuilder, Object request,Principal principal) {
		if("customsessionmenu".equals(key))
			sessionMenuBuilder.addItems(
					__inject__(MenuItemBuilder.class).setCommandableName("Layout AB").setCommandableOutputProperty(Properties.ICON, "fa fa-child")
						.addChild(__inject__(MenuItemBuilder.class).setCommandableName("Index 01"),__inject__(MenuItemBuilder.class).setCommandableName("Index 02")
						,__inject__(MenuItemBuilder.class).setCommandableName("Table"))
					,__inject__(MenuItemBuilder.class).setCommandableName("Layout CB").setCommandableOutputProperty(Properties.ICON, "fa fa-file")
						.addChild(__inject__(MenuItemBuilder.class).setCommandableName("Index")
								,__inject__(MenuItemBuilder.class).setCommandableName("Table"))	
						);
		else
			sessionMenuBuilder.addItems(
				__inject__(MenuItemBuilder.class).setCommandableName("Layout 01").setCommandableOutputProperty(Properties.ICON, "fa fa-child")
					.addChild(__inject__(MenuItemBuilder.class).setCommandableName("Index")
					,__inject__(MenuItemBuilder.class).setCommandableName("Table"))
				,__inject__(MenuItemBuilder.class).setCommandableName("Layout 02").setCommandableOutputProperty(Properties.ICON, "fa fa-file")
					.addChild(__inject__(MenuItemBuilder.class).setCommandableName("Index")
							,__inject__(MenuItemBuilder.class).setCommandableName("Table"))	
					);
	}

}
