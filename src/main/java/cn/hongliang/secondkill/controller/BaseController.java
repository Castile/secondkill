package cn.hongliang.secondkill.controller;

import cn.hongliang.secondkill.error.EmBusinessError;
import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hongliang Zhu
 * @create 2020-08-18 1:35
 */
@Controller
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/json";


    // 定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK) // 返回一个200 的代码
    public Object handlerException(HttpServletRequest request, Exception exception){
        Map<String, Object> responseDate = new HashMap<>();
        if(exception instanceof BusinessException){
            BusinessException  businessException = (BusinessException) exception;
            responseDate.put("errCode", businessException.getErrorCode());
            responseDate.put("errMsg", businessException.getErrMsg());
        }else{
            responseDate.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            responseDate.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());

        }

        return CommonReturnType.create(responseDate, "fail");
    }


}
