package org.ecommerceplatform;

import org.ecommerceplatform.dao.UserDOMapper;
import org.ecommerceplatform.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"org.ecommerceplatform"})
@RestController
@MapperScan("org.ecommerceplatform.dao")
public class App 
{
//    @Autowired
//    private UserDOMapper userDOMapper;
//
//    @RequestMapping("/")
//    public String home() {
//        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
//        return userDO == null ? "user not exist" : userDO.getName();
//    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
}
