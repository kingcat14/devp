package com.devin.ciserver.model.job;

/**
 * Created by devin on 2018/8/8.
 */
public class Job {
    private String jobName;
    private String desc;
    private long keepBuildDay=30;
    private long keepBuildNumber=30;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getKeepBuildDay() {
        return keepBuildDay;
    }

    public void setKeepBuildDay(long keepBuildDay) {
        this.keepBuildDay = keepBuildDay;
    }

    public long getKeepBuildNumber() {
        return keepBuildNumber;
    }

    public void setKeepBuildNumber(long keepBuildNumber) {
        this.keepBuildNumber = keepBuildNumber;
    }
}
