package cn.hongliang.secondkill.error;

/**
 * @author Hongliang Zhu
 * @create 2020-08-18 0:43
 */
public interface CommonError {

    int getErrorCode();
    String  getErrMsg();
    CommonError setErrMsg(String errMsg);

}
