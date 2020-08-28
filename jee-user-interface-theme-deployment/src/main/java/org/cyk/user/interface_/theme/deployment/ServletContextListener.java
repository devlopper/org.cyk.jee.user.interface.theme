package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.ThemeManager;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf.DesktopDefault;
import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.__kernel__.variable.VariableHelper;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.client.controller.component.menu.MenuBuilderMapInstantiator;
import org.cyk.utility.client.controller.component.theme.ThemeColorGetter;
import org.cyk.utility.client.deployment.AbstractServletContextListener;

import ci.gouv.dgbf.sib.menu.generator.api.service.MenuGeneratorPortailApiService;
import ci.gouv.dgbf.utility.Actor;

@WebListener
public class ServletContextListener extends AbstractServletContextListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(ServletContext context) {
		super.__initialize__(context);
		DependencyInjection.setQualifierClassTo(CustomTheme.class, MenuBuilderMapInstantiator.class,ThemeColorGetter.class,ThemeManager.class);
		
		Actor.HOST = "10.3.4.17";
		Actor.PORT = 30055;
		
		MenuGeneratorPortailApiService.HOST = "10.3.4.17";
		MenuGeneratorPortailApiService.PORT = 32300;
		VariableHelper.write(VariableName.USER_INTERFACE_THEME_MENU_IDENTIFIER, "SIIBC-ACTEUR");
		VariableHelper.write(VariableName.USER_INTERFACE_THEME_MENU_IS_DYNAMIC, Boolean.TRUE);		
		DesktopDefault.initialize();
		
		//DesktopDefaultImpl.MENU_PATH = "/adminfaces/menu.xhtml";
	}
	
}
