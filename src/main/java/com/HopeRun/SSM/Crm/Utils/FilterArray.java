package com.HopeRun.SSM.Crm.Utils;
/**
 * @author: visupervi
 * @Date: 2021/4/10 3:37 下午
 * @param:
 * @return:
 * @Description: 过滤数组
 */
import java.lang.reflect.Array;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

public class FilterArray {
    private static Logger logger = Logger.getLogger(FilterArray.class);

    public static JSONArray filterArray(JSONArray array) {
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.size(); j++) {
                if(array.getJSONObject(i).getString("children") != null) {
                    System.out.println(array.getJSONObject(i).getString("children"));
                    filterArray(JSONArray.parseArray(array.getJSONObject(i).getString("children")));
                } else {
                    if (array.getJSONObject(j).getString("parentid") != "") {
                        JSONArray jsonArray1 = new JSONArray();
                        if (array.getJSONObject(j).getString("parentid").equals(array.getJSONObject(i).getString("spanid"))) {
                            logger.info("收到数据" + JSONObject.toJSONString(array.getJSONObject(j)));
                            jsonArray1.add(array.getJSONObject(j));
                            array.getJSONObject(i).put("children", jsonArray1);
                            array.fluentRemove(j);
                        }
                    } else {
                        JSONArray jsonArray1 = new JSONArray();
                        array.getJSONObject(i).put("children", jsonArray1);
                    }
                }

            }
        }
        return array;
    }
}
