package net.aicoder.devp.product.business.product.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;



public class DevpPrdPersonCondition implements Serializable{


	private String id;

	private Long tid;
	private Long tidMax;
	private Long tidMin;
	private String code;
	private String name;
	private String alias;
	private String description;
	private String nexusType;
	private Long nexusRid;
	private Long nexusRidMax;
	private Long nexusRidMin;
	private Integer seq;
	private Integer seqMax;
	private Integer seqMin;
	private Long uid;
	private Long uidMax;
	private Long uidMin;
	private String type;
	private String role;
	private String status;
	private Long userTid;
	private Long userTidMax;
	private Long userTidMin;
	private Long orgRid;
	private Long orgRidMax;
	private Long orgRidMin;
	private String orgName;
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


	public String getNexusType(){
		return nexusType;
	}
	public void setNexusType(String nexusType) {
		this.nexusType = nexusType;
	}


	public Long getNexusRid(){
		return nexusRid;
	}
	public void setNexusRid(Long nexusRid) {
		this.nexusRid = nexusRid;
	}

	public Long getNexusRidMin(){
		return nexusRidMin;
	}
	public void setNexusRidMin(Long nexusRidMin) {
		this.nexusRidMin = nexusRidMin;
	}

	public Long getNexusRidMax(){
		return nexusRidMax;
	}
	public void setNexusRidMax(Long nexusRidMax) {
		this.nexusRidMax = nexusRidMax;
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


	public Long getUid(){
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getUidMin(){
		return uidMin;
	}
	public void setUidMin(Long uidMin) {
		this.uidMin = uidMin;
	}

	public Long getUidMax(){
		return uidMax;
	}
	public void setUidMax(Long uidMax) {
		this.uidMax = uidMax;
	}


	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String getRole(){
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	public Long getUserTid(){
		return userTid;
	}
	public void setUserTid(Long userTid) {
		this.userTid = userTid;
	}

	public Long getUserTidMin(){
		return userTidMin;
	}
	public void setUserTidMin(Long userTidMin) {
		this.userTidMin = userTidMin;
	}

	public Long getUserTidMax(){
		return userTidMax;
	}
	public void setUserTidMax(Long userTidMax) {
		this.userTidMax = userTidMax;
	}


	public Long getOrgRid(){
		return orgRid;
	}
	public void setOrgRid(Long orgRid) {
		this.orgRid = orgRid;
	}

	public Long getOrgRidMin(){
		return orgRidMin;
	}
	public void setOrgRidMin(Long orgRidMin) {
		this.orgRidMin = orgRidMin;
	}

	public Long getOrgRidMax(){
		return orgRidMax;
	}
	public void setOrgRidMax(Long orgRidMax) {
		this.orgRidMax = orgRidMax;
	}


	public String getOrgName(){
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
