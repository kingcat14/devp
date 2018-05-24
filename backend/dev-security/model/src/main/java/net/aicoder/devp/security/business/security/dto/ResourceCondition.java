package net.aicoder.devp.security.business.security.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class ResourceCondition implements Serializable{


	private String id;

	private String name;
	private String url;
	private String type;
	private String code;
	private Long parentId;
	private Long parentIdMax;
	private Long parentIdMin;
	private Integer orderIndex;
	private Integer orderIndexMax;
	private Integer orderIndexMin;


	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public Long getParentId(){
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentIdMin(){
		return parentIdMin;
	}
	public void setParentIdMin(Long parentIdMin) {
		this.parentIdMin = parentIdMin;
	}

	public Long getParentIdMax(){
		return parentIdMax;
	}
	public void setParentIdMax(Long parentIdMax) {
		this.parentIdMax = parentIdMax;
	}


	public Integer getOrderIndex(){
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getOrderIndexMin(){
		return orderIndexMin;
	}
	public void setOrderIndexMin(Integer orderIndexMin) {
		this.orderIndexMin = orderIndexMin;
	}

	public Integer getOrderIndexMax(){
		return orderIndexMax;
	}
	public void setOrderIndexMax(Integer orderIndexMax) {
		this.orderIndexMax = orderIndexMax;
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
