package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.ThemeManager;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf.annotation.DGBF;
import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.session.SessionHelper;
import org.cyk.utility.__kernel__.string.StringHelper;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

import ci.gouv.dgbf.utility.Actor;

@DGBF
public class ThemeManagerImpl extends ThemeManager.AbstractImpl implements Serializable {

	protected String systemName,systemLink;
	
	@Override
	protected String __getSystemName__(AbstractPageContainerManagedImpl page) {
		if(systemName == null)
			systemName = ValueHelper.defaultToIfBlank(ConfigurationHelper.getValueAsString(EnvironmentVariableName.NAME.getValue()),"SIGOBE");
		return systemName;
	}
	
	@Override
	protected String __getSystemLink__(AbstractPageContainerManagedImpl page) {
		if(systemLink == null)
			systemLink = getSystemLink();
		return systemLink;
	}
	
	@Override
	protected Object __getPropertyValue__(AbstractPageContainerManagedImpl page, Object name) {
		Actor actor = getActor();
		if(actor == null)
			return super.__getPropertyValue__(page, name);
		if(PropertyName.SESSION_USER_NAME.name().equals(name))
			return actor.getUsername();
		if(PropertyName.SESSION_USER_NAMES.name().equals(name))
			return actor.getNames();
		if(PropertyName.SESSION_USER_ELECTRONIC_MAIL_ADDRESS.name().equals(name))
			return actor.getElectronicMailAddress();
		if(PropertyName.SESSION_USER_SECTION.name().equals(name))
			return actor.getSection();
		if(PropertyName.SESSION_USER_ADMINISTRATIVE_UNIT.name().equals(name))
			return actor.getAdministrativeUnit();
		if(PropertyName.SESSION_USER_ADMINISTRATIVE_FUNCTION.name().equals(name))
			return actor.getAdministrativeFunction();
		if(PropertyName.SESSION_USER_VISIBLE_SECTIONS.name().equals(name))
			return actor.getVisibleSections();
		if(PropertyName.MANAGE_MY_ACCOUNT_URL.name().equals(name))
			return getSystemLink()+"acteur/mon_compte";
		return super.__getPropertyValue__(page, name);
	}
	
	/**/
	
	public static String getSystemLink() {
		if(StringHelper.isNotBlank(SYSTEM_LINK))
			return SYSTEM_LINK;
		String environment = ConfigurationHelper.getValueAsString(EnvironmentVariableName.ENVIRONMENT.getValue());
		//return StringUtils.equalsIgnoreCase(environment, "default") ? "https://siib.dgbf.ci/" : "https://siibtest.dgbf.ci/";
		return StringUtils.equalsIgnoreCase(environment, "test") ? "https://siibtest.dgbf.ci/" : "https://siib.dgbf.ci/";
	}
	
	private static Actor getActor() {
		Actor actor = (Actor) SessionHelper.getAttributeValue(Actor.class);
		if(actor != null)
			return actor;
		if(StringHelper.isBlank(SessionHelper.getUserName()))
			return null;
		SessionHelper.setAttributeValue(Actor.class, actor = Actor.getInformationsAndVisibilities(SessionHelper.getUserName()));
		return actor;
	}
	
	public static enum PropertyName {
		SESSION_USER_NAME
		,SESSION_USER_NAMES
		,SESSION_USER_ELECTRONIC_MAIL_ADDRESS
		,SESSION_USER_SECTION
		,SESSION_USER_ADMINISTRATIVE_FUNCTION
		,SESSION_USER_ADMINISTRATIVE_UNIT
		,SESSION_USER_PHOTO
		,SESSION_USER_VISIBLE_SECTIONS
		,MANAGE_MY_ACCOUNT_URL
		;
	}
	
	public static String SYSTEM_LINK;
}