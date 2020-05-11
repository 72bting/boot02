package com.jy.service.task;

import com.jy.model.task.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<Task> selectTaskList(Task task);

    Map<String,Object> selectAllTaskList(Task task);

    void insert(Task task);

    void updateTask(Task task);

    void dalete_task(Task task);
}
