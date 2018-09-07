package com.devin.ciserver.model.job;
import java.util.List;

/**
 * Created by lcy on 2018/9/5.
 */
public class PipelineStage {
    private String stageName;
    private List<String> shellCmd;

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<String> getShellCmd() {
        return shellCmd;
    }

    public void setShellCmd(List<String> shellCmd) {
        this.shellCmd = shellCmd;
    }
}
