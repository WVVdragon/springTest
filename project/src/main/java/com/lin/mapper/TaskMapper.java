package com.lin.mapper;

import com.lin.pojo.History;
import com.lin.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LYN
 */
@Mapper
@Repository
public interface TaskMapper {

    int addTask(Task task);

    List<Task> queryAllTask();

    List<Task> queryInTask();

    int updateETime(int ETime,int taskId);

    Task queryTaskById(int taskId);

    List<History> queryHistoryByTaskId(int taskId);

    int insertHistory(History history);

    int toStartTask(Task task);

    int updateTask(Task task);

    int toStopTask(Task task);

    int finishTask(Task task);

    int closeTask(int taskId);

    int postTask(Task task);
}
