package org.cyk.user.interface_.theme.deployment;

import java.io.Serializable;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.client.controller.component.menu.MenuBuilderMapGetter;
import org.cyk.utility.client.deployment.AbstractServletContextListener;
import org.cyk.utility.system.node.SystemNodeClient;

@WebListener
public class ServletContextListener extends AbstractServletContextListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __listenContextInitialized__(ServletContextEvent servletContextEvent) {
		DependencyInjection.setQualifierClass(MenuBuilderMapGetter.class, CustomTheme.class);
		__inject__(SystemNodeClient.class).setName("User Interface Theme");
	}
	
	@Override
	protected void __listenContextDestroyed__(ServletContextEvent servletContextEvent) {
		
	}

}
