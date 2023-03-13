package com.me.framework.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.me.framework.model.User;

public interface UserService extends IService<User> {

    User getOneByUsername(String username);
}
