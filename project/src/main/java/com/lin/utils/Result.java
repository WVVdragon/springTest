package com.lin.utils;

/**
 * @author LYN
 */
public class Result {
    private static final String OK = "ok";
    private int code;
    private String msg;
    private Object data1;
    private Object data2;


    /**
     * show 成功结果（无内容）
     * @return Result
     */
    public Result success(){
        code = 200;
        msg = OK;
        return this;
    }

    /**
     * show 成功结果（有内容）
     * @return Result
     */
    public Result success(Object data1,Object data2){
        this.code = 200;
        this.msg = OK;
        this.data1 = data1;
        this.data2 = data2;
        return this;
    }

    /**
     * show  失败结果
     * @return Result
     */
    public Result failure(){
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData1() {
        return data1;
    }

    public void setData(Object data1) {
        this.data1 = data1;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }
}
