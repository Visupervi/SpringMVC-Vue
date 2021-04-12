package com.HopeRun.SSM.Crm.service.serviceImpl;

import com.HopeRun.SSM.Crm.Utils.FilterArray;
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

import java.util.UUID;

import java.util.Date;
import java.util.List;

@Service
public class TrackPointServiceImpl implements TrackPointService {
    private Logger logger = Logger.getLogger(UserLoginServiceImpl.class);
    @Autowired
    private TblTrackpointMapper tblTrackpointMapper;

    /**
     * 存储point
     * @param tblTrackpoint
     * @return
     */
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

    /**
     * 获取链路point
     * @return
     */
    @Override
    public String getTackList() {
        JSONArray jsonArray2 = JSON.parseArray(JSONObject.toJSONString(getTrackPoint("Ajax")));
        logger.info("jsonArray2----------"+jsonArray2);
        if (!jsonArray2.isEmpty()){
            for (int i = 0; i < jsonArray2.size(); i++) {
                UUID uuid = UUID.randomUUID();
                String str = "{\"userAgent\": \"window.navigator.userAgent\",\n" +
                        "      \"networkState\": \"wifi\",\n" +
                        "      \"url\": \"http://localhost:8088/apis/setPointData\",\n" +
                        "      \"ip\": \"http://localhost:8088\",\n" +
                        "      \"traceId\": \"traceId\",\n" +
                        "      \"fingerprint\": \"fingerprint\",\n" +
                        "      \"eventType\": \"\",\n" +
                        "      \"userId\": \"\",\n" +
                        "      \"userType\": \"\",\n" +
                        "      \"timeStamp\":\"" + new Date().getTime() + "\",\n" +
                        "      \"widgetType\": \"\",\n" +
                        "      \"parentID\": \"" + jsonArray2.getJSONObject(i).getString("spanid") + "\",\n" +
                        "      \"spanId\": \"" + uuid + "\",\n" +
                        "      \"children\": []}";

                JSONObject jsonObject1 = JSONObject.parseObject(str);
                JSONObject jsonObject2 = HttpClientUtils.httpPost("http://localhost:8088/apis/setPointData", jsonObject1, true);
                logger.info("jsonObject1"+jsonObject2);
            }
        }
        JSONObject jsonObject = new JSONObject();
        List<TblTrackpoint> list = (List<TblTrackpoint>) tblTrackpointMapper.selectAll();
        JSONArray jsonArray = JSON.parseArray(jsonObject.toJSONString(list));
        logger.info("jsonObject1" + jsonArray);

        jsonArray = FilterArray.filterArray(FilterArray.filterArray(jsonArray));
        jsonObject.put("status", 200);
        jsonObject.put("result", jsonArray);
        logger.info("查询结果" + jsonObject.toJSONString());

        return jsonObject.toString();
    }

    @Override
    public List<TblTrackpoint> getTrackPoint(String string) {
        return tblTrackpointMapper.selectByWidgetType(string);
    }
}
