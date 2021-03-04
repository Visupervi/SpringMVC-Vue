package com.HopeRun.SSM.Crm.dao.mappers;

import com.HopeRun.SSM.Crm.entity.TblTrackpoint;

import java.util.List;

public interface TblTrackpointMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TblTrackpoint record);

    int insertSelective(TblTrackpoint record);

    TblTrackpoint selectByPrimaryKey(Integer id);
    List<TblTrackpoint> selectAll();
    int updateByPrimaryKeySelective(TblTrackpoint record);

    int updateByPrimaryKey(TblTrackpoint record);
}