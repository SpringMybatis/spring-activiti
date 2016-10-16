package com.cetc32.spring.activiti.create;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.junit.Test;

public class ActivitiEngine {

	@Test
	public void createEngine() {

		ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
		configuration.setJdbcDriver("com.mysql.jdbc.Driver");
		configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti");
		configuration.setJdbcUsername("root");
		configuration.setJdbcPassword("123456");
		configuration.setDatabaseSchema("true");
		// configuration.setDatabaseSchemaUpdate("create-drop");
		configuration.setDatabaseSchemaUpdate("update");

		ProcessEngine engine = configuration.buildProcessEngine();
		System.out.println(engine);
	}

	
	@Test
	public void createDefaultEngine() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		System.out.println(engine);
	}
	
	@Test
	public void createEngineWithResources() {
		ProcessEngineConfiguration config = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti/activiti.cfg.xml");  
		ProcessEngine engine = config.buildProcessEngine();  
		System.out.println(engine);
	}

}
