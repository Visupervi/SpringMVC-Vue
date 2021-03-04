package com.HopeRun.SSM.Crm.service.serviceImpl;

import com.HopeRun.SSM.Crm.dao.mappers.SysUserMapper;
import com.HopeRun.SSM.Crm.entity.SysUser;
import com.HopeRun.SSM.Crm.service.UserLoginService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

@Service
@SessionAttributes("userInfo")
public class UserLoginServiceImpl implements UserLoginService {

    private Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public String login(String str) {
        logger.info("userService执行");
        JSONObject jsonObject = JSONObject.parseObject(str);
        SysUser sysUser = new SysUser();
        sysUser.setUserName(jsonObject.getString("userName"));
        sysUser.setUserPassword(jsonObject.getString("userPwd"));
        logger.info(sysUser.toString());
        return returnResult(sysUserMapper.selectByUser(sysUser), jsonObject.getString("userName"));
    }

    public String returnResult(Object object, Object obj) {
        JSONObject jsonObject = new JSONObject();
        if (!("").equals(object) && object != null) {
            jsonObject.put("result", "操作成功");
            jsonObject.put("status", "success");
            jsonObject.put("Token", "true");
            jsonObject.put("userInfo", obj);

        } else {
            jsonObject.put("result", "操作失败");
            jsonObject.put("status", "failure");
            jsonObject.put("Token", "failure");
        }
        return jsonObject.toString();
    }


}
