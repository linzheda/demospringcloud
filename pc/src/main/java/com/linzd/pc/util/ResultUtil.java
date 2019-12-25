package com.linzd.pc.util;

import java.io.Serializable;

/**
 * 描述 响应结果返回工具类
 *
 * @author Lorenzo Lin
 * @created 2017年11月08日 9:34
 */
public class ResultUtil<T> implements Serializable {
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

    public static ResultUtil success(Object data) {
        ResultUtil result = new ResultUtil();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static ResultUtil success(String msg, Object data) {
        ResultUtil result = new ResultUtil();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static ResultUtil success(String msg) {
        return success(msg, null);
    }

    public static ResultUtil success() {
        return success("成功");
    }

    public static ResultUtil error(String msg) {
        ResultUtil result = new ResultUtil();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static ResultUtil error(Integer code, String msg) {
        ResultUtil result = new ResultUtil();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
