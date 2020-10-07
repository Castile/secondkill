package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.ItemDO;
import cn.hongliang.secondkill.dataobject.ItemDOExample;

import java.util.List;

public interface ItemDOMapper {
    long countByExample(ItemDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemDO record);

    List<ItemDO> listItem();

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
}