package net.aicoder.devp.business.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;


/**
* 产品线定义的值对象
*/
@ApiModel(value = "展现产品线定义的值对象")
public class DevpPrdPrdlineVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "产品线代码")
    /**
    * 产品线代码
    * 
    */
    private String code;

    @ApiModelProperty(value = "产品线名称")
    /**
    * 产品线名称
    * 
    */
    private String name;

    @ApiModelProperty(value = "产品线别名")
    /**
    * 产品线别名
    * 
    */
    private String alias;

    @ApiModelProperty(value = "产品线描述")
    /**
    * 产品线描述
    * 
    */
    private String description;

    @ApiModelProperty(value = "产品线类型")
    /**
    * 产品线类型
    * 
    */
    private String type;

    @ApiModelProperty(value = "领域")
    /**
    * 领域
    * 
    */
    private String domain;

    @ApiModelProperty(value = "构造型")
    /**
    * 构造型
    * 
    */
    private String stereotype;

    @ApiModelProperty(value = "访问控制范围")
    /**
    * 访问控制范围
    * 
    */
    private String scope;

    @ApiModelProperty(value = "版本")
    /**
    * 版本
    * 
    */
    private String version;

    @ApiModelProperty(value = "阶段")
    /**
    * 阶段
    * 
    */
    private String phase;

    @ApiModelProperty(value = "状态")
    /**
    * 状态
    * 
    */
    private String status;

    @ApiModelProperty(value = "父产品线编号")
    /**
    * 父产品线编号
    * 
    */
    private Long parentRid;

    @ApiModelProperty(value = "顺序号")
    /**
    * 顺序号
    * 
    */
    private Integer seq;

    @ApiModelProperty(value = "记录状态")
    /**
    * 记录状态
    * 
    */
    private Integer recordState;

    @ApiModelProperty(value = "创建用户代码")
    /**
    * 创建用户代码
    * 
    */
    private String createUcode;

    @ApiModelProperty(value = "修改用户代码")
    /**
    * 修改用户代码
    * 
    */
    private String modifyUcode;

    @ApiModelProperty(value = "产品线包含的产品")
    private List<DevpPrdProductVO> productList;


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
    public String getModifyUcode(){
        return modifyUcode;
    }
    public void setModifyUcode(String modifyUcode) {
        this.modifyUcode = modifyUcode;
    }

    public List<DevpPrdProductVO> getProductList() {
        return productList;
    }
    public void setProductList(List<DevpPrdProductVO> productList) {
        this.productList = productList;
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