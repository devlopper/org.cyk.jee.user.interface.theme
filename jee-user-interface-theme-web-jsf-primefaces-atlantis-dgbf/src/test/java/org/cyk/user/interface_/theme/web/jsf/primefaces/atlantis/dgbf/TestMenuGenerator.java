package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.jboss.weld.environment.se.Weld;

import ci.gouv.dgbf.sib.menu.generator.MenuGenerator;

public class TestMenuGenerator {

	public static void main(String[] args) {
		Weld weld = new Weld();
	    weld.initialize();
		MenuGenerator menuGenerator = DependencyInjection.inject(MenuGenerator.class);
		System.out.println(menuGenerator.generateServiceMenu("COLB","username"));
		weld.shutdown();
	}
	
}
