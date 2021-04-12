package com.HopeRun.SSM.Crm.service;

import com.HopeRun.SSM.Crm.entity.TblTrackpoint;

import java.util.List;

public interface TrackPointService {
    public String getTrackData(TblTrackpoint tblTrackpoint);
    public String getTackList();
    public List<TblTrackpoint> getTrackPoint(String string);
}

