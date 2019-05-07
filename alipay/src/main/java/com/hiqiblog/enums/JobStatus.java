package com.hiqiblog.enums;

/**
 * @Author helloc
 * @Date 2019/5/7 19:45
 * @Version 1.0
 */
public enum  JobStatus {
    //运行中
    RUNNING("RUNNING"),
    //完成
    COMPLETE("COMPLETE"),
    //暂停
    PAUSED("PAUSED");

    /**
     * 响应代码
      */
    private String status;

    JobStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
