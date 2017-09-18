package com.ssm.dto;

import java.io.Serializable;

/**
 * Created by mm on 2017/9/14.
 * redis测试一
 */
public class SeeUser implements Serializable {
    private String id;
    private String ip;
    private String seeTime;
    private int seeCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSeeTime() {
        return seeTime;
    }

    public void setSeeTime(String seeTime) {
        this.seeTime = seeTime;
    }

    public int getSeeCount() {
        return seeCount;
    }

    public void setSeeCount(int seeCount) {
        this.seeCount = seeCount;
    }
}
