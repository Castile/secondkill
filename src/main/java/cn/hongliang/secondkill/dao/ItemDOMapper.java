package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.ItemDO;
import cn.hongliang.secondkill.dataobject.ItemDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDOMapper {
    long countByExample(ItemDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemDO record);

    List<ItemDO> listItem();

    int increaseSales(@Param("id")Integer id,  @Param("amount")Integer amount);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
}