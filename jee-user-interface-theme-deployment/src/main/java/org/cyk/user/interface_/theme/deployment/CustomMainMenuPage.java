package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class CustomMainMenuPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected Object __getMenuBuilderMapKey__() {
		return "customsessionmenu";
	}
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Custom menus";
	}
}
