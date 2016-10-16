package com.cetc32.spring.activiti.deploy;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.junit.Test;

/**
 * 删除流程
 * 
 * @author zhongjun
 *
 */
public class ActivitiDelete {

	@Test
	public void delete() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();

		// 流程启动则报错
		repositoryService.deleteDeployment("1");
		
		// 级联删除，不管是否启动
		repositoryService.deleteDeployment("2501", true);
		

	}

}
