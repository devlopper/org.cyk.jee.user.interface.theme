package org.cyk.user.interface_.theme.web.jsf.primefaces.adminfaces;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.cyk.utility.__kernel__.DependencyInjection;
import org.cyk.utility.client.controller.component.menu.MenuItem;
import org.cyk.utility.collection.CollectionHelper;
import org.cyk.utility.string.StringHelper;

@FacesComponent(createTag = true, tagName = "menu", namespace = "http://runtime.adminfaces.primefaces.jsf.web.theme.interface.user.cyk.org")
public class MenuComponent extends UIComponentBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String getFamily() {
		return getClass().getPackage().getName();
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		org.cyk.utility.client.controller.component.menu.Menu menu = (org.cyk.utility.client.controller.component.menu.Menu) getAttributes().get("value");
		ResponseWriter writer = context.getResponseWriter();
		//<ul class="sidebar-menu tree" data-widget="tree">
		writer.startElement("ul", this);
		writer.writeAttribute("class", "sidebar-menu tree",null);
		writer.writeAttribute("data-widget", "tree",null);
		
		//addLi(writer, "fa fa-home", "HOME", "/index.jsf", "clearBreadCrumbs();");
		if(Boolean.TRUE.equals(DependencyInjection.inject(CollectionHelper.class).isNotEmpty(menu.getItems()))) {
			for(MenuItem index : menu.getItems().get()) {
				addLi(writer,index);
			}
		}
		
		writer.endElement("ul");
	}

	private void addLi(ResponseWriter writer,String iconClass,String text,String href,String onClick) throws IOException{
		/*
		 * 
		 * <li>
            <p:link>
                <i class="fa fa-car"></i>
                <span>Cars</span>
            </p:link>
        </li>
		 * 
		 */
		writer.startElement("li", this);
		addLink(writer, iconClass, text, href, onClick);
		writer.endElement("li");
	}
	
	private void addLi(ResponseWriter writer,MenuItem item) throws IOException{
		writer.startElement("li", this);
		addLink(writer, (String) item.getCommandable().getProperties().getIcon(),item.getCommandable().getName(),null,null);
		if(Boolean.TRUE.equals(DependencyInjection.inject(CollectionHelper.class).isNotEmpty(item.getChildren()))) {
			writer.startElement("ul", this);
			for(Object index : item.getChildren()) {
				if(index instanceof MenuItem)
					addLi(writer, (MenuItem) index);
			}
			writer.endElement("ul");
		}
		writer.endElement("li");
	}
	
	private void addLink(ResponseWriter writer,String iconClass,String text,String href,String onClick) throws IOException{
		writer.startElement("a", this);
		writeAttributeIfValueNotBlank(writer,"href",href);
		writeAttributeIfValueNotBlank(writer,"onclick",onClick);
		addIcon(writer, iconClass);
		addText(writer, text);
		writer.endElement("a");
	}
	
	private void addText(ResponseWriter writer,String text) throws IOException{
		writer.startElement("span", this);
		writeTextIfValueNotBlank(writer,text);
		writer.endElement("span");
	}
	
	private void addIcon(ResponseWriter writer,String klass) throws IOException{
		writer.startElement("i", this);
		writeAttributeIfValueNotBlank(writer,"class",klass);
		writer.endElement("i");
	}
	
	/**/
	
	private void writeAttributeIfValueNotBlank(ResponseWriter writer,String name,String value) throws IOException{
		if(Boolean.TRUE.equals(DependencyInjection.inject(StringHelper.class).isNotBlank(value)))
			writer.writeAttribute(name,value,null);
	}

	private void writeTextIfValueNotBlank(ResponseWriter writer,String text) throws IOException{
		if(Boolean.TRUE.equals(DependencyInjection.inject(StringHelper.class).isNotBlank(text)))
			writer.writeText(text,null);
	}
}

