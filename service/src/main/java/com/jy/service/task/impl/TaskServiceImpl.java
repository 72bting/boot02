package com.jy.service.task.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.task.TaskMapper;
import com.jy.model.task.Task;
import com.jy.quartz.MailJob;
import com.jy.quartz.QuartzManager;
import com.jy.service.task.TaskService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public List<Task> selectTaskList(Task task) {
        return taskMapper.selectTaskList(task);
    }

    @Override
    public Map<String, Object> selectAllTaskList(Task task) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> page =  PageHelper.startPage(task.getPage(),task.getLimit());
        List<Task> list = selectTaskList(task);
        long total = page.getTotal();
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        map.put("count",total);
        return map;
    }

    @Override
    public void insert(Task task) {
        if(task.getTaskFlag() == 1){
            //立即执行，把任务添加到定时器
            quartzManager.addJob(task);
        }
        //不执行，直接保存到数据库
        taskMapper.insertTask(task);
    }

    @Override
    public void updateTask(Task task) {
        if (task.getBtn().equals("stop")){
            //停止任务
            task.setTaskFlag(0);
        }else if(task.getBtn().equals("start")){
            //开始任务
            task.setTaskFlag(1);
        }
        updateTaskDetail(task);
    }

    @Override
    public void dalete_task(Task task) {
        if(1 == task.getTaskFlag()){
            task.setBtn("stop");
            //查询出当前任务并停止
            updateTaskDetail(task);
        }
        //删除任务
        taskMapper.deleteTask(task);
    }

    private void updateTaskDetail(Task task){
       //修改数据库
        taskMapper.updateTask(task);
        //将任务信息查询出来
        task.setTaskFlag(-1);
        List<Task> list = taskMapper.selectTaskList(task);
        if(list != null && list.size() == 1){
            if (task.getBtn().equals("stop")){
                //从调度器移除任务
                quartzManager.removeJob(list.get(0));
            }else if(task.getBtn().equals("start")){
                //将任务添加到调度器
                quartzManager.addJob(list.get(0));
            }
        }
    }
}
