package com.cetc32.spring.activiti.deploy;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ActivitiProcessTask {

	@Test  
	public void testaddComment() {  
	    
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		TaskService taskService = engine.getTaskService();
		
	    /** 
	     * 注意：添加批注的时候，由于Activiti底层代码是使用： 
	     *      String userId = Authentication.getAuthenticatedUserId(); 
	     *      CommentEntity comment = new CommentEntity(); 
	     *      comment.setUserId(userId); 
	     *  所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的 
	     *  User_ID的字段，不过不添加审核人，该字段为null 
	     *  所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人 
	     * */  
	    Authentication.setAuthenticatedUserId("user");  
	    // 添加  
	    taskService.addComment("17504", "17501", "this is a test");  
	    // 删除  
	    // taskService.deleteComments("17504", "17501");  
	}  
	
	@Test
	public void selectComment(){
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		HistoryService historyService = engine.getHistoryService();  
		TaskService taskService = engine.getTaskService();
        // 1.
		List<HistoricProcessInstance> hplist = historyService.createHistoricProcessInstanceQuery().processInstanceId("17501").list();  
        if (hplist != null && hplist.size() > 0) {  
            for (HistoricProcessInstance hp : hplist) {  
                String htaskid = hp.getId();  
                List<Comment> lc = taskService.getTaskComments(htaskid);  
                System.out.println(lc.toString());
            }  
        } 
        // 2.
        String taskId = "17504";  
        List<Comment> list = new ArrayList<Comment>();  
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();  
        String piid = task.getProcessInstanceId();  
        list = taskService.getProcessInstanceComments(piid);  
        System.out.println(list);  
	}
	
	
}
