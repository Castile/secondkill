package cn.hongliang.secondkill.service;

import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.service.model.UserModel;

/**
 * @author Hongliang Zhu
 * @create 2020-08-17 23:10
 */
public interface UserService {

    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String telephone, String encriptPassword) throws BusinessException;

}
