package com.jy.quartz;

import com.jy.model.task.Task;
import com.jy.service.task.TaskService;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.awt.SystemColor.info;

@Component
public class JobsInit implements InitializingBean{

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuartzManager quartzManager;
  @Override
    public void afterPropertiesSet() throws Exception {

      //设置休眠时间
      Thread.sleep(10000);
        //从数据库查询出所有待执行定时任务
        System.out.println("爱一个人是劫，有人劫后余生，有人在劫难逃");
        Task pt = new Task();
        pt.setTaskFlag(1);
        List<Task> list = taskService.selectTaskList(pt);
        if(null != list){
            list.forEach(t ->{
                quartzManager.addJob(t);
            });
        }
    }
}
