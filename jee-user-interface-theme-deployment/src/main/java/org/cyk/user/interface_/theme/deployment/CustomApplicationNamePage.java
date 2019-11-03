package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.utility.client.controller.component.window.WindowBuilder;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class CustomApplicationNamePage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected String __getWindowTitleValue__() {
		return "Custom application name";
	}
	
	@Override
	protected WindowBuilder __getWindowBuilder__(List<String> subDurations) {
		WindowBuilder windowBuilder = super.__getWindowBuilder__(subDurations);
		windowBuilder.getApplicationName(Boolean.TRUE).setValue("CUSTOM APP NAME");
		return windowBuilder;
	}
}
