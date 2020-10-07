package cn.hongliang.secondkill.service.impl;

import cn.hongliang.secondkill.dao.ItemDOMapper;
import cn.hongliang.secondkill.dao.ItemStockDOMapper;
import cn.hongliang.secondkill.dataobject.ItemDO;
import cn.hongliang.secondkill.dataobject.ItemStockDO;
import cn.hongliang.secondkill.error.EmBusinessError;
import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.service.ItemService;
import cn.hongliang.secondkill.service.model.ItemModel;
import cn.hongliang.secondkill.validator.ValidationResult;
import cn.hongliang.secondkill.validator.ValidatorImpl;
import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hongliang Zhu
 * @Date 2020-08-21-21:25
 */
@SuppressWarnings("ALL")
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    /**
     * 将ItemModel转成ItemDO
     * @param itemModel  itemModel
     * @return
     */
    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        // 设置价格
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }
    private ItemStockDO convertItemStockFromItemModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    /**
     * 创建商品
     * @param itemModel
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        // 校验入参
        ValidationResult validationResult = validator.validate(itemModel);
        if(validationResult.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR, validationResult.getErrMsg());

        }

        // 转化itemmodel -> dataObject
        ItemDO itemDO = convertItemDOFromItemModel(itemModel);

        // 写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO = this.convertItemStockFromItemModel(itemModel);

        // 入库
        itemStockDOMapper.insertSelective(itemStockDO);

        // 返回创建完成的对象

        return this.getItem(itemModel.getId());

    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = this.convertModelFromDataObject(itemDO, itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());

        return itemModelList;
    }

    @Override
    public ItemModel getItem(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if(itemDO == null){
            return null;
        }
        // 操作获得库存数量
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        // 将dataobject --> model
        ItemModel itemModel = convertModelFromDataObject(itemDO, itemStockDO);
        return itemModel;
    }

    /**
     * 库建库存
     * @param itemId
     * @param amount
     * @return
     */
    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int affectedRow = itemStockDOMapper.decreaseStock(itemId, amount);
        if(affectedRow > 0){
            // 更新库存成功
            return true;
        }
        // 更新库存失败
        return false;
    }


    /**
     * 商品销量的增加
     * @param itemId
     * @param amount
     * @throws BusinessException
     */
    @Override
    @Transactional
    public void increaseSales(Integer id, Integer amount) {
        itemDOMapper.increaseSales(id, amount);
    }

    /**
     * 将数据模型转换为model
     * @param itemDO
     * @return
     */
    private ItemModel convertModelFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;




    }


}
