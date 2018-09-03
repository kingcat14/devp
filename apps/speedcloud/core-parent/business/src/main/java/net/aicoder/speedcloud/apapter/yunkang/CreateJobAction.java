package net.aicoder.speedcloud.apapter.yunkang;

import java.util.List;

public class CreateJobAction {

    private String desc;
    private String jobName;
    private List<String> shellCmd;
    private List<StringParma> stringParmas;

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJobName() {
        return jobName;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<String> getShellCmd() {
        return shellCmd;
    }
    public void setShellCmd(List<String> shellCmd) {
        this.shellCmd = shellCmd;
    }

    public List<StringParma> getStringParmas() {
        return stringParmas;
    }
    public void setStringParmas(List<StringParma> stringParmas) {
        this.stringParmas = stringParmas;
    }
}
