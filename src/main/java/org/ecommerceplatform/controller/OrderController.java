package org.ecommerceplatform.controller;

import org.ecommerceplatform.controller.viewobject.OrderVO;
import org.ecommerceplatform.error.BusinessException;
import org.ecommerceplatform.error.EmBusinessError;
import org.ecommerceplatform.response.CommonReturnType;
import org.ecommerceplatform.service.OrderService;
import org.ecommerceplatform.service.model.OrderModel;
import org.ecommerceplatform.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", originPatterns = "*")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/createorder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,
                                        @RequestParam(name = "amount")Integer amount) throws BusinessException {
        Boolean isLogin = (Boolean)httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, amount);
        return CommonReturnType.create(orderModel);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listOrder() {
        List<OrderModel> orderModelList = orderService.listOrder();
        List<OrderVO> orderVOList = orderModelList.stream().map(orderModel -> {
            OrderVO orderVO = this.convertVOFromModel(orderModel);
            return orderVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(orderVOList);
    }

    private OrderVO convertVOFromModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderModel, orderVO);
        return orderVO;
    }
}
