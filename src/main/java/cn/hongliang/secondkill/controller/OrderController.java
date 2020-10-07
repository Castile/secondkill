package cn.hongliang.secondkill.controller;

import cn.hongliang.secondkill.error.EmBusinessError;
import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.response.CommonReturnType;
import cn.hongliang.secondkill.service.OrderService;
import cn.hongliang.secondkill.service.model.OrderModel;
import cn.hongliang.secondkill.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hongliang Zhu
 * @create 2020-10-07 17:05
 */
@Controller
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest request;


    // 封装下单请求
    @RequestMapping(value = "/createorder",  method = {RequestMethod.POST})
    @ResponseBody
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,
                                          @RequestParam(name = "amount")Integer amount) throws BusinessException {

        // 获取用户的登录信息
        Boolean is_login = (Boolean) request.getSession().getAttribute("IS_LOGIN");
        if(is_login == null || !is_login.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }

        // 获取登录用户的信息
        UserModel login_user = (UserModel) request.getSession().getAttribute("LOGIN_USER");
        OrderModel orderModel = orderService.createOrder(login_user.getId(), itemId, amount);
        return CommonReturnType.create(orderModel);
    }

}
