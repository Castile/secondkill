package cn.hongliang.secondkill.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Hongliang Zhu
 * @create 2020-08-17 23:10
 */
public class UserModel {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotNull(message = "年龄不能不填")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄必须小于150岁")
    private Integer age;

    @NotNull(message = "性别不能不填")
    private Byte gender;

    @NotBlank(message = "手机号不能为空")
    private String telephone;
    private String registMode;
    private String thirdPartyId;

    @NotBlank(message = "密码不能为空")
    private String encriptPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegistMode() {
        return registMode;
    }

    public void setRegistMode(String registMode) {
        this.registMode = registMode;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getEncriptPassword() {
        return encriptPassword;
    }

    public void setEncriptPassword(String encriptPassword) {
        this.encriptPassword = encriptPassword;
    }
}
