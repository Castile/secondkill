package cn.hongliang.secondkill.dao;

import cn.hongliang.secondkill.dataobject.SequenceDO;
import cn.hongliang.secondkill.dataobject.SequenceDOExample;

public interface SequenceDOMapper {
    long countByExample(SequenceDOExample example);

    int deleteByPrimaryKey(String name);

    int insert(SequenceDO record);

    SequenceDO getSequenceByName(String name);

    int insertSelective(SequenceDO record);

    SequenceDO selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(SequenceDO record);

    int updateByPrimaryKey(SequenceDO record);
}