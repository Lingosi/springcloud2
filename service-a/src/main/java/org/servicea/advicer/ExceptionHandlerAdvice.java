package org.servicea.advicer;

import org.servicea.util.Result;
import org.servicea.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@SuppressWarnings("rawtypes")
@ControllerAdvice(annotations = { RestController.class, Controller.class })
@ResponseBody
public class ExceptionHandlerAdvice implements ResponseBodyAdvice{
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@Override
	public Object beforeBodyWrite(Object arg0, MethodParameter arg1, MediaType arg2, Class arg3, ServerHttpRequest arg4,
			ServerHttpResponse arg5) {
		return arg0;
	}

	@Override
	public boolean supports(MethodParameter arg0, Class arg1) {
		return false;
	}

	@ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)	
    public Result handleResultException(Exception e) {//, HttpServletRequest request) {
    	logger.error("拦截异常：{}", e);
        return ResultUtil.ERROR("400", e.getMessage(), e);
    }
}
