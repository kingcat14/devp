package net.aicoder.devp.product.business.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
	 * 元素类型
	 * [元素类型]- SYS_CMP_MDU // 组件对应模块
     */
	@NotNull(message = "元素类型不能为空")
	@ApiModelProperty(value = "元素类型", required = true)
	@Size(max = 255, message = "元素类型超长，最多255个字符")
	private String etype;


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
	 * 记录状态
	 * [记录状态]-0-失效;1-生效;缺省为1
     */
	@ApiModelProperty(value = "记录状态", required = false)
	private Integer recordState;


    /**
	 * 类型
	 * [类型]
     */
	@ApiModelProperty(value = "类型", required = false)
	@Size(max = 255, message = "类型超长，最多255个字符")
	private String type;


    /**
	 * 子类型
	 * [子类型]
     */
	@ApiModelProperty(value = "子类型", required = false)
	@Size(max = 255, message = "子类型超长，最多255个字符")
	private String subType;


    /**
	 * 产品编号
	 * [产品编号]
     */
	@NotNull(message = "产品编号不能为空")
	@ApiModelProperty(value = "产品编号", required = true)
	private Long prdRid;


    /**
	 * 组件编号
	 * [组件编号]
     */
	@NotNull(message = "组件编号不能为空")
	@ApiModelProperty(value = "组件编号", required = true)
	private Long cmpRid;


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

	public Integer getRecordState(){
		return recordState;
	}
	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getSubType(){
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Long getPrdRid(){
		return prdRid;
	}
	public void setPrdRid(Long prdRid) {
		this.prdRid = prdRid;
	}

	public Long getCmpRid(){
		return cmpRid;
	}
	public void setCmpRid(Long cmpRid) {
		this.cmpRid = cmpRid;
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


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
