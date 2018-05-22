package net.aicoder.devp.product.business.product.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



public class DevpSysExtcmpCondition implements Serializable{


	private String id;

	private Long tid;
	private Long tidMax;
	private Long tidMin;
	private String code;
	private String name;
	private String alias;
	private String description;
	private Long prdRid;
	private Long prdRidMax;
	private Long prdRidMin;
	private Long extPrdRid;
	private Long extPrdRidMax;
	private Long extPrdRidMin;
	private Long extElmRid;
	private Long extElmRidMax;
	private Long extElmRidMin;
	private Integer seq;
	private Integer seqMax;
	private Integer seqMin;
	private String type;
	private String stereotype;
	private Integer recordState;
	private Integer recordStateMax;
	private Integer recordStateMin;
	private String createUcode;
	private String modifyUcode;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTidMin(){
		return tidMin;
	}
	public void setTidMin(Long tidMin) {
		this.tidMin = tidMin;
	}

	public Long getTidMax(){
		return tidMax;
	}
	public void setTidMax(Long tidMax) {
		this.tidMax = tidMax;
	}


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


	public String getAlias(){
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getDescription(){
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getPrdRidMin(){
		return prdRidMin;
	}
	public void setPrdRidMin(Long prdRidMin) {
		this.prdRidMin = prdRidMin;
	}

	public Long getPrdRidMax(){
		return prdRidMax;
	}
	public void setPrdRidMax(Long prdRidMax) {
		this.prdRidMax = prdRidMax;
	}


	public Long getExtPrdRid(){
		return extPrdRid;
	}
	public void setExtPrdRid(Long extPrdRid) {
		this.extPrdRid = extPrdRid;
	}

	public Long getExtPrdRidMin(){
		return extPrdRidMin;
	}
	public void setExtPrdRidMin(Long extPrdRidMin) {
		this.extPrdRidMin = extPrdRidMin;
	}

	public Long getExtPrdRidMax(){
		return extPrdRidMax;
	}
	public void setExtPrdRidMax(Long extPrdRidMax) {
		this.extPrdRidMax = extPrdRidMax;
	}


	public Long getExtElmRid(){
		return extElmRid;
	}
	public void setExtElmRid(Long extElmRid) {
		this.extElmRid = extElmRid;
	}

	public Long getExtElmRidMin(){
		return extElmRidMin;
	}
	public void setExtElmRidMin(Long extElmRidMin) {
		this.extElmRidMin = extElmRidMin;
	}

	public Long getExtElmRidMax(){
		return extElmRidMax;
	}
	public void setExtElmRidMax(Long extElmRidMax) {
		this.extElmRidMax = extElmRidMax;
	}


	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getSeqMin(){
		return seqMin;
	}
	public void setSeqMin(Integer seqMin) {
		this.seqMin = seqMin;
	}

	public Integer getSeqMax(){
		return seqMax;
	}
	public void setSeqMax(Integer seqMax) {
		this.seqMax = seqMax;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getStereotype(){
		return stereotype;
	}
	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}


	public Integer getRecordState(){
		return recordState;
	}
	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public Integer getRecordStateMin(){
		return recordStateMin;
	}
	public void setRecordStateMin(Integer recordStateMin) {
		this.recordStateMin = recordStateMin;
	}

	public Integer getRecordStateMax(){
		return recordStateMax;
	}
	public void setRecordStateMax(Integer recordStateMax) {
		this.recordStateMax = recordStateMax;
	}


	public String getCreateUcode(){
		return createUcode;
	}
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}


	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
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
