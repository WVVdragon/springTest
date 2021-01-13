package com.lin.controller;

import com.lin.pojo.History;
import com.lin.pojo.Task;
import com.lin.service.TaskService;
import com.lin.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author LYN
 */
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value="/task/insert",method = RequestMethod.POST)
    @ResponseBody
    public Result addTask(@RequestParam("taskName") String taskName, @RequestParam("taskTypeId") int taskTypeId, @RequestParam("taskPiroId") int taskPiroId, @RequestParam("taskToUserId") int taskToUserId, @RequestParam("taskDesc") String taskDesc,@RequestParam("taskETime") int taskETime, @RequestParam("taskStartDate") Date taskStartDate, @RequestParam("taskStopDate") Date taskStopDate){
        int taskStatuId = 3;
        int taskSTime = 0;
        int taskRTime = taskETime;
        Date taskCreateTime = new Date();
        Task task = new Task(taskName,taskTypeId,taskPiroId,taskStatuId,taskToUserId,taskDesc,taskETime,taskSTime,taskRTime,taskStartDate,taskStopDate,taskCreateTime);
        return taskService.addTask(task);
    }

    @GetMapping("/task/allTask")
    @ResponseBody
    public Result queryAllTask(){
        return taskService.queryAllTask();
    }

    @GetMapping("/task/oneTask/{taskId}")
    public Result queryTaskById(@PathVariable("taskId") int taskId){
        return taskService.queryTaskById(taskId);
    }

    @PostMapping("/task/toStart")
    public Result toStartTask(@RequestParam("taskId") int taskId, @RequestParam("taskSTime") int taskSTime, @RequestParam("taskRTime") int taskRTime, @RequestParam("taskNote") String taskNote){
        return taskService.toStartTask(taskId,taskSTime,taskRTime,taskNote);
    }

    @RequestMapping(value="/task/update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateTask(@RequestParam("taskId")int taskId,@RequestParam("taskName") String taskName, @RequestParam("taskTypeId") int taskTypeId, @RequestParam("taskPiroId") int taskPiroId, @RequestParam("taskToUserId") int taskToUserId, @RequestParam("taskDesc") String taskDesc, @RequestParam("taskNote") String taskNote){
        Task task = new Task(taskId,taskName,taskTypeId,taskPiroId,taskToUserId,taskDesc,taskNote);
        return taskService.updateTask(task);
    }

    @PostMapping("/task/toStop")
    public Result toStopTask(@RequestParam("taskId") int taskId, @RequestParam("taskSTime") int taskSTime, @RequestParam("taskRTime") int taskRTime, @RequestParam("taskNote") String taskNote){
        return taskService.toStopTask(taskId,taskSTime,taskRTime,taskNote);
    }

    @PostMapping("/task/finish")
    public Result finishTask(@RequestParam("taskId") int taskId, @RequestParam("taskSTime") int taskSTime, @RequestParam("taskNote") String taskNote, @RequestParam("taskToUserId") int taskToUserId){
        int finisherId = 1;
        return taskService.finishTask(taskId,taskSTime,taskToUserId,finisherId,taskNote);
    }

    @PostMapping("/task/close")
    public Result closeTask(@RequestParam("taskId") int taskId, @RequestParam("taskNote") String taskNote){
        return taskService.closeTask(taskId,taskNote);
    }

    @PostMapping("/task/post")
    public Result postTask(@RequestParam("taskId") int taskId, @RequestParam("taskSTime") int taskSTime, @RequestParam("taskRTime") int taskRTime, @RequestParam("taskNote") String taskNote, @RequestParam("taskToUserId") int taskToUserId){
        return taskService.postTask(taskId,taskSTime,taskRTime,taskNote,taskToUserId);
    }
}
