package com.lin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LYN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int taskId;
    private String taskName;
    private int taskTypeId;
    private int taskPiroId;
    private int taskStatuId;
    private int taskToUserId;
    private int taskFinisherId;
    private String taskDesc;
    /**
     * 预计时长
     */
    private int taskETime;
    /**
     * 实际时长
     */
    private int taskSTime;
    /**
     * 剩余时长
     */
    private int taskRTime;
    private Date taskStartDate;
    private Date taskStopDate;
    private String taskNote;
    private Date taskCreateTime;

    public Task(String taskName, int taskTypeId, int taskPiroId, int taskStatuId, int taskToUserId, String taskDesc, int taskETime,int taskSTime,int taskRTime, Date taskStartDate, Date taskStopDate,Date taskCreateTime) {
        this.taskName = taskName;
        this.taskTypeId = taskTypeId;
        this.taskPiroId = taskPiroId;
        this.taskStatuId = taskStatuId;
        this.taskToUserId = taskToUserId;
        this.taskDesc = taskDesc;
        this.taskETime = taskETime;
        this.taskSTime = taskSTime;
        this.taskRTime = taskRTime;
        this.taskStartDate = taskStartDate;
        this.taskStopDate = taskStopDate;
        this.taskCreateTime = taskCreateTime;
    }

    public Task(int taskId, int taskSTime, int taskRTime) {
        this.taskId = taskId;
        this.taskSTime = taskSTime;
        this.taskRTime = taskRTime;
    }

    public Task(int taskId,String taskName, int taskTypeId, int taskPiroId, int taskToUserId, String taskDesc, String taskNote) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskTypeId = taskTypeId;
        this.taskPiroId = taskPiroId;
        this.taskToUserId = taskToUserId;
        this.taskDesc = taskDesc;
        this.taskNote = taskNote;
    }

    public Task(int taskId, int taskToUserId, int taskFinisherId, int taskSTime) {
        this.taskId = taskId;
        this.taskToUserId = taskToUserId;
        this.taskFinisherId = taskFinisherId;
        this.taskSTime = taskSTime;
    }

    public Task(int taskId, int taskToUserId, int taskRTime, int taskSTime, String taskNote) {
        this.taskId = taskId;
        this.taskToUserId = taskToUserId;
        this.taskRTime = taskRTime;
        this.taskSTime = taskSTime;
        this.taskNote = taskNote;
    }
}
