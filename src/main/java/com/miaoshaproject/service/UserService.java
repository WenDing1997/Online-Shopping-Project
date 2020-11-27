package com.miaoshaproject.service;

import com.miaoshaproject.service.model.UserModel;

public interface UserService {
  //用过用户ID获取用户对象的方法
  UserModel getUserByID(Integer id);
}
