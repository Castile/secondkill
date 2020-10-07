package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.ItemStockDO;
import cn.hongliang.secondkill.dataobject.ItemStockDOExample;

public interface ItemStockDOMapper {
    long countByExample(ItemStockDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemStockDO record);

    int insertSelective(ItemStockDO record);

    ItemStockDO selectByPrimaryKey(Integer id);

    ItemStockDO selectByItemId(Integer itemId);


    int updateByPrimaryKeySelective(ItemStockDO record);

    int updateByPrimaryKey(ItemStockDO record);
}