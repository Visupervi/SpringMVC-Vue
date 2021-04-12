package com.HopeRun.SSM.Crm.controller;
import com.HopeRun.SSM.Crm.service.UserLoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/**
 * @author: visupervi
 * @Date: 2021/3/11 1:59 下午
 * @param: 
 * @return: 
 * @Description:
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("apis")
@SessionAttributes("userInfo")
public class UserLoginController {
  private Logger logger = Logger.getLogger(UserLoginController.class);
  @Autowired
  private UserLoginService userLoginService;

  @RequestMapping(value="/userLogin",method = {RequestMethod.POST})
  @ResponseBody
  public String userLogin( @RequestBody String string) {
    logger.info(string);
    logger.info("方法被调用");
    return userLoginService.login(string);
  }


}
