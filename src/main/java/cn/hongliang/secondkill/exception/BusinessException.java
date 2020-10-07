package cn.hongliang.secondkill.exception;

import cn.hongliang.secondkill.error.CommonError;


/**
 * @author Hongliang Zhu
 * @create 2020-08-18 0:53
 */
// 包装器业务异常类实现
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    // 直接接受EmBusinessError传参用于构造业务异常
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return this.commonError.setErrMsg(errMsg);
    }
}
