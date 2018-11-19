package com.devin.ciserver.model.job;
import java.util.List;

/**
 * Created by devin on 2018/8/8.
 */
public class PipelineJob extends Job {
    private List<StringParm> stringParms;
    private List<PipelineStage> pipelineStages;

    public List<StringParm> getStringParms() {
        return stringParms;
    }

    public void setStringParms(List<StringParm> stringParms) {
        this.stringParms = stringParms;
    }

    public List<PipelineStage> getPipelineStages() {
        return pipelineStages;
    }

    public void setPipelineStages(List<PipelineStage> pipelineStages) {
        this.pipelineStages = pipelineStages;
    }
}
