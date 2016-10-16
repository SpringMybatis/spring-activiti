package com.cetc32.spring.activiti.service;

import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;

public class ActivitiService {

	
	@Test
	public void getService(){
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		System.out.println(engine);
		
		RepositoryService repositoryService = engine.getRepositoryService();
		System.out.println(repositoryService);
		
		RuntimeService runtimeService = engine.getRuntimeService();
		System.out.println(runtimeService);
		
		IdentityService identityService = engine.getIdentityService();
		System.out.println(identityService);
		
		HistoryService historyService = engine.getHistoryService();
		System.out.println(historyService);
		
		TaskService taskService = engine.getTaskService();
		System.out.println(taskService);
		
		FormService formService = engine.getFormService();
		System.out.println(formService);
		
		DynamicBpmnService dynamicBpmnService = engine.getDynamicBpmnService();
		System.out.println(dynamicBpmnService);
	}
	
}
