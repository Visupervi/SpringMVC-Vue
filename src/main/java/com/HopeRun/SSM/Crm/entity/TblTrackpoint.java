package com.HopeRun.SSM.Crm.entity;

import java.util.Date;

public class TblTrackpoint {
    private Integer id;

    private String url;

    private String useragent;

    private String networkstate;

    private String ip;

    private String eventtype;

    private Integer userid;

    private String usertype;

    private Date timestamp;

    private String widgettype;

    private String traceid;

    private String spanid;

    private String parentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getNetworkstate() {
        return networkstate;
    }

    public void setNetworkstate(String networkstate) {
        this.networkstate = networkstate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getWidgettype() {
        return widgettype;
    }

    public void setWidgettype(String widgettype) {
        this.widgettype = widgettype;
    }

    public String getTraceid() {
        return traceid;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public String getSpanid() {
        return spanid;
    }

    public void setSpanid(String spanid) {
        this.spanid = spanid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}