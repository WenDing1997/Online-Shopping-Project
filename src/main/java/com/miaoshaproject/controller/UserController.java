package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/get")
  @ResponseBody
  public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {
    //调用service服务获取对应id的用户对象并返回给前端
    UserModel userModel = userService.getUserByID(id);

    //若获取的对应用户信息不存在
    if (userModel == null) {
      throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
    }

    //将核心领域模型用户对象转化为可供UI使用的viewobject
    UserVO userVO = convertFromModel(userModel);

    //返回通用对象
    return CommonReturnType.create(userVO);
  }

  private UserVO convertFromModel(UserModel userModel) {
    if (userModel == null) {
      return null;
    }
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(userModel, userVO);
    return userVO;
  }

}
