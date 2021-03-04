package com.HopeRun.SSM.Crm.controller;
import com.HopeRun.SSM.Crm.service.TrackPointService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("apis")
public class GetAllPointController {
    private Logger logger = Logger.getLogger(UserLoginController.class);
    @Autowired
    private TrackPointService trackPointService;
    @RequestMapping(value = "/getList",method = {RequestMethod.GET})
    @ResponseBody
    public String getPointList(HttpServletRequest request){
        return trackPointService.getTackList();
    }
}

