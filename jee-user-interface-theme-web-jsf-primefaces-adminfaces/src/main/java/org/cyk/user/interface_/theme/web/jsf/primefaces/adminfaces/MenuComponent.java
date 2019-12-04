package org.cyk.user.interface_.theme.web.jsf.primefaces.adminfaces;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.cyk.utility.__kernel__.collection.CollectionHelper;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.client.controller.component.menu.MenuItem;

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
		if(menu == null)
			return;
		ResponseWriter writer = context.getResponseWriter();
		/*
		<ul class="sidebar-menu tree" data-widget="tree">
	        <li>
	            <p:link outcome="/index" onclick="clearBreadCrumbs()">
	                <i class="fa fa-home"></i>
	                <span>My Home</span>
	            </p:link>
	        </li>
		</ul>
		*/
		/*
		writer.append("<ul class=\"sidebar-menu tree\" data-widget=\"tree\">");
		writer.append(" <li>");
		writer.append("<a href=\"/index\" onclick=\"clearBreadCrumbs()\">");
		writer.append("<i class=\"fa fa-home\"></i>");
		writer.append("<span>My Home</span>");
		writer.append(" </a>");
		writer.append("</li>");
		writer.append("</ul>");
		*/
		
		writer.startElement("ul", this);
		writer.writeAttribute("class", "sidebar-menu tree",null);
		writer.writeAttribute("data-widget", "tree",null);
		
		//addLi(writer, "fa fa-home", "HOME", "/index.jsf", "clearBreadCrumbs();");
		if(CollectionHelper.isNotEmpty(menu.getItems())) {
			for(MenuItem index : menu.getItems().get()) {
				addLi(writer,index);
			}
		}
		
		writer.endElement("ul");
	}

	protected void addLi(ResponseWriter writer,String iconClass,String text,String href,String onClick) throws IOException{
		writer.startElement("li", this);
		addLink(writer, iconClass, text, href, onClick);
		writer.endElement("li");
	}
	
	private void addLi(ResponseWriter writer,MenuItem item) throws IOException{
		writer.startElement("li", this);
		addLink(writer, (String) item.getCommandable().getProperties().getIcon(),item.getCommandable().getName(),item.getCommandable().getUniformResourceIdentifier(),null);
		if(CollectionHelper.isNotEmpty(item.getChildren())) {
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
		writeAttributeIfValueNotBlank(writer,"href",ValueHelper.defaultToIfBlank(href, "#"));
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
		if(StringHelper.isNotBlank(value))
			writer.writeAttribute(name,value,null);
	}

	private void writeTextIfValueNotBlank(ResponseWriter writer,String text) throws IOException{
		if(StringHelper.isNotBlank(text))
			writer.writeText(text,null);
	}
}

