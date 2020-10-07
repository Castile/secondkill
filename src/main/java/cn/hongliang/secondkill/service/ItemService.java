package cn.hongliang.secondkill.service;

import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.service.model.ItemModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Hongliang Zhu
 * @Date 2020-08-21-21:23
 */
public interface ItemService {

    // 创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    // 商品列表浏览
    List<ItemModel> listItem();

    // 商品详情浏览
    ItemModel getItem(Integer idd);

    // 库存扣减
    boolean decreaseStock(Integer itemId, Integer amount);

    // 商品销量增加
    void increaseSales(Integer itemId, Integer amount);



}
