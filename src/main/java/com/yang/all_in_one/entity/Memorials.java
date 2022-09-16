package com.yang.all_in_one.entity;

public class Memorials {
    private Integer memorialsID;
    private String memorialsTitle;
    private String memorialsContent;
    private String memorialsContentDigest;
    private Integer memorialsEmp;
    private String memorialsEmpName;
    private String memorialsCreateTime;
    private String feedbackTime;
    private String feedbackContent;
    private Integer memorialsStatus;

    public Memorials() {
    }

    public Memorials(Integer memorialsID, String memorialsTitle, String memorialsContent, String memorialsContentDigest, Integer memorialsEmp, String memorialsEmpName, String memorialsCreateTime, String feedbackTime, String feedbackContent, Integer memorialsStatus) {
        this.memorialsID = memorialsID;
        this.memorialsTitle = memorialsTitle;
        this.memorialsContent = memorialsContent;
        this.memorialsContentDigest = memorialsContentDigest;
        this.memorialsEmp = memorialsEmp;
        this.memorialsEmpName = memorialsEmpName;
        this.memorialsCreateTime = memorialsCreateTime;
        this.feedbackTime = feedbackTime;
        this.feedbackContent = feedbackContent;
        this.memorialsStatus = memorialsStatus;
    }

    public Integer getMemorialsID() {
        return memorialsID;
    }

    public void setMemorialsID(Integer memorialsID) {
        this.memorialsID = memorialsID;
    }

    public String getMemorialsTitle() {
        return memorialsTitle;
    }

    public void setMemorialsTitle(String memorialsTitle) {
        this.memorialsTitle = memorialsTitle;
    }

    public String getMemorialsContent() {
        return memorialsContent;
    }

    public void setMemorialsContent(String memorialsContent) {
        this.memorialsContent = memorialsContent;
    }

    public String getMemorialsContentDigest() {
        return memorialsContentDigest;
    }

    public void setMemorialsContentDigest(String memorialsContentDigest) {
        this.memorialsContentDigest = memorialsContentDigest;
    }

    public Integer getMemorialsEmp() {
        return memorialsEmp;
    }

    public void setMemorialsEmp(Integer memorialsEmp) {
        this.memorialsEmp = memorialsEmp;
    }

    public String getMemorialsEmpName() {
        return memorialsEmpName;
    }

    public void setMemorialsEmpName(String memorialsEmpName) {
        this.memorialsEmpName = memorialsEmpName;
    }

    public String getMemorialsCreateTime() {
        return memorialsCreateTime;
    }

    public void setMemorialsCreateTime(String memorialsCreateTime) {
        this.memorialsCreateTime = memorialsCreateTime;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Integer getMemorialsStatus() {
        return memorialsStatus;
    }

    public void setMemorialsStatus(Integer memorialsStatus) {
        this.memorialsStatus = memorialsStatus;
    }

    @Override
    public String toString() {
        return "memorials{" +
                "memorialsID=" + memorialsID +
                ", memorialsTitle='" + memorialsTitle + '\'' +
                ", memorialsContent='" + memorialsContent + '\'' +
                ", memorialsContentDigest='" + memorialsContentDigest + '\'' +
                ", memorialsEmp=" + memorialsEmp +
                ", memorialsEmpName='" + memorialsEmpName + '\'' +
                ", memorialsCreateTime='" + memorialsCreateTime + '\'' +
                ", feedbackTime='" + feedbackTime + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", memorialsStatus=" + memorialsStatus +
                '}';
    }
}
