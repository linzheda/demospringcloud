package com.linzd.basecore.common.entity;

import java.io.Serializable;

/**
 * 描述 响应结果返回类
 *
 * @author Lorenzo Lin
 * @created 2017年11月08日 9:34
 */
public class ResultPojo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码  如果成功为200  失败 500
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultPojo success(Object data) {
        ResultPojo result = new ResultPojo();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static ResultPojo success(String msg, Object data) {
        ResultPojo result = new ResultPojo();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static ResultPojo success(String msg) {
        return success(msg, null);
    }

    public static ResultPojo success() {
        return success("成功");
    }

    public static ResultPojo error(String msg) {
        ResultPojo result = new ResultPojo();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static ResultPojo error(Integer code, String msg) {
        ResultPojo result = new ResultPojo();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
