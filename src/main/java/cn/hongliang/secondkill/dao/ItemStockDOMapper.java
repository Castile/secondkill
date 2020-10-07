package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.ItemStockDO;
import cn.hongliang.secondkill.dataobject.ItemStockDOExample;
import org.apache.ibatis.annotations.Param;

public interface ItemStockDOMapper {
    long countByExample(ItemStockDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemStockDO record);

    int insertSelective(ItemStockDO record);

    ItemStockDO selectByPrimaryKey(Integer id);

    ItemStockDO selectByItemId(Integer itemId);

    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount")Integer amount);


    int updateByPrimaryKeySelective(ItemStockDO record);

    int updateByPrimaryKey(ItemStockDO record);
}