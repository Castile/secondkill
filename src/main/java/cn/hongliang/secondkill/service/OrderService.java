package cn.hongliang.secondkill.service;

import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.service.model.OrderModel;

/**
 * @author Hongliang Zhu
 * @create 2020-10-07 15:50
 */
public interface OrderService {

    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;


}
