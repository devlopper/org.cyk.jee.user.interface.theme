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
			systemName = ValueHelper.defaultToIfBlank(ConfigurationHelper.getValueAsString(EnvironmentVariableName.NAME.getValue()),"SIGOBE");
		return systemName;
	}
	
	@Override
	protected String __getSystemLink__(AbstractPageContainerManagedImpl page) {
		if(systemLink == null)
			systemLink = getSystemLink();
		return systemLink;
	}
	
	public static String getSystemLink() {
		String environment = ConfigurationHelper.getValueAsString(EnvironmentVariableName.ENVIRONMENT.getValue());
		return StringUtils.equalsIgnoreCase(environment, "default") ? "https://siib.dgbf.ci/" : "https://siibtest.dgbf.ci/";
	}
}