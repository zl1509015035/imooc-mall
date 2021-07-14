package com.imooc.mall.exception;

import com.imooc.mall.common.ApiRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：    处理统一异常的handler
 */
//拦截异常
@ControllerAdvice
@Slf4j
public class GloalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GloalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        logger.error("Default Exception:", e);
        return ApiRestResponse.error(ImoocMallExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(ImoocMallException.class)
    @ResponseBody
    public Object handleImoocMallException(ImoocMallException e) {
        logger.error("ImoocMallException:", e);
        return ApiRestResponse.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiRestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("MethodArgumentNotValidException:", e);
        return handleBindingResult(e.getBindingResult());
    }

    private ApiRestResponse handleBindingResult(BindingResult result) {
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            // itli
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (list.size() == 0) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ApiRestResponse.error(ImoocMallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }

    /**
     * 方法参数校验
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ApiRestResponse handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        return ApiRestResponse.error(ImoocMallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ValidationException
     *
     * <p>对应@RequestBody传参方式
     */
    @ExceptionHandler(ValidationException.class)
    public ApiRestResponse handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return ApiRestResponse.error(ImoocMallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     *
     * <p>对应@RequestBody传参方式
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiRestResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return ApiRestResponse.error(ImoocMallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), e.getMessage());
    }


}
