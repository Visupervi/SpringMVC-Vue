package com.HopeRun.SSM.Crm.service.serviceImpl;

import com.HopeRun.SSM.Crm.Utils.HttpClientUtils;
import com.HopeRun.SSM.Crm.dao.mappers.TblTrackpointMapper;
import com.HopeRun.SSM.Crm.entity.TblTrackpoint;
import com.HopeRun.SSM.Crm.service.TrackPointService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrackPointServiceImpl implements TrackPointService {
    private Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
    @Autowired
    private TblTrackpointMapper tblTrackpointMapper;

    @Override
    public String getTrackData(TblTrackpoint tblTrackpoint) {
        logger.info("getTrackData调用" + tblTrackpoint);
        Object obj = tblTrackpointMapper.insert(tblTrackpoint);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "200");
        jsonObject.put("result", "success");
        jsonObject.put("Object", obj);
        return jsonObject.toString();
    }

    @Override
    public String getTackList() {
        JSONObject jsonObject = new JSONObject();
        List<TblTrackpoint> list = (List<TblTrackpoint>) tblTrackpointMapper.selectAll();
        JSONArray jsonArray = JSON.parseArray(jsonObject.toJSONString(list));

        logger.info("jsonObject1" + jsonArray);
        for (int i = 0; i < jsonArray.size(); i++) {
            for (int j = 0; j < jsonArray.size(); j++) {
                if (!jsonArray.getJSONObject(j).getString("parentid").equals("")) {
                    JSONArray jsonArray1 = new JSONArray();
                    if (jsonArray.getJSONObject(j).getString("parentid").equals(jsonArray.getJSONObject(i).getString("spanid"))) {
                        logger.info("数据是啥" + JSONObject.toJSONString(jsonArray.getJSONObject(j)));
                        jsonArray1.add(jsonArray.getJSONObject(j));
                        jsonArray.getJSONObject(i).put("children", jsonArray1);
                        jsonArray.fluentRemove(j);
                    }
                } else {
                    JSONArray jsonArray1 = new JSONArray();
                    jsonArray.getJSONObject(i).put("children", jsonArray1);
                }
            }
        }
        jsonObject.put("status", 200);
        jsonObject.put("result", jsonArray);
        logger.info("查询结果" + jsonObject.toJSONString());
        String str = "{\"userAgent\": \"window.navigator.userAgent\",\n" +
                "      \"networkState\": \"wifi\",\n" +
                "      \"url\": \"window.location.href\",\n" +
                "      \"ip\": \"\",\n" +
                "      \"traceId\": \"traceId\",\n" +
                "      \"fingerprint\": \"fingerprint\",\n" +
                "      \"eventType\": \"load\",\n" +
                "      \"userId\": \"\",\n" +
                "      \"userType\": \"\",\n" +
                "      \"timeStamp\":\""+ new Date().getTime()+"\",\n"+
                "      \"widgetType\": \"show\",\n" +
                "      \"parentID\": \"\",\n" +
                "      \"spanId\": \"spanId_${uuid()}\",\n" +
                "      \"children\": []}";
        JSONObject jsonObject1 = JSONObject.parseObject(str);
        JSONObject jsonObject2 = HttpClientUtils.httpPost("http://localhost:8088/apis/setPointData",jsonObject1,true);
        logger.info("jsonObject1"+jsonObject2);
        return jsonObject.toString();
    }
}
