package com.hongtao.common.dto;

import java.io.Serializable;

public class UserDTO extends CommonDTO implements Serializable{

    private String username;

    private Integer status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
