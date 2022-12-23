package org.ecommerceplatform.service;

import org.ecommerceplatform.error.BusinessException;
import org.ecommerceplatform.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;
    UserModel validateLogin(String telephone, String encryptPassword) throws BusinessException;
}
