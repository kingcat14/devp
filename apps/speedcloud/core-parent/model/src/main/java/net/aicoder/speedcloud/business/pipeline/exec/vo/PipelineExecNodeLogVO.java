package net.aicoder.speedcloud.business.pipeline.exec.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 运行节点日志的值对象
*/
@ApiModel(value = "展现运行节点日志的值对象")
public class PipelineExecNodeLogVO {

    @ApiModelProperty(value = "记录id")
    private String id;


    /**log*/
    @ApiModelProperty(value = "log")
    private String log;


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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}