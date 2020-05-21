package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class DashboardIndexPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Collection<String> boxes = List.of("One","Two","Three","Four","Five","Six","Seven","Height","Nine","Ten");
	
	@Override
	protected String __getWindowTitleValue__() {
		return "Dashboard";
	}
	
}
