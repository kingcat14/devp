package net.aicoder.speedcloud.business.pipeline.exec.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by lcy on 2018/11/14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({}) //需要忽略的属性，请添加在{}内，多个使用请“,”号隔开
@Getter
@Setter
public class PipelineBuildLogVO {
    private String displayName;
    private String fullDisplayName;
    private String id;
    private String result;
    private String consoleOutputText;
    private List<StageConsoleOutputVO> pipelineConsoleOutputText;
    private long number;
    private long duration;
    private long timestamp;
    private String timeShow;

    public PipelineBuildLogVO() {
    }
    public PipelineBuildLogVO(String displayName, String fullDisplayName, String id, String result, String consoleOutputText, List<StageConsoleOutputVO> pipelineConsoleOutputText, long number, long duration, long timestamp, String timeShow) {
        this.displayName = displayName;
        this.fullDisplayName = fullDisplayName;
        this.id = id;
        this.result = result;
        this.consoleOutputText = consoleOutputText;
        this.pipelineConsoleOutputText = pipelineConsoleOutputText;
        this.number = number;
        this.duration = duration;
        this.timestamp = timestamp;
        this.timeShow= timeShow;
    }

}
