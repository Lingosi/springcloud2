package org.commons.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int code;
    protected String msg;

    public BusinessException(int code) {
        this(code, "msg." + code, (Throwable)null);
    }

    public BusinessException(String msg) {
        this(500, msg, (Throwable)null);
    }

    public BusinessException(int code, String msg) {
        this(code, msg, (Throwable)null);
    }

    public BusinessException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.msg;
    }

}
