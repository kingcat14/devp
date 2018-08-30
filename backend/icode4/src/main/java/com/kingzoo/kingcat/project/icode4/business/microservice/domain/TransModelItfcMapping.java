package com.kingzoo.kingcat.project.icode4.business.microservice.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 传输对象接口映射
 * @author icode
 */
@Entity
@Table
public class TransModelItfcMapping {

	public static final String PROPERTY_TRANS_MODEL_ID = "transModelId";
	public static final String PROPERTY_MICRO_SERVICE_ITFC_ID = "microServiceItfcId";
	public static final String PROPERTY_RELATION_TYPE = "relationType";


    @Id
    private String id;


    /**
    * 传输对象ID
    * 
    */
    @Column(name = "trans_model_id")
	@NotNull(message = "传输对象ID不能为空")
	@Size(max = 255, message = "传输对象ID超长，最多255个字符")
	private String transModelId;

    /**
    * 微服务接口ID
    * 
    */
    @Column(name = "micro_service_itfc_id")
	@NotNull(message = "微服务接口ID不能为空")
	@Size(max = 255, message = "微服务接口ID超长，最多255个字符")
	private String microServiceItfcId;

    /**
    * 关系类型
    * REQUEST,RESPONSE
    */
    @Column(name = "relation_type")
	@NotNull(message = "关系类型不能为空")
	@Size(max = 255, message = "关系类型超长，最多255个字符")
	private String relationType;

	public String getTransModelId(){
		return transModelId;
	}
	public void setTransModelId(String transModelId) {
		this.transModelId = transModelId;
	}

	public String getMicroServiceItfcId(){
		return microServiceItfcId;
	}
	public void setMicroServiceItfcId(String microServiceItfcId) {
		this.microServiceItfcId = microServiceItfcId;
	}

	public String getRelationType(){
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
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