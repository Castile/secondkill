package cn.hongliang.secondkill.controller;

import cn.hongliang.secondkill.controller.viewmodel.UserVO;
import cn.hongliang.secondkill.error.EmBusinessError;
import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.response.CommonReturnType;
import cn.hongliang.secondkill.service.UserService;
import cn.hongliang.secondkill.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Hongliang Zhu
 * @create 2020-08-17 22:45
 */
@SuppressWarnings("ALL")
@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login",  method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone")String telephone,
                                  @RequestParam(name = "password")String password) throws BusinessException, NoSuchAlgorithmException {
        // 入参校验
        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR);
        }
        // 调用用户登录服务，用来校验用户登录 是否合法
        UserModel userModel = userService.validateLogin(telephone, EncodeByMD5(password));
        // 加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }


    /**
     * 用户注册接口
     * @param telephone
     * @param otpCode
     * @param age
     * @param name
     * @param gender
     * @param password
     * @return
     * @throws BusinessException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/regist",  method = {RequestMethod.POST})
    @ResponseBody
    @Transactional
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    public CommonReturnType register(
            @RequestParam(name = "telephone", required = false)String telephone,
            @RequestParam(name = "otpCode")String otpCode,
            @RequestParam(name = "age")Integer age,
            @RequestParam(name = "name")String name,
            @RequestParam(name = "gender")Integer gender,
            @RequestParam(name = "password")String password) throws BusinessException, NoSuchAlgorithmException {

        // 验证手机号与对应的OTPCode相符合
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);

        if(!StringUtils.equals(otpCode, inSessionOtpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR, "短信验证码错误");
        }

        UserModel userModel = new UserModel();
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setName(name);
        userModel.setTelephone(telephone);
        userModel.setEncriptPassword(EncodeByMD5(password));
        userModel.setRegistMode("by phone");
        // 用户注册流程
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    /**
     * md5加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String EncodeByMD5(String str) throws NoSuchAlgorithmException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder encoder = new BASE64Encoder();
        // 加密字符串
        String newStr = encoder.encode(md5.digest(str.getBytes()));
        return newStr;
    }


    /**
     * 用户获取otp短信接口
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam("telephone")String telephone){
        // 需要按照一定的规则去生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);
        // 将OTP验证码同对应用户的手机号关联  httpsession
        httpServletRequest.getSession().setAttribute(telephone, otpCode);
        // 将OTP验证码通过短信通道发送给用户： 省略
        System.out.println("telephone:" +telephone +"& otpCode:" + otpCode);
        return CommonReturnType.create(null);
    }




    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(Integer id) throws BusinessException {
        // 调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);
        if(userModel == null){
            // 用户信息不存在
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convetFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convetFromModel(UserModel userModel){
        if (userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;

    }



}
