package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyk.utility.__kernel__.array.ArrayHelper;
import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.map.MapHelper;
import org.cyk.utility.__kernel__.object.AbstractObject;
import org.cyk.utility.__kernel__.object.Builder;
import org.cyk.utility.__kernel__.object.Configurator;
import org.cyk.utility.__kernel__.string.StringHelper;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class TabMenu extends org.cyk.utility.client.controller.web.jsf.primefaces.model.AbstractObject implements Serializable {

	private Collection<Tab> tabs;
	
	public Collection<Tab> getTabs(Boolean injectIfNull) {
		if(tabs == null && Boolean.TRUE.equals(injectIfNull))
			tabs = new ArrayList<>();
		return tabs;
	}
	
	protected Listener __getListener__() {
		return listener == null ? Listener.AbstractImpl.DefaultImpl.INSTANCE : (Listener) listener;
	}
	
	public TabMenu addTabByNames(Collection<String> names) {
		if(CollectionHelper.isEmpty(names))
			return this;
		names.stream().filter(name -> StringHelper.isNotBlank(name)).collect(Collectors.toList()).forEach(name -> {
			Map<Object,Object> arguments = __getListener__().getTabArguments(name);
			if(MapHelper.isNotEmpty(arguments)) {
				Tab tab = Tab.build(arguments);
				getTabs(Boolean.TRUE).add(tab);
			}
		});
		return this;
	}
	
	public TabMenu addTabByNames(String...names) {
		if(ArrayHelper.isEmpty(names))
			return this;
		return addTabByNames(CollectionHelper.listOf(names));
	}
	
	/**/
	
	public static final String FIELD_TABS = "tabs";
	
	/**/
	
	public static class ConfiguratorImpl extends org.cyk.utility.client.controller.web.jsf.primefaces.model.AbstractObject.AbstractConfiguratorImpl<TabMenu> implements Serializable {

		@SuppressWarnings("unchecked")
		@Override
		public void configure(TabMenu tabMenu, Map<Object, Object> arguments) {
			super.configure(tabMenu, arguments);
			if(tabMenu.tabs == null) {
				Collection<String> names = (Collection<String>) MapHelper.readByKey(arguments, FIELD_TABS_NAMES);
				if(CollectionHelper.isNotEmpty(names))
					tabMenu.addTabByNames(names);
			}
		}
		
		@Override
		protected Class<TabMenu> __getClass__() {
			return TabMenu.class;
		}
		
		@Override
		protected String __getTemplate__(TabMenu tabMenu, Map<Object, Object> arguments) {
			return "/atlantis/tabMenu.xhtml";
		}
		
		public static final String FIELD_TABS_NAMES = "tabs.names";
	}
	
	public static TabMenu build(Map<Object, Object> arguments) {
		return Builder.build(TabMenu.class,arguments);
	}
	
	public static TabMenu build(Object...objects) {
		return build(MapHelper.instantiate(objects));
	}
	
	static {
		Configurator.set(TabMenu.class, new ConfiguratorImpl());
	}
	
	public static interface Listener {
		Map<Object,Object> getTabArguments(String name);
		/**/
		public static abstract class AbstractImpl extends AbstractObject implements Listener,Serializable {
			
			@Override
			public Map<Object, Object> getTabArguments(String name) {
				return MapHelper.instantiate(Tab.FIELD_TITLE,name);
			}
			
			/**/
			public static class DefaultImpl extends Listener.AbstractImpl implements Serializable {
				public static final Listener INSTANCE = new DefaultImpl();
			}			
		}
	}
}