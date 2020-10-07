package cn.hongliang.secondkill.error;

/**
 * @author Hongliang Zhu
 * @create 2020-08-18 0:46
 */
public enum EmBusinessError implements CommonError {

    /**
     * 通用的错误类型 以1000开头
     */
    PARAMETER_VALIDATIONERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),

    /**
     * 20000 開圖的為用戶信息相關的錯誤定義
     */
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户名或密码错误"),
    USER_NOT_LOGIN(20003, "用户还未登录"),


    // 30000 开头为交易型错误
    STOCK_NOT_ENOUGH(30001, "库存不足"),


    ;


    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误描述
     *
     */
    private String errMsg;

    EmBusinessError(int errorCode, String errMsg) {
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
