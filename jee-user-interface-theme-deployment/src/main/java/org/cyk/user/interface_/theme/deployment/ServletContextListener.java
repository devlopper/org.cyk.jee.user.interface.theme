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
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractServletContextListener;

import ci.gouv.dgbf.utility.Helper;

@WebListener
public class ServletContextListener extends AbstractServletContextListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void __initialize__(ServletContext context) {
		super.__initialize__(context);
		DependencyInjection.setQualifierClassTo(CustomTheme.class, MenuBuilderMapInstantiator.class,ThemeColorGetter.class,ThemeManager.class);
		
		//Actor.HOST = "10.3.4.17";
		//Actor.PORT = 30055;
		
		/*
		VariableHelper.write(VariableName.USER_INTERFACE_THEME_MENU_IS_DYNAMIC, Boolean.TRUE);
		MenuGeneratorPortailApiService.HOST = "10.3.4.17";
		MenuGeneratorPortailApiService.PORT = 32300;
		*/
		
		VariableHelper.write(Helper.getApiHostVariableName("acteur"),"localhost");
		VariableHelper.write(Helper.getApiPortVariableName("acteur"),"3000");
		
		//ActeurApiService.HOST = ci.gouv.dgbf.utility.Helper.getApiHost("acteur");
		//ActeurApiService.PORT = ci.gouv.dgbf.utility.Helper.getApiPort("acteur");
		
		VariableHelper.write(VariableName.USER_INTERFACE_THEME_MENU_IDENTIFIER, "SIIBC-ACTEUR");
		
		DesktopDefault.initialize();		
		//DesktopDefaultImpl.MENU_PATH = "/adminfaces/menu.xhtml";
	}
	
}
