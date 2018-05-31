package net.aicoder.devp.product.business.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 组件对应模块的值对象
*/
@ApiModel(value = "展现组件对应模块的值对象")
public class DevpSysCmpModuleVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "租户编号")
    /**
    * 租户编号
    * [租户编号]
    */
    private Long tid;

    @ApiModelProperty(value = "对应关系名称")
    /**
    * 对应关系名称
    * [对应关系名称]
    */
    private String name;

    @ApiModelProperty(value = "对应关系代码")
    /**
    * 对应关系代码
    * [对应关系代码]
    */
    private String code;

    @ApiModelProperty(value = "对应关系别名")
    /**
    * 对应关系别名
    * [对应关系别名]
    */
    private String alias;

    @ApiModelProperty(value = "对应关系描述")
    /**
    * 对应关系描述
    * [对应关系描述]
    */
    private String description;

    @ApiModelProperty(value = "对应关系类型")
    /**
    * 对应关系类型
    * [对应关系类型]
    */
    private String type;

    @ApiModelProperty(value = "构造型")
    /**
    * 构造型
    * [构造型]
    */
    private String stereotype;

    @ApiModelProperty(value = "范围")
    /**
    * 范围
    * [范围]-(保留)
    */
    private String scope;

    @ApiModelProperty(value = "版本")
    /**
    * 版本
    * [版本]-(保留)
    */
    private String version;

    @ApiModelProperty(value = "阶段")
    /**
    * 阶段
    * [阶段]-(保留)
    */
    private String phase;

    @ApiModelProperty(value = "状态")
    /**
    * 状态
    * [状态]-(保留)
    */
    private String status;

    @ApiModelProperty(value = "产品编号")
    /**
    * 产品编号
    * [产品编号]
    */
    private Long prdRid;

    @ApiModelProperty(value = "系统元素编号")
    /**
    * 系统元素编号
    * [系统元素编号]
    */
    private Long elmRid;

    @ApiModelProperty(value = "模块编号")
    /**
    * 模块编号
    * [模块编号]
    */
    private Long mduRid;

    @ApiModelProperty(value = "顺序号")
    /**
    * 顺序号
    * [顺序号]
    */
    private Integer seq;

    @ApiModelProperty(value = "记录状态")
    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    private Integer recordState;

    @ApiModelProperty(value = "创建用户代码")
    /**
    * 创建用户代码
    * [创建用户代码]
    */
    private String createUcode;

    @ApiModelProperty(value = "修改用户代码")
    /**
    * 修改用户代码
    * [修改用户代码]
    */
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