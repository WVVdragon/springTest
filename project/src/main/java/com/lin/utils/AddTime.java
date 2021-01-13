package com.lin.utils;

import com.lin.mapper.TaskMapper;
import com.lin.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author LYN
 */
public class AddTime extends java.util.TimerTask {
    @Autowired
    TaskMapper taskMapper;

    @Override
    public void run() {
        List<Task> taskList = taskMapper.queryInTask();
        if (taskList.isEmpty()) {
            return;
        }
        for (Task task : taskList) {
            int ETime = task.getTaskETime();
            ETime++;
            task.setTaskETime(ETime);
            taskMapper.updateETime(ETime,task.getTaskId());
        }
    }
}
