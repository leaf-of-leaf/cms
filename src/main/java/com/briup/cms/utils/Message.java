package com.briup.cms.utils;
/**
 * 自定义相应返回类型
 * @author kj
 *
 * */
public class Message<T> {
    /**
     * 状态码
     * */
    private Integer status;
    /**
     * 返回信息
     * */
    private String message;
    /**
     * 返回的数据类
     * */
    private T data;
    /**
     * 返回的时间
     * */
    private Long time;

    public Message(Integer status, String message, T data, Long time) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.time = time;
    }
    public Message(){

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
