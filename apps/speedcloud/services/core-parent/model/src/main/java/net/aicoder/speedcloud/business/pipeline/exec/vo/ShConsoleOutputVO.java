package net.aicoder.speedcloud.business.pipeline.exec.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by lcy on 2018/11/19.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({}) //需要忽略的属性，请添加在{}内，多个使用请“,”号隔开
public class ShConsoleOutputVO {
    private String result="SUCCESS";
    private String consoleOutputText="";
    private long duration=0;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getConsoleOutputText() {
        return consoleOutputText;
    }

    public void setConsoleOutputText(String consoleOutputText) {
        this.consoleOutputText = consoleOutputText;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
