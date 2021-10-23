package com.zf;

import com.zf.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    UserServiceImpl userService;


    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("aaa"));
        
    }

}
