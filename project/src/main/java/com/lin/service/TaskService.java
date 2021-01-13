package com.lin.service;

import com.lin.pojo.Task;
import com.lin.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LYN
 */
public interface TaskService {
    /**
     * show 添加一个任务，状态为<未启动>
     * @param task
     * @return
     */
    Result addTask(Task task);

    /**
     * show 查询所有任务
     * @return
     */
    Result queryAllTask();

    /**
     * show 根据id查询任务
     * @param taskId
     * @return
     */
    Result queryTaskById(int taskId);

    /**
     * show 开启项目
     * @param taskId
     * @param taskETime
     * @param taskSTime
     * @param taskNote
     * @return
     */
    Result toStartTask(int taskId,int taskSTime,int taskETime,String taskNote);

    /**
     * show 添加 创建项目的历史
     * @param taskId
     * @return
     */
    void createNote(int taskId);

    /**
     * show 编辑项目
     * @param task
     * @return
     */
    Result updateTask(Task task);

    /**
     * show 暂停项目
     * @param taskNote
     * @param taskSTime
     * @param taskETime
     * @param taskId
     * @return
     */
    Result toStopTask(int taskId,int taskSTime,int taskETime,String taskNote);

    /**
     * show 完成项目
     * @param taskId
     * @param taskSTime
     * @param taskToUserId
     * @param taskFinisherId
     * @param taskNote
     * @return
     */
    Result finishTask(int taskId,int taskSTime,int taskToUserId,int taskFinisherId,String taskNote);

    /**
     * show 关闭任务
     * @param taskId
     * @param taskNote
     * @return
     */
    Result closeTask(int taskId,String taskNote);

    /**
     * show 指派任务
     * @param taskId
     * @param taskSTime
     * @param taskETime
     * @param taskNote
     * @param taskToUserId
     * @return
     */
    Result postTask(int taskId,int taskSTime,int taskETime,String taskNote,int taskToUserId);

}
