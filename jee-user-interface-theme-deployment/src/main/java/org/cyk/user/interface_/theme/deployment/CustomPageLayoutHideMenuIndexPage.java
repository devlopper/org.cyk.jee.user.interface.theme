package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.cyk.utility.client.controller.web.jsf.primefaces.dialog.DialogOpener;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped @Getter @Setter
public class CustomPageLayoutHideMenuIndexPage extends AbstractPageContainerManagedImpl implements Serializable {
	private static final long serialVersionUID = 1L;

	public void showDynamic() {
		DialogOpener.getInstance().open("hidemenudynamic", null, null);
	}
	
	public void showStatic() {
		DialogOpener.getInstance().open("hidemenustatic", null, null);
	}
}
