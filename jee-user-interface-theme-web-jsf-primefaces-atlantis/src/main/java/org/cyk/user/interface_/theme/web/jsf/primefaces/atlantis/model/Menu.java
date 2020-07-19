package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model;

import java.io.Serializable;
import java.util.Map;

import org.cyk.utility.__kernel__.map.MapHelper;
import org.cyk.utility.__kernel__.object.Builder;
import org.cyk.utility.__kernel__.object.Configurator;
import org.cyk.utility.client.controller.web.jsf.primefaces.model.AbstractObject;
import org.primefaces.model.menu.MenuModel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class Menu extends AbstractObject implements Serializable {

	private MenuModel model;
	
	/**/
	
	public static final String FIELD_MODEL = "model";
	
	/**/
	
	public static class ConfiguratorImpl extends org.cyk.utility.client.controller.web.jsf.primefaces.model.AbstractObject.AbstractConfiguratorImpl<Menu> implements Serializable {

		@Override
		public void configure(Menu menu, Map<Object, Object> arguments) {
			super.configure(menu, arguments);
			
		}
		
		@Override
		protected Class<Menu> __getClass__() {
			return Menu.class;
		}
		
		@Override
		protected String __getTemplate__(Menu menu, Map<Object, Object> arguments) {
			return "/atlantis/menu.xhtml";
		}
		
		public static final String FIELD_INPUT = "input";
	}
	
	public static Menu build(Map<Object, Object> arguments) {
		return Builder.build(Menu.class,arguments);
	}
	
	public static Menu build(Object...objects) {
		return build(MapHelper.instantiate(objects));
	}
	
	static {
		Configurator.set(Menu.class, new ConfiguratorImpl());
	}
}