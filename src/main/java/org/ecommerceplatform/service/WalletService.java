package org.ecommerceplatform.service;

import org.ecommerceplatform.service.model.WalletModel;

import java.math.BigDecimal;

public interface WalletService {
    WalletModel getWalletById(Integer id);
    void decreaseBalance(Integer userId, BigDecimal cost);
    void increaseBalance(Integer userId, BigDecimal cost);
}
