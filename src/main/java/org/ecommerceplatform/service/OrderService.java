package org.ecommerceplatform.service;

import org.ecommerceplatform.error.BusinessException;
import org.ecommerceplatform.service.model.OrderModel;

import java.util.List;

public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
    List<OrderModel> listOrder();
}
