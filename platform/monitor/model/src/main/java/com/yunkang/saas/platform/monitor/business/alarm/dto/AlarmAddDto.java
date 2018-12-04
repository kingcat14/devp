package com.yunkang.saas.platform.monitor.business.alarm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 告警
 * @author icode
 */
@ApiModel(value = "新增告警使用的DTO")
@Setter @Getter
public class AlarmAddDto {

    /**代码*/
	@ApiModelProperty(value = "代码", required = false)
	private String code;

    /**名称*/
	@ApiModelProperty(value = "名称", required = false)
	private String name;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false)
	private String type;

    /**程序*/
	@ApiModelProperty(value = "程序", required = false)
	private String app;

    /**指标*/
	@ApiModelProperty(value = "指标", required = false)
	private String counter;

    /**指标值*/
	@ApiModelProperty(value = "指标值", required = false)
	private String value;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "已确认、已取消、已失效")
	private String status;

    /**发生时间*/
	@ApiModelProperty(value = "发生时间", required = false)
	private Date occurTime;

    /**最后发生时间*/
	@ApiModelProperty(value = "最后发生时间", required = false)
	private Date lastOccurTime;

    /**消失时间*/
	@ApiModelProperty(value = "消失时间", required = false)
	private Date disappearTime;

    /**发生次数*/
	@ApiModelProperty(value = "发生次数", required = false)
	private Integer occurCount;

    /**内容*/
	@ApiModelProperty(value = "内容", required = false, notes = "告警内容")
	private String content;


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

	public String getApp(){
        return app;
    }
    public void setApp(String app) {
        this.app = app;
    }

	public String getCounter(){
        return counter;
    }
    public void setCounter(String counter) {
        this.counter = counter;
    }

	public String getValue(){
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOccurTime(){
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public Date getLastOccurTime(){
		return lastOccurTime;
	}
	public void setLastOccurTime(Date lastOccurTime) {
		this.lastOccurTime = lastOccurTime;
	}

	public Date getDisappearTime(){
		return disappearTime;
	}
	public void setDisappearTime(Date disappearTime) {
		this.disappearTime = disappearTime;
	}

	public Integer getOccurCount(){
		return occurCount;
	}
	public void setOccurCount(Integer occurCount) {
		this.occurCount = occurCount;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
