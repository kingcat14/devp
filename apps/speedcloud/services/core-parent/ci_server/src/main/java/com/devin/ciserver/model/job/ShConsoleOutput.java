package com.devin.ciserver.model.job;

/**
 * Created by lcy on 2018/9/5.
 */
public class ShConsoleOutput {
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
