package com.ssm.dto;

public class Linestationcopy {
    private Integer sid;

    private String traincode;

    private String fromstation;

    private String tostation;

    private String arrivetime;

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
}