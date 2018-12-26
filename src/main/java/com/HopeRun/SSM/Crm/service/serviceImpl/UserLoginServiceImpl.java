package com.HopeRun.SSM.Crm.service.serviceImpl;
import com.HopeRun.SSM.Crm.dao.mappers.SysUserMapper;
import com.HopeRun.SSM.Crm.entity.SysUser;
import com.HopeRun.SSM.Crm.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service//("userService")
public class UserLoginServiceImpl implements UserLoginService {
  @Autowired
  private SysUserMapper sysUserMapper;
  @Override
  public String login(SysUser user) {
    return  sysUserMapper.selectByUser(user).toString();
  }
}
