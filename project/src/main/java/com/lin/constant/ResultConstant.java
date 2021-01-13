package com.lin.constant;

import com.lin.utils.Result;

/**
 * @author LYN
 */
public class ResultConstant {

    public final static Result SUCCESS = new Result(200, "ok");
    public final static Result ERROR = new Result(1001,"操作失败，请重试！");
    public final static Result TASK_NULL = new Result(1002,"任务列表为空！");
    public final static Result STARTED = new Result(1003,"该任务已启动!");
    public final static Result NOT_STOP = new Result(1004,"无法暂停!");
    public final static Result NOT_FINISH = new Result(1005,"无法完成!");
    public final static Result NOT_CLOSE = new Result(1006,"无法关闭!");
    public final static Result NOT_POST = new Result(1007,"无法指派!");
    public final static Result TIME_ERROR = new Result(1008,"时间设置错误!");


}

