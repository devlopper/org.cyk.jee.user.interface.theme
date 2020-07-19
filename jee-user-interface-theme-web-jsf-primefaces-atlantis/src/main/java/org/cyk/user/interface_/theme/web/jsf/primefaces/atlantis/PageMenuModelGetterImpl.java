package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis;
import java.io.Serializable;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.annotation.Atlantis;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;
import org.cyk.utility.client.controller.web.jsf.primefaces.page.PageMenuModelGetter;
import org.primefaces.model.menu.MenuModel;

@Atlantis
public class PageMenuModelGetterImpl extends PageMenuModelGetter.AbstractImpl implements Serializable {

	@Override
	public MenuModel get(AbstractPageContainerManagedImpl page) {
		// TODO Auto-generated method stub
		return super.get(page);
	}
	
}