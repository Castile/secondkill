package cn.hongliang.secondkill.response;

/**
 * @author Hongliang Zhu
 * @create 2020-08-18 0:19
 */
public class CommonReturnType {

    /**
     * 如果status=success， 则data内返回前端需要的json数据，如果status=fail，则data里面是统一的错误码格式。
     */
    private String status;
    private Object data;

    // 定義一個通用的方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success");
    }
    public static CommonReturnType create(Object result, String status){
        CommonReturnType commonReturnType = new CommonReturnType();
        commonReturnType.setData(result);
        commonReturnType.setStatus(status);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
