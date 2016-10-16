package com.cetc32.spring.activiti.deploy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

public class ActivitiProcessDefinition {

	@Test
	public void getProcessDefinition() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();
		// 查询流程定义，可以排序，查询数量，分页等
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().asc().list();
		for (ProcessDefinition processDefinition : list) {
			System.out.println("流程组织机构： " + processDefinition.getCategory());
			System.out.println("流程部署ID： " + processDefinition.getDeploymentId());
			System.out.println("流程描述：       " + processDefinition.getDescription());
			System.out.println("流程图片文件： " + processDefinition.getDiagramResourceName());
			System.out.println("流程定义ID： " + processDefinition.getId());
			System.out.println("流程定义key " + processDefinition.getKey());
			System.out.println("流程设计名称： " + processDefinition.getName());
			System.out.println("流程定义文件： " + processDefinition.getResourceName());
			System.out.println("流程所有人ID：    " + processDefinition.getTenantId());
			System.out.println("流程版本：       " + processDefinition.getVersion());
		}
	}

	@Test
	public void getLastProcessDefinition() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();
		// 查询流程定义，可以排序，查询数量，分页等
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();

		// 使用map集合存储得到的集合，同时将所有低版本的过滤
		Map<String, ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
		if (list != null && list.size() > 0) {
			for (ProcessDefinition pd : list) {
				// 新版本数据将会替代就版本数据
				map.put(pd.getKey(), pd);
			}
		}

		for (ProcessDefinition processDefinition : map.values()) {
			System.out.println("流程组织机构： " + processDefinition.getCategory());
			System.out.println("流程部署ID： " + processDefinition.getDeploymentId());
			System.out.println("流程描述：       " + processDefinition.getDescription());
			System.out.println("流程图片文件： " + processDefinition.getDiagramResourceName());
			System.out.println("流程定义ID： " + processDefinition.getId());
			System.out.println("流程定义key " + processDefinition.getKey());
			System.out.println("流程设计名称： " + processDefinition.getName());
			System.out.println("流程定义文件： " + processDefinition.getResourceName());
			System.out.println("流程所有人ID：    " + processDefinition.getTenantId());
			System.out.println("流程版本：       " + processDefinition.getVersion());
		}
	}
	
	
	@Test
	public void getLastProcessDefinitionByApi() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();
		// 查询流程定义，可以排序，查询数量，分页等
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().latestVersion().singleResult();

		System.out.println("流程组织机构： " + processDefinition.getCategory());
		System.out.println("流程部署ID： " + processDefinition.getDeploymentId());
		System.out.println("流程描述：       " + processDefinition.getDescription());
		System.out.println("流程图片文件： " + processDefinition.getDiagramResourceName());
		System.out.println("流程定义ID： " + processDefinition.getId());
		System.out.println("流程定义key " + processDefinition.getKey());
		System.out.println("流程设计名称： " + processDefinition.getName());
		System.out.println("流程定义文件： " + processDefinition.getResourceName());
		System.out.println("流程所有人ID：    " + processDefinition.getTenantId());
		System.out.println("流程版本：       " + processDefinition.getVersion());
	}
	
	@Test
	public void delete() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();

		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().asc().list();  
		
		if(list!=null && list.size()>0){  
			ProcessDefinition processDefinition = list.get(0);  
		    String id = processDefinition.getDeploymentId();  
		    // 级联删除流程，不论是否启动，都会删除  
		    repositoryService.deleteDeployment(id, true);  
		} 
	}

	@Test
	public void activeOrSuspend(){
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();
		
		//挂起流程定义，挂起后不能启动  
		repositoryService.suspendProcessDefinitionByKey("7504");  
		  
		//激活流程定义，激活后可以启动  
		repositoryService.activateProcessDefinitionByKey("7504");  
		
	}
	
}
