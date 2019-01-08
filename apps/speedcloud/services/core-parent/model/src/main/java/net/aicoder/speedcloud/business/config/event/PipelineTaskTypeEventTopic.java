package net.aicoder.speedcloud.business.config.event;

import com.yunkang.saas.common.framework.message.DomainEvent;

public enum PipelineTaskTypeEventTopic implements DomainEvent {

	CREATE("create")
	,DELETE("delete")
	,UPDATE("update");

	private String topic = "speedcloud.config";
	private String value;
  
	PipelineTaskTypeEventTopic(String event){
		this.value = event;
	}

	@Override
	public String toString() {
		return topic+"."+this.value;
	}

	public String getTopic(){
		return topic;
	}

	@Override
	public String getEventType() {
		return value;
	}

}