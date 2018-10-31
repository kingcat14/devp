package com.yunkang.saas.bootstrap.platform.business.resource.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Getter @Setter
public class ResourceCondition implements Serializable{

	private String id;

	private String appCode;
	private String name;
	private String url;
	private String type;
	private Long code;
	private Long parentCode;
	private Integer orderIndex;
	private Boolean hidden;


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
