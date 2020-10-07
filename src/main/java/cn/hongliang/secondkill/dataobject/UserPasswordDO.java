package cn.hongliang.secondkill.dataobject;

public class UserPasswordDO {
    private Integer id;

    private String encriptPassword;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEncriptPassword() {
        return encriptPassword;
    }

    public void setEncriptPassword(String encriptPassword) {
        this.encriptPassword = encriptPassword == null ? null : encriptPassword.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}