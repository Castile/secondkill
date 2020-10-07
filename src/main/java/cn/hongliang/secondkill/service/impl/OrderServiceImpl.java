package cn.hongliang.secondkill.service.impl;

import cn.hongliang.secondkill.dao.OrderDOMapper;
import cn.hongliang.secondkill.dao.SequenceDOMapper;
import cn.hongliang.secondkill.dataobject.OrderDO;
import cn.hongliang.secondkill.dataobject.SequenceDO;
import cn.hongliang.secondkill.error.EmBusinessError;
import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.service.ItemService;
import cn.hongliang.secondkill.service.OrderService;
import cn.hongliang.secondkill.service.UserService;
import cn.hongliang.secondkill.service.model.ItemModel;
import cn.hongliang.secondkill.service.model.OrderModel;
import cn.hongliang.secondkill.service.model.UserModel;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Hongliang Zhu
 * @create 2020-10-07 15:52
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {

        // 校验下单状态，下单的商品是否存在，用户是否合法，购买数量是否正确
        ItemModel item = itemService.getItem(itemId);
        if(item == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR, "商品信息不存在");
        }
        UserModel userById = userService.getUserById(userId);
        if(userById == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR, "用户信息不存在");

        }
        if(amount <= 0 || amount > 99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATIONERROR, "数量信息不存在");

        }

        // 落单减库存， 支付减库存
        boolean result = itemService.decreaseStock(itemId, amount);
        if(!result){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);

        }

        // 订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(item.getPrice());
        orderModel.setOrderPrice(item.getPrice().multiply(new BigDecimal(amount)));

        // 生成交易流水号
        orderModel.setId(generateOrderNo());
        OrderDO orderDO = this.convertOrderDOFromOrderModel(orderModel);
        // 处理金额问题
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        orderDOMapper.insertSelective(orderDO);


        // 加上商品的销量
        itemService.increaseSales(itemId, amount);

        // 返回前端

        return orderModel;

    }

    /**
     * 生成交易流水号
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
     String generateOrderNo(){
        // 16位
        StringBuilder stringBuilder = new StringBuilder();
        // 前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        java.lang.String nowDateTime = now.format(DateTimeFormatter.BASIC_ISO_DATE).replace("-", "");
        stringBuilder.append(nowDateTime);

        //中间6位为自增序列
        // 获取当前sequence
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);

        java.lang.String sequenceStr = java.lang.String.valueOf(sequence);
        // 补充6位
        for(int i = 0; i < 6 - sequenceStr.length(); i++){
            stringBuilder.append(0);
        }

        stringBuilder.append(sequenceStr);

        // 最后两位为分库分表位
        stringBuilder.append("00");
        return stringBuilder.toString();

    }

    private OrderDO convertOrderDOFromOrderModel(OrderModel orderModel){
        if(orderModel == null){
            return null;
        }

        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }
}
