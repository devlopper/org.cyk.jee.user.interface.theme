package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model;

import java.io.Serializable;
import java.util.Map;

import org.cyk.utility.__kernel__.map.MapHelper;
import org.cyk.utility.__kernel__.object.Builder;
import org.cyk.utility.__kernel__.object.Configurator;
import org.cyk.utility.client.controller.web.jsf.primefaces.model.AbstractObject;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Tab extends AbstractObject implements Serializable {

	private String icon,title;
	private Menu menu;

	/**/
	
	public static final String FIELD_ICON = "icon";
	public static final String FIELD_TITLE = "title";
	public static final String FIELD_MENU = "menu";
	
	/**/
	
	public static class ConfiguratorImpl extends org.cyk.utility.client.controller.web.jsf.primefaces.model.AbstractObject.AbstractConfiguratorImpl<Tab> implements Serializable {

		@Override
		public void configure(Tab tab, Map<Object, Object> arguments) {
			super.configure(tab, arguments);
			
		}
		
		@Override
		protected Class<Tab> __getClass__() {
			return Tab.class;
		}
		
		@Override
		protected String __getTemplate__(Tab tab, Map<Object, Object> arguments) {
			return "/atlantis/tab.xhtml";
		}
	}
	
	public static Tab build(Map<Object, Object> arguments) {
		return Builder.build(Tab.class,arguments);
	}
	
	public static Tab build(Object...objects) {
		return build(MapHelper.instantiate(objects));
	}
	
	static {
		Configurator.set(Tab.class, new ConfiguratorImpl());
	}
}