package com.HopeRun.SSM.Crm.dao.mappers;

import com.HopeRun.SSM.Crm.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long userId);

    SysUser selectByUser(SysUser user);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}