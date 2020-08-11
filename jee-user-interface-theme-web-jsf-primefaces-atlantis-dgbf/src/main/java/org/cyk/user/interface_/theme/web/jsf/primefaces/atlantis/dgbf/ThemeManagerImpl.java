package org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf;

import java.io.Serializable;

import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.ThemeManager;
import org.cyk.user.interface_.theme.web.jsf.primefaces.atlantis.dgbf.annotation.DGBF;
import org.cyk.utility.__kernel__.configuration.ConfigurationHelper;
import org.cyk.utility.__kernel__.value.ValueHelper;
import org.cyk.utility.__kernel__.variable.VariableName;
import org.cyk.utility.client.controller.web.jsf.primefaces.AbstractPageContainerManagedImpl;

@DGBF
public class ThemeManagerImpl extends ThemeManager.AbstractImpl implements Serializable {

	private String systemName,systemLink;
	
	@Override
	protected String __getSystemName__(AbstractPageContainerManagedImpl page) {
		if(systemName == null)
			systemName = ValueHelper.defaultToIfBlank(ConfigurationHelper.getValueAsString("SIIBC_NAME"),"SIGOBE");
		return systemName;
	}
	
	@Override
	protected String __getSystemLink__(AbstractPageContainerManagedImpl page) {
		if(systemLink == null)
			systemLink = ValueHelper.defaultToIfBlank(ConfigurationHelper.getValueAsString(VariableName.SYSTEM_WEB_HOME_URL),"http://siibtest.dgbf.ci");	
		return systemLink;
	}
}