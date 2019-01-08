package net.aicoder.speedcloud.business.pipeline.exec.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 运行节点日志的值对象
*/
@ApiModel(value = "展现运行节点日志的值对象")
@Getter @Setter
public class PipelineExecNodeLogVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**log*/
    @ApiModelProperty(value = "log")
    private String log;

    /**
     * 运行状态
     * 未开始、等待中、运行中、已结束
     */
    private String status;

    /**
     * 运行结果
     * 成功、失败
     */
    private String result;

    /**结果消息*/
    private String resultMessage;

    private PipelineBuildLogVO pipelineBuildLog;


    public String getLog(){
        return log;
    }
    public void setLog(String log) {
        this.log = log;
    }


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}