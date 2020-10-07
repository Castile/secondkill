package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.UserPasswordDO;
import cn.hongliang.secondkill.dataobject.UserPasswordDOExample;

public interface UserPasswordDOMapper {
    long countByExample(UserPasswordDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPasswordDO record);

    int insertSelective(UserPasswordDO record);

    UserPasswordDO selectByPrimaryKey(Integer id);

    UserPasswordDO selectByUserId(Integer id);

    int updateByPrimaryKeySelective(UserPasswordDO record);

    int updateByPrimaryKey(UserPasswordDO record);


}