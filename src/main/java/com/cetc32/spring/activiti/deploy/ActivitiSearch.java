package com.cetc32.spring.activiti.deploy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.junit.Test;

/**
 * 删除流程
 * 
 * @author zhongjun
 *
 */
public class ActivitiSearch {

	@Test
	public void delete() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();

		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();

		// 根据id查询
		List<Deployment> list = deploymentQuery.deploymentId("5001").orderByDeploymentId().desc().listPage(0, 10);
		// List<Deployment> list = deploymentQuery.deploymentName("hello").orderByDeploymentId().desc().listPage(0,10);
		// List<Deployment> list = deploymentQuery.deploymentNameLike("%llo%").orderByDeploymentId().desc().listPage(0,10);

		for (Deployment deployment : list) {
			System.out.println("流程ID：       " + deployment.getId());
			System.out.println("流程名称：       " + deployment.getName());
			System.out.println("流程类别：       " + deployment.getCategory());
			System.out.println("流程所有人：  " + deployment.getTenantId());
			System.out.println("部署时间：       " + deployment.getDeploymentTime());
		}
	}

	@Test
	public void getFile() throws Exception {
		
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = engine.getRepositoryService();
		// 获取流程部署文件
		List<String> resourceNames = repositoryService.getDeploymentResourceNames("5001");
		System.out.println(resourceNames);
		// 遍历文件名
		for (String string : resourceNames) {
			// 通过文件名和流程id获取文件
			InputStream in = repositoryService.getResourceAsStream("5001", string);
			string = string.substring(string.indexOf("/") + "/".length());
			// 写出文件
			OutputStream out = new FileOutputStream(new File("d:/" + string));
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		}

	}

}
