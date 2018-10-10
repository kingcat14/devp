package net.aicoder.speedcloud.business.deploy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesCategoryVO;
import net.aicoder.speedcloud.business.deploy.vo.DevpSysDpyResourcesTypeVO;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 方案资源的值对象
*/
@ApiModel(value = "展现方案资源的值对象")
public class DevpSysDpyResourcesVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    /**资源名称*/
    @ApiModelProperty(value = "资源名称", notes = "[资源名称]")
    private String name;


    /**资源代码*/
    @ApiModelProperty(value = "资源代码", notes = "[资源代码]")
    private String code;


    /**资源别名*/
    @ApiModelProperty(value = "资源别名", notes = "[资源别名]")
    private String alias;


    /**资源类别*/
    @ApiModelProperty(value = "资源类别")
    private Long category;
    private DevpSysDpyResourcesCategoryVO categoryVO;


    /**资源类型*/
    @ApiModelProperty(value = "资源类型", notes = "[类型]-运行环境/数据库/消息队列/缓存/外部接口")
    private Long type;
    private DevpSysDpyResourcesTypeVO typeVO;


    /**备注*/
    @ApiModelProperty(value = "备注", notes = "[备注]")
    private String notes;


    /**资源描述*/
    @ApiModelProperty(value = "资源描述", notes = "[资源描述]")
    private String description;


    /**版本*/
    @ApiModelProperty(value = "版本", notes = "[版本]")
    private String version;


    @ApiModelProperty(value = "顺序号")
    private Integer seq;


    /**所属环境*/
    @ApiModelProperty(value = "所属环境")
    private Long evn;
    private AppEnvConfigVO evnVO;


    /**产品编号*/
    @ApiModelProperty(value = "产品编号", notes = "[产品编号]")
    private Long project;
    private ProjectVO projectVO;


    @ApiModelProperty(value = "外部资源")
    private Boolean outerResource;


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

    public Long getCategory(){
        return category;
    }
    public void setCategory(Long category) {
        this.category = category;
    }
    public DevpSysDpyResourcesCategoryVO getCategoryVO(){
        return categoryVO;
    }
    public void setCategoryVO(DevpSysDpyResourcesCategoryVO categoryVO) {
        this.categoryVO = categoryVO;
    }

    public Long getType(){
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
    public DevpSysDpyResourcesTypeVO getTypeVO(){
        return typeVO;
    }
    public void setTypeVO(DevpSysDpyResourcesTypeVO typeVO) {
        this.typeVO = typeVO;
    }

    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion(){
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getEvn(){
        return evn;
    }
    public void setEvn(Long evn) {
        this.evn = evn;
    }
    public AppEnvConfigVO getEvnVO(){
        return evnVO;
    }
    public void setEvnVO(AppEnvConfigVO evnVO) {
        this.evnVO = evnVO;
    }

    public Long getProject(){
        return project;
    }
    public void setProject(Long project) {
        this.project = project;
    }
    public ProjectVO getProjectVO(){
        return projectVO;
    }
    public void setProjectVO(ProjectVO projectVO) {
        this.projectVO = projectVO;
    }

    public Boolean getOuterResource(){
        return outerResource;
    }
    public void setOuterResource(Boolean outerResource) {
        this.outerResource = outerResource;
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