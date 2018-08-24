package org.servicea.util;

import org.apache.http.HttpStatus;

public class ResultUtil {
	public static String PARAM_ERROR = "参数错误";
	
	public static Result SUCCESS() {
        return new Result<>(String.valueOf(HttpStatus.SC_OK), "操作成功.");
    }
	
    public static Result SUCCESS(Object data) {
        return new Result<>(String.valueOf(HttpStatus.SC_OK), "操作成功.", data);
    }

    public static Result ERROR(String code, String msg) {
        return new Result<>(code, msg);
    }
    
    public static Result ERROR(String code, String msg, Object data) {
        return new Result<Object>(code, msg, data);
    }
}
