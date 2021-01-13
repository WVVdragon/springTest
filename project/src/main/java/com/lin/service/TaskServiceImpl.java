package com.lin.service;

import com.lin.constant.ResultConstant;
import com.lin.mapper.TaskMapper;
import com.lin.pojo.History;
import com.lin.pojo.Task;
import com.lin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author LYN
 */
@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Result addTask(Task task) {
        Date nowDate = new Date();
        if(task.getTaskStartDate().before(nowDate)||task.getTaskStopDate().before(task.getTaskStartDate())){
            return ResultConstant.TIME_ERROR;
        }
        if(taskMapper.addTask(task)!=0){
            return ResultConstant.SUCCESS;
        }else{
            return ResultConstant.ERROR;
        }
    }

    @Override
    public Result queryAllTask() {
        if(taskMapper.queryAllTask().isEmpty()){
            return ResultConstant.TASK_NULL;
        }else {
            List<Task> taskList = taskMapper.queryAllTask();
            for(Task task:taskList){
                this.createNote(task.getTaskId());
            }
            return new Result().success(taskList,null);
        }
    }

    @Override
    public Result queryTaskById(int taskId) {
        Task task = taskMapper.queryTaskById(taskId);
        return new Result().success(task,taskMapper.queryHistoryByTaskId(taskId));
    }

    @Override
    public Result toStartTask(int taskId,int taskSTime,int taskRTime,String taskNote) {
        Task task = taskMapper.queryTaskById(taskId);
        if(task.getTaskStatuId()==1||task.getTaskStatuId()==2){
            return ResultConstant.STARTED;
        }
        taskSTime = task.getTaskSTime()+taskSTime;
        Task newTask = new Task(taskId,taskSTime,taskRTime);
        if(taskMapper.toStartTask(newTask)!=0){
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new Date());
            String msg = datetime + "由 用户1 开启";
            History history = new History(msg,task.getTaskId(),taskNote);
            taskMapper.insertHistory(history);
            System.out.println(taskMapper.queryHistoryByTaskId(taskId));
            return new Result().success(null,taskMapper.queryHistoryByTaskId(taskId));
        }else{
            return ResultConstant.ERROR;
        }
    }

    @Override
    public void createNote(int taskId) {
        Task task = taskMapper.queryTaskById(taskId);
        if(taskMapper.queryHistoryByTaskId(taskId).isEmpty()){
            System.out.println("2");
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(task.getTaskCreateTime());
            String msg = datetime + "由 用户1 创建";
            History history = new History(msg,task.getTaskId(),null);
            taskMapper.insertHistory(history);
        }
    }

    @Override
    public Result updateTask(Task task) {
        if(taskMapper.updateTask(task)!=0){
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new Date());
            String msg = datetime + "由 用户1 编辑修改";
            History history = new History(msg,task.getTaskId(),task.getTaskNote());
            taskMapper.insertHistory(history);
            return new Result().success(null,taskMapper.queryHistoryByTaskId(task.getTaskId()));
        }else{
            return ResultConstant.ERROR;
        }
    }

    @Override
    public Result toStopTask(int taskId,int taskSTime,int taskRTime,String taskNote) {
        Task task = taskMapper.queryTaskById(taskId);
        if(task.getTaskStatuId() != 2){
            return ResultConstant.NOT_STOP;
        }
        taskSTime = task.getTaskSTime()+taskSTime;
        System.out.println(taskSTime);
        task = new Task(taskId,taskSTime,taskRTime);
        if(taskMapper.toStopTask(task)!=0){
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new Date());
            String msg = datetime + "由 用户1 暂停";
            History history = new History(msg,task.getTaskId(),taskNote);
            taskMapper.insertHistory(history);
            return new Result().success(null,taskMapper.queryHistoryByTaskId(taskId));
        }else{
            return ResultConstant.ERROR;
        }
    }

    @Override
    public Result finishTask(int taskId, int taskSTime, int taskToUserId, int taskFinisherId, String taskNote) {
        Task task = taskMapper.queryTaskById(taskId);
        if(task.getTaskStatuId() == 2||task.getTaskStatuId() == 4){
            taskSTime = task.getTaskSTime()+taskSTime;
           Task newTask = new Task(taskId,taskToUserId,taskFinisherId,taskSTime);
           if(taskMapper.finishTask(newTask)!=0){
               SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String datetime = tempDate.format(new Date());
               String msg = datetime + "由 用户1 完成";
               History history = new History(msg,task.getTaskId(),taskNote);
               taskMapper.insertHistory(history);
               return new Result().success(null,taskMapper.queryHistoryByTaskId(taskId));
           }else{
               return ResultConstant.ERROR;
           }
        }else{
            return ResultConstant.NOT_FINISH;
        }

    }

    @Override
    public Result closeTask(int taskId, String taskNote) {
        Task task = taskMapper.queryTaskById(taskId);
        if(task.getTaskStatuId()==1||task.getTaskStatuId()==4){
            if(taskMapper.closeTask(taskId)!=0){
                SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String datetime = tempDate.format(new Date());
                String msg = datetime + "由 用户1 关闭";
                History history = new History(msg,task.getTaskId(),taskNote);
                taskMapper.insertHistory(history);
                return new Result().success(null,taskMapper.queryHistoryByTaskId(taskId));
            }else{
                return ResultConstant.ERROR;
            }
        }else{
            return ResultConstant.NOT_CLOSE;
        }
    }

    @Override
    public Result postTask(int taskId, int taskSTime, int taskRTime, String taskNote, int taskToUserId) {
        Task task = taskMapper.queryTaskById(taskId);
        if(task.getTaskStatuId()==3||task.getTaskStatuId()==5){
            return ResultConstant.NOT_POST;
        }
        taskSTime = task.getTaskSTime()+taskSTime;
        Task newTask = new Task(taskId,taskToUserId,taskRTime,taskSTime,taskNote);
        if(taskMapper.postTask(newTask)!=0){
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String datetime = tempDate.format(new Date());
            String msg = datetime + "由 用户1 指派给了 用户" + taskToUserId;
            History history = new History(msg,task.getTaskId(),taskNote);
            taskMapper.insertHistory(history);
            return new Result().success(null,taskMapper.queryHistoryByTaskId(taskId));
        }else{
            return ResultConstant.ERROR;
        }
    }
}
