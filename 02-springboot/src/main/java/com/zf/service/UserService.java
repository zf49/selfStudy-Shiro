package com.zf.service;


import com.zf.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

public interface UserService {

    User queryUserByName(@Param("name") String name);
    
}
