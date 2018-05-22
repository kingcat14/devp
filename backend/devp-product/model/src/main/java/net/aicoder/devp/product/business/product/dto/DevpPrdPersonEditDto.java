package net.aicoder.devp.product.business.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
 * 产品干系人
 * @author icode
 */
@ApiModel(value = "修改产品干系人使用的DTO")
public class DevpPrdPersonEditDto {

    /**
	 * 租户编号
	 * 
     */
	@NotNull(message = "租户编号不能为空")
	@ApiModelProperty(value = "租户编号", required = true)
	private Long tid;

    /**
	 * 用户代码
	 * 
     */
	@NotNull(message = "用户代码不能为空")
	@ApiModelProperty(value = "用户代码", required = true)
	@Size(max = 255, message = "用户代码超长，最多255个字符")
	private String code;

    /**
	 * 用户名称
	 * 
     */
	@NotNull(message = "用户名称不能为空")
	@ApiModelProperty(value = "用户名称", required = true)
	@Size(max = 255, message = "用户名称超长，最多255个字符")
	private String name;

    /**
	 * 用户别名
	 * 
     */
	@ApiModelProperty(value = "用户别名", required = false)
	@Size(max = 255, message = "用户别名超长，最多255个字符")
	private String alias;

    /**
	 * 用户描述
	 * 
     */
	@ApiModelProperty(value = "用户描述", required = false)
	@Size(max = 255, message = "用户描述超长，最多255个字符")
	private String description;

    /**
	 * 关联元素类型
	 * 
     */
	@NotNull(message = "关联元素类型不能为空")
	@ApiModelProperty(value = "关联元素类型", required = true)
	@Size(max = 255, message = "关联元素类型超长，最多255个字符")
	private String nexusType;

    /**
	 * 关联元素编号
	 * 
     */
	@NotNull(message = "关联元素编号不能为空")
	@ApiModelProperty(value = "关联元素编号", required = true)
	private Long nexusRid;

    /**
	 * 顺序号
	 * 
     */
	@ApiModelProperty(value = "顺序号", required = false)
	private Integer seq;

    /**
	 * 用户编号
	 * 
     */
	@NotNull(message = "用户编号不能为空")
	@ApiModelProperty(value = "用户编号", required = true)
	private Long uid;

    /**
	 * 用户类型
	 * 
     */
	@ApiModelProperty(value = "用户类型", required = false)
	@Size(max = 255, message = "用户类型超长，最多255个字符")
	private String type;

    /**
	 * 用户类型
	 * 
     */
	@ApiModelProperty(value = "用户类型", required = false)
	@Size(max = 255, message = "用户类型超长，最多255个字符")
	private String role;

    /**
	 * 状态
	 * 
     */
	@ApiModelProperty(value = "状态", required = false)
	@Size(max = 255, message = "状态超长，最多255个字符")
	private String status;

    /**
	 * 用户租户编号
	 * 
     */
	@ApiModelProperty(value = "用户租户编号", required = false)
	private Long userTid;

    /**
	 * 组织编号
	 * 
     */
	@ApiModelProperty(value = "组织编号", required = false)
	private Long orgRid;

    /**
	 * 组织名称
	 * 
     */
	@ApiModelProperty(value = "组织名称", required = false)
	@Size(max = 255, message = "组织名称超长，最多255个字符")
	private String orgName;

    /**
	 * 记录状态
	 * 
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;

    /**
	 * 创建用户代码
	 * 
     */
	@ApiModelProperty(value = "创建用户代码", required = false)
	@Size(max = 255, message = "创建用户代码超长，最多255个字符")
	private String createUcode;

    /**
	 * 修改用户代码
	 * 
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

	public Integer getSeq(){
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getUid(){
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
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

	public Long getOrgRid(){
		return orgRid;
	}
	public void setOrgRid(Long orgRid) {
		this.orgRid = orgRid;
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
