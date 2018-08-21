package net.aicoder.devp.deploy.business.ops.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 资产项目分组映射的值对象
*/
@ApiModel(value = "展现资产项目分组映射的值对象")
public class DevpOpsCiGroupVO {

    @ApiModelProperty(value = "记录id")
   private Long id;

    /**
    * 租户编号
    * [租户编号]
    */
    @ApiModelProperty(value = "租户编号")
    private Long tid;

    /**
    * 元素类型
    * [元素类型]
    */
    @ApiModelProperty(value = "元素类型")
    private String etype;

    /**
    * 名称
    * [名称]
    */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
    * 代码
    * [代码]
    */
    @ApiModelProperty(value = "代码")
    private String code;

    /**
    * 别名
    * [别名]
    */
    @ApiModelProperty(value = "别名")
    private String alias;

    /**
    * 描述
    * [描述]-资产项目所属分组描述
    */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @ApiModelProperty(value = "记录状态")
    private Integer recordState;

    /**
    * 类型代码
    * [类型代码]
    */
    @ApiModelProperty(value = "类型代码")
    private String typeCode;

    /**
    * 类型名称
    * [类型名称]-冗余字段，方便显示
    */
    @ApiModelProperty(value = "类型名称")
    private String typeName;

    /**
    * 分组记录编号
    * [分组记录编号]
    */
    @ApiModelProperty(value = "分组记录编号")
    private Long groupRid;

    /**
    * 资产记录编号
    * [资产记录编号]
    */
    @ApiModelProperty(value = "资产记录编号")
    private Long ciRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @ApiModelProperty(value = "顺序号")
    private Integer seq;

    /**
    * 参数定义标识
    * [参数定义标识]-扩展参数定义的标识
    */
    @ApiModelProperty(value = "参数定义标识")
    private String parasCode;

    /**
    * 修改用户代码
    * [修改用户代码]
    */
    @ApiModelProperty(value = "修改用户代码")
    private String cmodifyUcode;


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
    public String getTypeCode(){
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeName(){
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Long getGroupRid(){
        return groupRid;
    }
    public void setGroupRid(Long groupRid) {
        this.groupRid = groupRid;
    }
    public Long getCiRid(){
        return ciRid;
    }
    public void setCiRid(Long ciRid) {
        this.ciRid = ciRid;
    }
    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public String getParasCode(){
        return parasCode;
    }
    public void setParasCode(String parasCode) {
        this.parasCode = parasCode;
    }
    public String getCmodifyUcode(){
        return cmodifyUcode;
    }
    public void setCmodifyUcode(String cmodifyUcode) {
        this.cmodifyUcode = cmodifyUcode;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}