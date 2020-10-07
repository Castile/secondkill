package cn.hongliang.secondkill.validator;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hongliang Zhu
 * @Date 2020-08-21-0:31
 */
public class ValidationResult {

    // 检验结果是否有错误
    private boolean isHasError = false;

    // 存放错误的信息
    private Map<String, String> errorMsgMap = new HashMap<>();

    public boolean isHasError() {
        return isHasError;
    }

    public void setHasError(boolean hasError) {
        isHasError = hasError;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    // 定义通用的通过格式化字符串信息获取错误结果的msg方法
    public String getErrMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(), ",");
    }



}
