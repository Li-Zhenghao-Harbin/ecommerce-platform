package org.ecommerceplatform.controller;

import org.ecommerceplatform.controller.viewobject.WalletVO;
import org.ecommerceplatform.error.BusinessException;
import org.ecommerceplatform.error.EmBusinessError;
import org.ecommerceplatform.response.CommonReturnType;
import org.ecommerceplatform.service.UserService;
import org.ecommerceplatform.service.WalletService;
import org.ecommerceplatform.service.model.UserModel;
import org.ecommerceplatform.service.model.WalletModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import static org.ecommerceplatform.controller.BaseController.CONTENT_TYPE_FORMED;

@Controller("wallet")
@RequestMapping("/wallet")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", originPatterns = "*")
public class WalletController extends BaseController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/get", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getWallet() throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        WalletModel walletModel = walletService.getWalletById(userModel.getId());
        WalletVO walletVO = convertFromWalletModel(walletModel);
        return CommonReturnType.create(walletVO);
    }

    @RequestMapping(value = "/recharge", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType recharge(@RequestParam(name = "id")Integer id,
                                     @RequestParam(name = "cost") BigDecimal cost) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        walletService.increaseBalance(id, cost);
        return CommonReturnType.create(null);
    }

    private WalletVO convertFromWalletModel(WalletModel walletModel) {
        if (walletModel == null) {
            return null;
        }
        WalletVO walletVO = new WalletVO();
        BeanUtils.copyProperties(walletModel, walletVO);
        walletVO.setBalance(walletModel.getBalance());
        return walletVO;
    }
}
