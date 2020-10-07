package cn.hongliang.secondkill.controller;

import cn.hongliang.secondkill.controller.viewmodel.ItemVO;
import cn.hongliang.secondkill.dataobject.ItemDO;
import cn.hongliang.secondkill.exception.BusinessException;
import cn.hongliang.secondkill.response.CommonReturnType;
import cn.hongliang.secondkill.service.ItemService;
import cn.hongliang.secondkill.service.model.ItemModel;
import org.apache.commons.lang3.math.IEEE754rUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hongliang Zhu
 * @create 2020-10-06 21:17
 */
@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;


    /**
     * 创建商品
     * @param title
     * @param description
     * @param price
     * @param stock
     * @param imgUrl
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/create",  method = {RequestMethod.POST})
    @ResponseBody
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    public CommonReturnType createItem(@RequestParam(name= "title")String title,
                                       @RequestParam(name= "description")String description,
                                       @RequestParam(name= "price") BigDecimal price,
                                       @RequestParam(name= "stock")Integer stock,
                                       @RequestParam(name= "imgUrl")String imgUrl) throws BusinessException {

        // 封装service请求用来创建商品
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setDescription(description);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);
        ItemModel item = itemService.createItem(itemModel);
        // 返回给前端的是ItemVO
        ItemVO itemVO = convertVOFromModel(item);

        return CommonReturnType.create(itemVO);
    }

    private ItemVO convertVOFromModel(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        return itemVO;

    }

    /**
     * 商品详情页的浏览
     */
    @RequestMapping(value = "/get",  method = {RequestMethod.GET})
    @ResponseBody
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id){
        ItemModel item = itemService.getItem(id);
        ItemVO itemVO = convertVOFromModel(item);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/list",  method = {RequestMethod.GET})
    @ResponseBody
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
    public CommonReturnType listItem(){
        List<ItemModel> itemModels = itemService.listItem();
        List<ItemVO> collect = itemModels.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(collect);
    }




    }
