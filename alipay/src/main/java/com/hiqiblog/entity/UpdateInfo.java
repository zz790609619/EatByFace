package com.hiqiblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UpdateInfo {
    private Integer id;

    private String version;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    private String disc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc == null ? null : disc.trim();
    }
}