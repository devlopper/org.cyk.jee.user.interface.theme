package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;
import java.security.Principal;

import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.client.controller.component.menu.AbstractMenuBuilderMapGetterImpl;
import org.cyk.utility.client.controller.component.menu.MenuBuilder;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilder;

@CustomTheme
public class MenuBuilderMapGetterImpl extends AbstractMenuBuilderMapGetterImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void ____executePrincipalIsNotNull____(MenuBuilder sessionMenuBuilder, Object request, Principal principal) throws Exception {
		
	}

	@Override
	protected void ____executePrincipalIsNull____(MenuBuilder sessionMenuBuilder, Object request) throws Exception {
		sessionMenuBuilder.addItems(
				__inject__(MenuItemBuilder.class).setCommandableName("Layout").setCommandableOutputProperty(Properties.ICON, "fa fa-child")
					.addChild(__inject__(MenuItemBuilder.class).setCommandableName("Index")
					,__inject__(MenuItemBuilder.class).setCommandableName("Table"))
					);
	}

}
