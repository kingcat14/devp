package net.aicoder.devp.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 功能模块
 * @author icode
 */
@ApiModel(value = "新增功能模块使用的DTO")
public class DevpDevFunaModuleAddDto {

    /**租户编号*/
	@ApiModelProperty(value = "租户编号", required = false, notes = "[租户编号]")
	private Long tid;

    /**模块名称*/
	@ApiModelProperty(value = "模块名称", required = false, notes = "[模块名称]")
	private String name;

    /**模块代码*/
	@ApiModelProperty(value = "模块代码", required = false, notes = "[模块代码]")
	private String code;

    /**模块别名*/
	@ApiModelProperty(value = "模块别名", required = false, notes = "[模块别名]")
	private String alias;

    /**模块描述*/
	@ApiModelProperty(value = "模块描述", required = false, notes = "[模块描述]")
	private String description;

    /**模块标志*/
	@ApiModelProperty(value = "模块标志", required = false, notes = "[模块标志]-0:产品;1:模块;2:功能")
	private Integer mduFlag;

    /**类型*/
	@ApiModelProperty(value = "类型", required = false, notes = "[类型]")
	private String type;

    /**构造型*/
	@ApiModelProperty(value = "构造型", required = false, notes = "[构造型]")
	private String stereotype;

    /**访问控制范围*/
	@ApiModelProperty(value = "访问控制范围", required = false, notes = "[访问控制范围]-访问控制范围:产品内可见，同模块可见，模块内可见")
	private String scope;

    /**使用者*/
	@ApiModelProperty(value = "使用者", required = false, notes = "[使用者]")
	private String actor;

    /**优先级*/
	@ApiModelProperty(value = "优先级", required = false, notes = "[优先级]")
	private String priority;

    /**版本*/
	@ApiModelProperty(value = "版本", required = false, notes = "[版本]-当前版本")
	private String version;

    /**阶段*/
	@ApiModelProperty(value = "阶段", required = false, notes = "[阶段]-需求调研，需求分析,软件设计,软件开发，系统测试，功能交付")
	private String phase;

    /**状态*/
	@ApiModelProperty(value = "状态", required = false, notes = "[状态]-未开始,进行中,已完成,暂停,取消")
	private String status;

    /**产品编号*/
	@ApiModelProperty(value = "产品编号", required = false, notes = "[产品编号]")
	private Long prdRid;

    /**父模块编号*/
	@ApiModelProperty(value = "父模块编号", required = false, notes = "[父模块编号]")
	private Long parentRid;

    /**顺序号*/
	@ApiModelProperty(value = "顺序号", required = false, notes = "[顺序号]")
	private Integer seq;

    /**备注*/
	@ApiModelProperty(value = "备注", required = false, notes = "[备注]")
	private String notes;

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

	public Integer getMduFlag(){
		return mduFlag;
	}
	public void setMduFlag(Integer mduFlag) {
		this.mduFlag = mduFlag;
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

	public String getScope(){
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getActor(){
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getPriority(){
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
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

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
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

	public String getNotes(){
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
