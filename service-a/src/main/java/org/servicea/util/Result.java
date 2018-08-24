package org.servicea.util;

public class Result<T> {
	private String code;
    private String msg;
    private T data;

    public Result(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg, T data) {
        super();
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
}
