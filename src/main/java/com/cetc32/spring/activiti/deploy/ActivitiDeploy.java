package com.cetc32.spring.activiti.deploy;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;

/**
 * 
 * （1）部署流程之后，在对应的记录表（act_ge_bytearray--流程资源文件表）会生成响应的两条记录，其中一条是流程定义文件，一条是流程图片
 * （2）act_ge_property--流程主键生成策略表 
 * （3）act_re_deployment--流程对象表，部署之后会在表中生成一条响应的记录
 * （4）act_re_procdef--流程定义表，保存所部属流程的信息，id规则为（ 流程定义的key：版本：随机值）
 * 
 * @author zhongjun
 *
 */
public class ActivitiDeploy {

	@Test
	public void deloy() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = engine.getRepositoryService();

		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();

		// 1.deploy()方法部署
		// ---------------------------------
		// 使用deploy()方法部署,部署同时还可以使用name方法为流程起一个名字
		// 使用category()方法为流程设定类别,返回值为Deployment对象
		// 使用流程文件路径部署,会自动生成图片文件,建议将图片文件一同部署addClasspathResource("process/hello.png")
		Deployment deployment = deploymentBuilder.addClasspathResource("HelloProcess.bpmn")
				.addClasspathResource("HelloProcess.png").name("hello").category("first").tenantId("1").deploy();
		System.out.println("流程定义id： " + deployment.getId());
		
		// 2.使用输入流部署，不生成图片文件,建议将图片文件一同部署getResourceAsStream("/process/hello.png")  
		// InputStream in = this.getClass().getResourceAsStream("/process/hello.bpmn");  
		// builder.addInputStream("hello",in).deploy();
		
		// 3.使用zipinputStream部署，将流程定义文件与图片文件一起打包为zip格式文件  
       /* InputStream in = ActivitiDeploy.class.getClassLoader().getResourceAsStream("HelloProcess.zip");  
        ZipInputStream zip = new ZipInputStream(in);  
        deploymentBuilder.addZipInputStream(zip).name("hello").deploy();*/

	}

}
