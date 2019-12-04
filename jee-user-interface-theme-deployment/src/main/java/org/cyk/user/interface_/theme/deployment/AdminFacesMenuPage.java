package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.utility.client.controller.component.menu.Menu;
import org.cyk.utility.client.controller.component.menu.MenuBuilder;
import org.cyk.utility.client.controller.component.menu.MenuItemBuilder;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class AdminFacesMenuPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	private Menu menu;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		MenuBuilder menuBuilder = __inject__(MenuBuilder.class);
		menuBuilder.addItems(__inject__(MenuItemBuilder.class).setCommandableName("I1"));
		menu = menuBuilder.execute().getOutput();
	}
	
}
