package com.HopeRun.SSM.Crm.controller;

import com.HopeRun.SSM.Crm.entity.TblTrackpoint;
import com.HopeRun.SSM.Crm.service.TrackPointService;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("apis")
public class TrackPointController {
    private Logger logger = Logger.getLogger(UserLoginController.class);
    @Autowired
    private TrackPointService trackPointService;
    @RequestMapping(value = "/setPointData",method = {RequestMethod.POST})
    @ResponseBody
    public String setPontData(@RequestBody String string){
        TblTrackpoint tblTrackpoint = JSONObject.parseObject(string,TblTrackpoint.class);
        logger.info("tblTrackpoint"+tblTrackpoint);
        return trackPointService.getTrackData(tblTrackpoint);
    }
}
