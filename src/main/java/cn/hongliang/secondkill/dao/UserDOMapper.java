package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.UserDO;
import cn.hongliang.secondkill.dataobject.UserDOExample;

public interface UserDOMapper {
    long countByExample(UserDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByTelephone(String telephone);

    UserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}