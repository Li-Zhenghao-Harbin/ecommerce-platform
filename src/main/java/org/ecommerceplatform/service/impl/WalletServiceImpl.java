package org.ecommerceplatform.service.impl;

import org.ecommerceplatform.dao.WalletDOMapper;
import org.ecommerceplatform.dataobject.WalletDO;
import org.ecommerceplatform.service.WalletService;
import org.ecommerceplatform.service.model.WalletModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletDOMapper walletDOMapper;

    @Override
    public WalletModel getWalletById(Integer id) {
        WalletDO walletDO = walletDOMapper.selectByUserId(id);
        if (walletDO == null) {
            return null;
        }
        return convertFromDataObject(walletDO);
    }

    @Override
    public void decreaseBalance(Integer userId, BigDecimal cost) {
        walletDOMapper.decreaseBalance(userId, cost);
    }

    @Override
    public void increaseBalance(Integer userId, BigDecimal cost) {
        walletDOMapper.increaseBalance(userId, cost);
    }

    private WalletModel convertFromDataObject(WalletDO walletDO) {
        if (walletDO == null) {
            return null;
        }
        WalletModel walletModel = new WalletModel();
        BeanUtils.copyProperties(walletDO, walletModel);
        walletModel.setBalance(BigDecimal.valueOf(walletDO.getBalance()));
        return walletModel;
    }
}
