package net.aicoder.devp.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 产品线
 * @author icode
 */
@ApiModel(value = "新增产品线使用的DTO")
public class DevpPrdPrdlineAddDto {

    /**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;

    /**etype*/
	@ApiModelProperty(value = "etype", required = false, notes = "")
	private String etype;

    /**产品线名称*/
	@ApiModelProperty(value = "产品线名称", required = false, notes = "[产品线名称]")
	private String name;

    /**产品线代码*/
	@ApiModelProperty(value = "产品线代码", required = false, notes = "[产品线代码]")
	private String code;

    /**产品线别名*/
	@ApiModelProperty(value = "产品线别名", required = false, notes = "[产品线别名]")
	private String alias;

    /**产品线描述*/
	@ApiModelProperty(value = "产品线描述", required = false, notes = "[产品线描述]")
	private String description;

    /**产品线类型*/
	@ApiModelProperty(value = "产品线类型", required = false, notes = "[产品线类型]-(保留)")
	private String type;

    /**领域*/
	@ApiModelProperty(value = "领域", required = false, notes = "[领域]-所属领域")
	private String domain;

    /**构造型*/
	@ApiModelProperty(value = "构造型", required = false, notes = "[构造型]-(保留)")
	private String stereotype;

    /**访问控制范围*/
	@ApiModelProperty(value = "访问控制范围", required = false, notes = "[访问控制范围]-访问控制范围:共享产品，租户内共享,私有产品")
	private String scope;

    /**版本*/
	@ApiModelProperty(value = "版本", required = false, notes = "[版本]-当前版本")
	private String version;

    /**阶段*/
	@ApiModelProperty(value = "阶段", required = false, notes = "[阶段]-产品调研,产品设计,产品开发,试运行,产品维护,产品停用")
	private String phase;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]-未开始,进行中,已完成,暂停,取消")
	private String status;

    /**父产品线编号*/
	@ApiModelProperty(value = "父产品线编号", required = false, notes = "[父产品线编号]")
	private Long parentRid;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;

    /**记录状态*/
	@ApiModelProperty(value = "记录状态", required = false, notes = "[记录状态]-0-失效;1-生效;缺省为1")
	private Integer recordState;

    /**创建用户代码*/
	@ApiModelProperty(value = "创建用户代码", required = false, notes = "[创建用户代码]")
	private String createUcode;

    /**创建用户姓名*/
	@ApiModelProperty(value = "创建用户姓名", required = false, notes = "[创建用户姓名]")
	private String createUname;

    /**修改用户代码*/
	@ApiModelProperty(value = "修改用户代码", required = false, notes = "[修改用户代码]")
	private String modifyUcode;

    /**修改用户姓名*/
	@ApiModelProperty(value = "修改用户姓名", required = false, notes = "[修改用户姓名]")
	private String modifyUname;


	public Long getTid(){
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getEtype(){
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDomain(){
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getStereotype(){
		return stereotype;
	}
	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}

	public String getScope(){
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getVersion(){
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getPhase(){
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getParentRid(){
		return parentRid;
	}
	public void setParentRid(Long parentRid) {
		this.parentRid = parentRid;
	}

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getRecordState(){
		return recordState;
	}
	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getCreateUcode(){
		return createUcode;
	}
	public void setCreateUcode(String createUcode) {
		this.createUcode = createUcode;
	}

	public String getCreateUname(){
		return createUname;
	}
	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}

	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}

	public String getModifyUname(){
		return modifyUname;
	}
	public void setModifyUname(String modifyUname) {
		this.modifyUname = modifyUname;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
