package cn.hongliang.secondkill.service.impl;
import cn.hongliang.secondkill.dao.UserDOMapper;
import cn.hongliang.secondkill.dao.UserPasswordDOMapper;
import cn.hongliang.secondkill.dataobject.UserDO;
import cn.hongliang.secondkill.dataobject.UserPasswordDO;
import cn.hongliang.secondkill.error.EmBusinessError;
import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.service.UserService;
import cn.hongliang.secondkill.service.model.UserModel;
import cn.hongliang.secondkill.validator.ValidationResult;
import cn.hongliang.secondkill.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @author Hongliang Zhu
 * @create 2020-08-17 23:14
 */
@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO ==null){
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        return convetUserDOToUserModel(userDO, userPasswordDO);
    }

    /**
     * 用户注册
     * @param userModel
     */
    @Override
    public void register(UserModel userModel) throws BusinessException {
        if(userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR);
        }


//        if(StringUtils.isEmpty(userModel.getName())
//            || userModel.getAge() == null
//            || userModel.getGender() == null
//            || StringUtils.isEmpty(userModel.getTelephone())){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR);
//        }
        ValidationResult validationResult = validator.validate(userModel);
        if(validationResult.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR, validationResult.getErrMsg());

        }
        // 实现model-> userDO的转换
        UserDO userDO = convertFromModel(userModel);

        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException e) {
           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR, "该手机号已注册");
        }
        userModel.setId(userDO.getId());
        // 密码
        UserPasswordDO userPasswordDO = convetPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);

        return;

    }

    /**
     * 用户登录校验
     * @param password 加密后的密码
     * @param telephone 用户注册的手机号
     * @return
     */
    @Override
    public UserModel validateLogin(String telephone, String encriptPassword) throws BusinessException {
        // 获取用户的信息
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if(userDO == null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        // 拿到密码
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convetUserDOToUserModel(userDO, userPasswordDO);
        // 校验密码
        if(!StringUtils.equals(encriptPassword, userModel.getEncriptPassword())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);

        }
        return userModel;

    }

    /**
     * 密码处理
     * @param userModel
     * @return
     */
    private UserPasswordDO convetPasswordFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncriptPassword(userModel.getEncriptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }


    /**
     * 将UserModel对象转换成 UserDO
     * @param userModel
     * @return
     */
    private UserDO convertFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;

    }
    /**
     * 将userDO 对象转换成UserModel
     * @param userDO
     * @param userPasswordDO
     * @return
     */
    private UserModel convetUserDOToUserModel(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if(userPasswordDO != null){

            userModel.setEncriptPassword(userPasswordDO.getEncriptPassword());
        }
        return userModel;

    }

}
