package com.cetc32.spring.activiti.deploy;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class ActivitiProcessInstance {

	@Test
	public void getProcessDefinition() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RuntimeService runtimeService = engine.getRuntimeService();

		// 1.使用流程定义的id启动流程实例，返回值为流程实例对象
		// ProcessInstance processInstance =
		// runtimeService.startProcessInstanceById("hello:2:7504");

		// 2.使用流程定义的key启动流程实例,推荐使用
		// 同一个流程key相同，不同的是版本，使用key启动可以默认启动最新版本的流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceById("hello:3:12504");

		// 流程实例中包含的信息
		System.out.println("当前活动节点  " + processInstance.getActivityId());
		System.out.println("关联业务键：  " + processInstance.getBusinessKey());
		System.out.println("流程部署id： " + processInstance.getDeploymentId());
		System.out.println("流程描述：       " + processInstance.getDescription());
		System.out.println("流程实例id： " + processInstance.getId());
		System.out.println("流程实例名称： " + processInstance.getName());
		System.out.println("父流程id：      " + processInstance.getParentId());
		System.out.println("流程定义id： " + processInstance.getProcessDefinitionId());
		System.out.println("流程定义key：    " + processInstance.getProcessDefinitionKey());
		System.out.println("流程定义名称： " + processInstance.getProcessDefinitionName());
		System.out.println("流程实例id： " + processInstance.getProcessInstanceId());
		System.out.println("流程所属人id：    " + processInstance.getTenantId());
		System.out.println("流程定义版本： " + processInstance.getProcessDefinitionVersion());
		System.out.println("流程变量：       " + processInstance.getProcessVariables());
		System.out.println("是否结束：       " + processInstance.isEnded());
		System.out.println("是否暂停：       " + processInstance.isSuspended());
		System.out.println("################################");
	}

	@Test
	public void delete() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = engine.getRuntimeService();
		// 会清空当前执行的流程表和当前任务表中的与当前流程实例对应的数据
		// 同时更新历史任务表中的数据
		runtimeService.deleteProcessInstance("15001", "test");
	}

	@Test
	public void activeOrSuspend() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = engine.getRuntimeService();
		// 暂停
		runtimeService.suspendProcessInstanceById("17501");
		// 启用
		runtimeService.activateProcessInstanceById("17501");

	}

	@Test
	public void search() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		RuntimeService runtimeService = engine.getRuntimeService();
		List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().orderByProcessDefinitionId().asc().listPage(0, 10);
		if (list != null && list.size() > 0) {
			for (ProcessInstance processInstance : list) {
				System.out.println("当前活动节点  " + processInstance.getActivityId());
				System.out.println("关联业务键：  " + processInstance.getBusinessKey());
				System.out.println("流程部署id： " + processInstance.getDeploymentId());
				System.out.println("流程描述：       " + processInstance.getDescription());
				System.out.println("流程实例id： " + processInstance.getId());
				System.out.println("流程实例名称： " + processInstance.getName());
				System.out.println("父流程id：      " + processInstance.getParentId());
				System.out.println("流程定义id： " + processInstance.getProcessDefinitionId());
				System.out.println("流程定义key：    " + processInstance.getProcessDefinitionKey());
				System.out.println("流程定义名称： " + processInstance.getProcessDefinitionName());
				System.out.println("流程实例id： " + processInstance.getProcessInstanceId());
				System.out.println("流程所属人id：    " + processInstance.getTenantId());
				System.out.println("流程定义版本： " + processInstance.getProcessDefinitionVersion());
				System.out.println("流程变量：       " + processInstance.getProcessVariables());
				System.out.println("是否结束：       " + processInstance.isEnded());
				System.out.println("是否暂停：       " + processInstance.isSuspended());
				System.out.println("################################");
			}
		}
	}
	
	@Test
	public void excute() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ExecutionQuery executionQuery = engine.getRuntimeService().createExecutionQuery();
		// 查询流程执行信息，可以根据条件查询
		List<Execution> list = executionQuery.list();
		if (list != null && list.size() > 0) {
			for (Execution e : list) {
				System.out.println("流程当前活动节点：" + e.getActivityId());
				System.out.println("流程描述：       " + e.getDescription());
				System.out.println("流程ID：       " + e.getId());
				System.out.println("流程名称：       " + e.getName());
				System.out.println("父流程ID：      " + e.getParentId());
				System.out.println("流程定义ID： " + e.getProcessInstanceId());
				System.out.println("流程所有人ID：    " + e.getTenantId());
				System.out.println("#######################");
			}
		}
	}
	
	
	@Test
	public void result() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		String processInstanceId = "17501";
		ProcessInstance pi = engine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if (pi == null) {
			System.out.println("流程已经结束");
		} else {
			System.out.println("流程没有结束");
		}
	}
	
	
	
	

}
