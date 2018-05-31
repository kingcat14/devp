package net.aicoder.devp.product.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 组件对应模块
 * @author icode
 */
@ApiModel(value = "修改组件对应模块使用的DTO")
public class DevpSysCmpModuleEditDto {

    /**
	 * 租户编号
	 * [租户编号]
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 对应关系名称
	 * [对应关系名称]
     */
	@ApiModelProperty(value = "对应关系名称", required = false)
	@Size(max = 255, message = "对应关系名称超长，最多255个字符")
	private String name;

    /**
	 * 对应关系代码
	 * [对应关系代码]
     */
	@ApiModelProperty(value = "对应关系代码", required = false)
	@Size(max = 255, message = "对应关系代码超长，最多255个字符")
	private String code;

    /**
	 * 对应关系别名
	 * [对应关系别名]
     */
	@ApiModelProperty(value = "对应关系别名", required = false)
	@Size(max = 255, message = "对应关系别名超长，最多255个字符")
	private String alias;

    /**
	 * 对应关系描述
	 * [对应关系描述]
     */
	@ApiModelProperty(value = "对应关系描述", required = false)
	@Size(max = 255, message = "对应关系描述超长，最多255个字符")
	private String description;

    /**
	 * 对应关系类型
	 * [对应关系类型]
     */
	@ApiModelProperty(value = "对应关系类型", required = false)
	@Size(max = 255, message = "对应关系类型超长，最多255个字符")
	private String type;

    /**
	 * 构造型
	 * [构造型]
     */
	@ApiModelProperty(value = "构造型", required = false)
	@Size(max = 255, message = "构造型超长，最多255个字符")
	private String stereotype;

    /**
	 * 范围
	 * [范围]-(保留)
     */
	@ApiModelProperty(value = "范围", required = false)
	@Size(max = 255, message = "范围超长，最多255个字符")
	private String scope;

    /**
	 * 版本
	 * [版本]-(保留)
     */
	@ApiModelProperty(value = "版本", required = false)
	@Size(max = 255, message = "版本超长，最多255个字符")
	private String version;

    /**
	 * 阶段
	 * [阶段]-(保留)
     */
	@ApiModelProperty(value = "阶段", required = false)
	@Size(max = 255, message = "阶段超长，最多255个字符")
	private String phase;

    /**
	 * 状态
	 * [状态]-(保留)
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
	 * 产品编号
	 * [产品编号]
     */
	@NotNull(message = "产品编号不能为空")
	@ApiModelProperty(value = "产品编号", required = true)
	private Long prdRid;

    /**
	 * 系统元素编号
	 * [系统元素编号]
     */
	@NotNull(message = "系统元素编号不能为空")
	@ApiModelProperty(value = "系统元素编号", required = true)
	private Long elmRid;

    /**
	 * 模块编号
	 * [模块编号]
     */
	@NotNull(message = "模块编号不能为空")
	@ApiModelProperty(value = "模块编号", required = true)
	private Long mduRid;

    /**
	 * 顺序号
	 * [顺序号]
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;

    /**
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;

    /**
	 * 创建用户代码
	 * [创建用户代码]
     */
	@ApiModelProperty(value = "创建用户代码", required = false)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
	 * 修改用户代码
	 * [修改用户代码]
     */
	@ApiModelProperty(value = "修改用户代码", required = false)
	@Size(max = 255, message = "修改用户代码超长，最多255个字符")
	private String modifyUcode;


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

	public Long getElmRid(){
		return elmRid;
	}
	public void setElmRid(Long elmRid) {
		this.elmRid = elmRid;
	}

	public Long getMduRid(){
		return mduRid;
	}
	public void setMduRid(Long mduRid) {
		this.mduRid = mduRid;
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

	public String getModifyUcode(){
		return modifyUcode;
	}
	public void setModifyUcode(String modifyUcode) {
		this.modifyUcode = modifyUcode;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
