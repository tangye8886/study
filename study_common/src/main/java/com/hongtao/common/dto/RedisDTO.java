package com.hongtao.common.dto;

import java.io.Serializable;

public class RedisDTO extends CommonDTO implements Serializable{

    private String uid;//用户ID

    private String cid;// 课程ID

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
