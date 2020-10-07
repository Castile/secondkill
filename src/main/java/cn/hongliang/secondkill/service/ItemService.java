package cn.hongliang.secondkill.service;

import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.service.model.ItemModel;

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

}
