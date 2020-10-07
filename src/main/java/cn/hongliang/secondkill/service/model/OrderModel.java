package cn.hongliang.secondkill.service.model;

import java.math.BigDecimal;

/**
 * @author Hongliang Zhu
 * @create 2020-10-07 15:29
 * 用户下单的交易模型
 */
public class OrderModel {


    // 交易号
    // 202012221001234
    private String id;

    // 用户id
    private Integer userId;

    // 商品id
    private Integer itemId;

    // 商品的的单价
    private BigDecimal itemPrice;

    // 购买金额
    private BigDecimal orderPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
