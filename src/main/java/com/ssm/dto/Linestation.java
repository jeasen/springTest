package com.ssm.dto;

public class Linestation {
    private Integer sid;

    private String traincode;

    private String fromstation;

    private String tostation;

    private String arrivetime;

    private String departtime;

    private Integer stationid;

    private String stationname;

    private Integer stationnum;

    private String staytime;

    private String stoptype;

    private String type;

    private String lng;

    private String lat;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getTraincode() {
        return traincode;
    }

    public void setTraincode(String traincode) {
        this.traincode = traincode == null ? null : traincode.trim();
    }

    public String getFromstation() {
        return fromstation;
    }

    public void setFromstation(String fromstation) {
        this.fromstation = fromstation == null ? null : fromstation.trim();
    }

    public String getTostation() {
        return tostation;
    }

    public void setTostation(String tostation) {
        this.tostation = tostation == null ? null : tostation.trim();
    }

    public String getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime == null ? null : arrivetime.trim();
    }

    public String getDeparttime() {
        return departtime;
    }

    public void setDeparttime(String departtime) {
        this.departtime = departtime == null ? null : departtime.trim();
    }

    public Integer getStationid() {
        return stationid;
    }

    public void setStationid(Integer stationid) {
        this.stationid = stationid;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname == null ? null : stationname.trim();
    }

    public Integer getStationnum() {
        return stationnum;
    }

    public void setStationnum(Integer stationnum) {
        this.stationnum = stationnum;
    }

    public String getStaytime() {
        return staytime;
    }

    public void setStaytime(String staytime) {
        this.staytime = staytime == null ? null : staytime.trim();
    }

    public String getStoptype() {
        return stoptype;
    }

    public void setStoptype(String stoptype) {
        this.stoptype = stoptype == null ? null : stoptype.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }
}