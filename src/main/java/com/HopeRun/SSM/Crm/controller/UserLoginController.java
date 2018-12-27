package com.HopeRun.SSM.Crm.controller;

import com.HopeRun.SSM.Crm.entity.SysUser;
import com.HopeRun.SSM.Crm.service.UserLoginService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apis")
public class UserLoginController {
  private Logger logger = Logger.getLogger(UserLoginController.class);
  @Autowired
  private UserLoginService userLoginService;


  @RequestMapping(value = "/userLogin",method= RequestMethod.POST)
  @ResponseBody
  public String userLogin(@RequestBody String string) {
    JSONObject jsonObject = JSON.parseObject(string);

    logger.info(jsonObject);
    logger.info("111111111111");
    SysUser sysUser = new SysUser();
    sysUser.setUserName(jsonObject.getString("userName"));
    sysUser.setUserPassword(jsonObject.getString("userPassword"));
    logger.info(sysUser.toString());
    return  returnResult(userLoginService.login(sysUser));

  }


  public String returnResult(Object object){
    JSONObject jsonObject = new JSONObject();
    if(!("").equals(object) && object!=null){

      jsonObject.put("result","操作成功");
      jsonObject.put("status","success");

    }else{
      jsonObject.put("result","操作失败");
      jsonObject.put("status","fail");
    }
    return jsonObject.toString();
  }

}
