package net.aicoder.speedcloud.business.deployscheme.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceVO;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceRelationTypeVO;
import net.aicoder.speedcloud.business.deployscheme.vo.SchemeVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 方案资源间关系的值对象
*/
@ApiModel(value = "展现方案资源间关系的值对象")
public class ResourceRelationVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**主资源*/
    @ApiModelProperty(value = "主资源", notes = "[元素类型]-SYS_DPY_CMP_REF // 部署组件关联元素")
    private Long resource;
    private ResourceVO resourceVO;


    /**关联资源*/
    @ApiModelProperty(value = "关联资源", notes = "[关联产品编号]")
    private Long refResource;
    private ResourceVO refResourceVO;


    /**对应关系类型*/
    @ApiModelProperty(value = "对应关系类型", notes = "[类型]-关联类型：部署到、连接、调用")
    private Long type;
    private ResourceRelationTypeVO typeVO;


    /**对应关系代码*/
    @ApiModelProperty(value = "对应关系代码", notes = "[对应关系代码]")
    private String code;


    /**对应关系名称*/
    @ApiModelProperty(value = "对应关系名称", notes = "[对应关系名称]")
    private String name;


    /**对应关系别名*/
    @ApiModelProperty(value = "对应关系别名", notes = "[对应关系别名]")
    private String alias;


    /**对应关系描述*/
    @ApiModelProperty(value = "对应关系描述", notes = "[对应关系描述]")
    private String description;


    /**部署方案编号*/
    @ApiModelProperty(value = "部署方案编号", notes = "[部署方案编号]")
    private Long scheme;
    private SchemeVO schemeVO;


    @ApiModelProperty(value = "顺序号", notes = "[顺序号]")
    private Integer seq;


    /**对应关系方向*/
    @ApiModelProperty(value = "对应关系方向", notes = "[方向]-(保留)")
    private String direction;


    public Long getResource(){
        return resource;
    }
    public void setResource(Long resource) {
        this.resource = resource;
    }
    public ResourceVO getResourceVO(){
        return resourceVO;
    }
    public void setResourceVO(ResourceVO resourceVO) {
        this.resourceVO = resourceVO;
    }

    public Long getRefResource(){
        return refResource;
    }
    public void setRefResource(Long refResource) {
        this.refResource = refResource;
    }
    public ResourceVO getRefResourceVO(){
        return refResourceVO;
    }
    public void setRefResourceVO(ResourceVO refResourceVO) {
        this.refResourceVO = refResourceVO;
    }

    public Long getType(){
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
    public ResourceRelationTypeVO getTypeVO(){
        return typeVO;
    }
    public void setTypeVO(ResourceRelationTypeVO typeVO) {
        this.typeVO = typeVO;
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

    public Long getScheme(){
        return scheme;
    }
    public void setScheme(Long scheme) {
        this.scheme = scheme;
    }
    public SchemeVO getSchemeVO(){
        return schemeVO;
    }
    public void setSchemeVO(SchemeVO schemeVO) {
        this.schemeVO = schemeVO;
    }

    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDirection(){
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
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