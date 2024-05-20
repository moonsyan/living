package com.hspedu.hspliving.commodity.exception;

import com.hspedu.common.exception.HspLivingCodeEnum;
import com.hspedu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ming
 * @Description
 * @projectName living
 * @create 2024-05-04 8:10
 */

/**
 * @ResponseBody 表示以json格式返回信息
 */
@Slf4j
@ResponseBody
@ControllerAdvice(basePackages = {"com.hspedu.hspliving.commodity.controller"})
public class HspLivingExceptionControllerAdvice {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R handleValidException(MethodArgumentNotValidException e){

//        log.info("数据校验出现问题{)异常类型是{}",e.getMessage(),e.getClass() );

        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> map = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return R.error(HspLivingCodeEnum.INVALID_EXCEPTION.getCode(), HspLivingCodeEnum.INVALID_EXCEPTION.getMsg()).put("data",map );
    }


    @ExceptionHandler({Throwable.class})
    public R handleException(Throwable throwable) {

        return R.error(HspLivingCodeEnum.UNKNOWN_EXCEPTION.getCode(), HspLivingCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
