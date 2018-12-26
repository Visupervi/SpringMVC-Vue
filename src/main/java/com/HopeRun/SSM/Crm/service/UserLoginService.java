package com.HopeRun.SSM.Crm.service;

import com.HopeRun.SSM.Crm.entity.SysUser;

/**
 * 登陆接口
 * params 用户名，用户密码
 */

public interface UserLoginService {
  public String login(SysUser user);
}
