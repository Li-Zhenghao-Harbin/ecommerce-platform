package org.ecommerceplatform.service.impl;

import org.ecommerceplatform.dao.OrderDOMapper;
import org.ecommerceplatform.dao.SequenceDOMapper;
import org.ecommerceplatform.dataobject.OrderDO;
import org.ecommerceplatform.dataobject.SequenceDO;
import org.ecommerceplatform.error.BusinessException;
import org.ecommerceplatform.error.EmBusinessError;
import org.ecommerceplatform.service.ItemService;
import org.ecommerceplatform.service.OrderService;
import org.ecommerceplatform.service.UserService;
import org.ecommerceplatform.service.WalletService;
import org.ecommerceplatform.service.model.ItemModel;
import org.ecommerceplatform.service.model.OrderModel;
import org.ecommerceplatform.service.model.UserModel;
import org.ecommerceplatform.service.model.WalletModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(itemId);
        WalletModel walletModel = walletService.getWalletById(userId);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }
        if (amount <= 0 || amount > 99) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "数量信息有误");
        }
        BigDecimal cost = itemModel.getPrice().multiply(new BigDecimal(amount));
        if (walletModel.getBalance().compareTo(cost) == -1) {
            throw new BusinessException(EmBusinessError.BALANCE_NOT_ENOUGH);
        }
        boolean result = itemService.decreaseStock(itemId, amount);
        if (!result) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setOrderPrice(itemModel.getPrice().multiply(new BigDecimal(amount)));
        orderModel.setId(generateOrderNo());
        OrderDO orderDO = convertFromModel(orderModel);
        orderDOMapper.insertSelective(orderDO);
        itemService.increaseSales(itemId, amount);
        walletService.decreaseBalance(userId, cost);
        return orderModel;
    }

    @Override
    public List<OrderModel> listOrder() {
        List<OrderDO> orderDOList = orderDOMapper.listOrder();
        List<OrderModel> orderModelList = orderDOList.stream().map(orderDO -> {
            OrderModel orderModel = this.convertModelFromDataObject(orderDO);
            return orderModel;
        }).collect(Collectors.toList());
        return orderModelList;
    }

    private OrderModel convertModelFromDataObject(OrderDO orderDO) {
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(orderDO, orderModel);
        orderModel.setItemPrice(new BigDecimal(orderDO.getItemPrice()));
        orderModel.setOrderPrice(BigDecimal.valueOf(orderDO.getItemPrice()).multiply(new BigDecimal(orderDO.getAmount())));
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo() {
        StringBuilder sb = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        sb.append(nowDate);

        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++) {
            sb.append(0);
        }
        sb.append(sequenceStr);
        return sb.toString();
    }

    private OrderDO convertFromModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }
}
