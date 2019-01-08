package net.aicoder.speedcloud.business.pipeline.exec.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by lcy on 2018/11/19.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({}) //需要忽略的属性，请添加在{}内，多个使用请“,”号隔开
public class StageConsoleOutputVO {
    private String stageName;
    private List<ShConsoleOutputVO> shConsoleOutputs;
    private String result;
    private long duration;
    private String consoleOutputText="";

    public String getConsoleOutputText() {
        return consoleOutputText;
    }

    public void setConsoleOutputText(String consoleOutputText) {
        this.consoleOutputText = consoleOutputText;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<ShConsoleOutputVO> getShConsoleOutputs() {
        return shConsoleOutputs;
    }

    public void setShConsoleOutputs(List<ShConsoleOutputVO> shConsoleOutputs) {
        this.shConsoleOutputs = shConsoleOutputs;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
