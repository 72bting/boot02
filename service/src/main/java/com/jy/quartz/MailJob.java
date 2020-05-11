package com.jy.quartz;

import com.jy.common.MyTask;
import com.jy.common.MailUtil;
import com.jy.model.task.Task;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MailJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        MailUtil.sendMail("15243096027@163.com","定时器测试","所有热爱的事情都要不遗余力");
        //Object scheduleJob = jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        System.out.println(jobExecutionContext.getJobDetail().getKey());
        Task task = (Task) jobExecutionContext.getMergedJobDataMap().get("myTask");
        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getMergedJobDataMap().get("applicationContext");

        try {
            //利用反射执行对应方法
            if (task.getTaskClass().contains("MyTask")){
                Object obj = applicationContext.getBean("myTask");
                Method method = obj.getClass().getMethod(task.getTaskMethod());
                method.invoke(obj);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(jobExecutionContext.getJobDetail().getKey() + ": 发送定时邮件");
    }
}
