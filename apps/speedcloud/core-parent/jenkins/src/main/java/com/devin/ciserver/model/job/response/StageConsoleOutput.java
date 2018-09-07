package com.devin.ciserver.model.job.response;
import java.util.List;
/**
 * Created by lcy on 2018/9/4.
 */
public class StageConsoleOutput {
    private String stageName;
    private List<ShConsoleOutput> shConsoleOutputs;
    private String result;
    private long duration;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<ShConsoleOutput> getShConsoleOutputs() {
        return shConsoleOutputs;
    }

    public void setShConsoleOutputs(List<ShConsoleOutput> shConsoleOutputs) {
        this.shConsoleOutputs = shConsoleOutputs;
    }
}