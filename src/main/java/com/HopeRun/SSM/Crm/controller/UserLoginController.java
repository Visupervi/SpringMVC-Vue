package com.HopeRun.SSM.Crm.controller;

import com.HopeRun.SSM.Crm.entity.SysUser;
import com.HopeRun.SSM.Crm.service.UserLoginService;
import com.HopeRun.SSM.Crm.service.serviceImpl.UserLoginServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/apis")
public class UserLoginController {
  private Logger logger = Logger.getLogger(UserLoginController.class);
  @Autowired
  private UserLoginService userLoginService;


  @RequestMapping("/userLogin")
  @ResponseBody
  public String userLogin(@RequestParam String userName,@RequestParam String userPassWord) {
//    JSONObject jsonObject = JSON.parseObject(string);

//    logger.info(jsonObject);
    logger.info("111111111111");
    SysUser sysUser = new SysUser();
    sysUser.setUserName(userName);
    sysUser.setUserPassword(userPassWord);
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
