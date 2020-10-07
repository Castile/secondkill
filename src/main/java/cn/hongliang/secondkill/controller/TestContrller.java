package cn.hongliang.secondkill.controller;

import cn.hongliang.secondkill.dao.UserDOMapper;
import cn.hongliang.secondkill.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hongliang Zhu
 * @create 2020-08-17 22:37
 */
@RestController
public class TestContrller {

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/home")
    public String home(){
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if(userDO == null){
            return "用户对象不存在";
        }else{
            return userDO.getName();
        }
    }

}
