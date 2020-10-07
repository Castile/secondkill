package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.OrderDO;
import cn.hongliang.secondkill.dataobject.OrderDOExample;

public interface OrderDOMapper {
    long countByExample(OrderDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}