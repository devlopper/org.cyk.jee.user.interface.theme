package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.ThemeManager;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf.annotation.DGBF;
import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

@DGBF
public class ThemeManagerImpl extends ThemeManager.AbstractImpl implements Serializable {

	protected String systemName,systemLink;
	
	@Override
	protected String __getSystemName__(AbstractPageContainerManagedImpl page) {
		if(systemName == null)
			systemName = ValueHelper.defaultToIfBlank(ConfigurationHelper.getValueAsString("SIIBC_NAME"),"SIGOBE");
		return systemName;
	}
	
	@Override
	protected String __getSystemLink__(AbstractPageContainerManagedImpl page) {
		if(systemLink == null) {
			String environment = ConfigurationHelper.getValueAsString("SIIB_ENVIRONMENT");
			systemLink = StringUtils.equalsIgnoreCase(environment, "default") ? "http://siib.dgbf.ci" : "http://siibtest.dgbf.ci";
		}
		return systemLink;
	}
}