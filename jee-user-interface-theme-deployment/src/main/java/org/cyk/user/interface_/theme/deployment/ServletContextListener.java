package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf.DesktopDefault;
import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.client.controller.component.menu.MenuBuilderMapInstantiator;
import org.cyk.utility.client.controller.component.theme.ThemeColorGetter;
import org.cyk.utility.client.deployment.AbstractServletContextListener;

import ci.gouv.dgbf.sib.menu.generator.MenuGenerator;
import ci.gouv.dgbf.sib.menu.generator.api.service.MenuGeneratorPortailApiService;

@WebListener
public class ServletContextListener extends AbstractServletContextListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(ServletContext context) {
		super.__initialize__(context);
		DependencyInjection.setQualifierClassTo(CustomTheme.class, MenuBuilderMapInstantiator.class,ThemeColorGetter.class);
		MenuGeneratorPortailApiService.HOST = "10.3.4.17";
		MenuGeneratorPortailApiService.PORT = 32300;
		
		//MenuGenerator.BASE_URL = "http://"+context.getse;
		
		DesktopDefault.IS_SHOW_USER_MENU = Boolean.FALSE;
		DesktopDefault.DYNAMIC_MENU = Boolean.TRUE;
		DesktopDefault.MENU_IDENTIFIER = "COLB";
		//DesktopDefault.initialize();
		//DesktopDefaultImpl.MENU_PATH = "/adminfaces/menu.xhtml";
	}
	
}
