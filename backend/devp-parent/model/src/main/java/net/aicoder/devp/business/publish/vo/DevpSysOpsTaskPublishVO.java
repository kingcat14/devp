package net.aicoder.devp.business.publish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 发布设置的值对象
*/
@ApiModel(value = "展现发布设置的值对象")
public class DevpSysOpsTaskPublishVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "租户编号", notes = "[租户编号]")
    private Long tid;


    /**元素类型*/
    @ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_OPS_TASK_PUBLISH")
    private String etype;


    /**系统元素名称*/
    @ApiModelProperty(value = "系统元素名称", notes = "[系统元素名称]")
    private String name;


    /**系统元素代码*/
    @ApiModelProperty(value = "系统元素代码", notes = "[系统元素代码]")
    private String code;


    /**系统元素别名*/
    @ApiModelProperty(value = "系统元素别名", notes = "[系统元素别名]")
    private String alias;


    /**系统元素描述*/
    @ApiModelProperty(value = "系统元素描述", notes = "[系统元素描述]")
    private String description;


    @ApiModelProperty(value = "记录状态", notes = "[记录状态]-0-失效;1-生效;缺省为1")
    private Integer recordState;


    /**类型*/
    @ApiModelProperty(value = "类型", notes = "[类型]-软件发布库/Maven私有仓库/文件归档/容器镜像仓库")
    private String type;


    /**子类型*/
    @ApiModelProperty(value = "子类型", notes = "[子类型]")
    private String subType;


    /**参考模板*/
    @ApiModelProperty(value = "参考模板", notes = "[参考模板]-如为Docker时即为对应的dockfile")
    private String template;


    /**发布物分组*/
    @ApiModelProperty(value = "发布物分组", notes = "[发布物分组]")
    private String group;


    /**发布物*/
    @ApiModelProperty(value = "发布物", notes = "[发布物]")
    private String artifact;


    /**版本标识*/
    @ApiModelProperty(value = "版本标识", notes = "[版本标识]")
    private String version;


    /**发布文件名*/
    @ApiModelProperty(value = "发布文件名", notes = "[发布文件名]-jar包、war包等")
    private String publishFile;


    /**状态*/
    @ApiModelProperty(value = "状态", notes = "[状态]")
    private String status;


    /**备注*/
    @ApiModelProperty(value = "备注", notes = "[备注]")
    private String notes;


    @ApiModelProperty(value = "产品编号", notes = "[产品编号]")
    private Long prdRid;


    @ApiModelProperty(value = "部署方案编号", notes = "[部署方案编号]")
    private Long schemeRid;


    @ApiModelProperty(value = "组件编号", notes = "[组件编号]")
    private Long cmpRid;


    @ApiModelProperty(value = "任务编号", notes = "[任务编号]")
    private Long taskRid;


    @ApiModelProperty(value = "顺序号", notes = "[顺序号]")
    private Integer seq;


    /**创建用户代码*/
    @ApiModelProperty(value = "创建用户代码", notes = "[创建用户代码]")
    private String createUcode;


    /**创建用户姓名*/
    @ApiModelProperty(value = "创建用户姓名", notes = "[创建用户姓名]")
    private String createUname;


    /**修改用户代码*/
    @ApiModelProperty(value = "修改用户代码", notes = "[修改用户代码]")
    private String modifyUcode;


    /**修改用户姓名*/
    @ApiModelProperty(value = "修改用户姓名", notes = "[修改用户姓名]")
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

    public String getTemplate(){
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
    }

    public String getGroup(){
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public String getArtifact(){
        return artifact;
    }
    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getVersion(){
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public String getPublishFile(){
        return publishFile;
    }
    public void setPublishFile(String publishFile) {
        this.publishFile = publishFile;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getPrdRid(){
        return prdRid;
    }
    public void setPrdRid(Long prdRid) {
        this.prdRid = prdRid;
    }

    public Long getSchemeRid(){
        return schemeRid;
    }
    public void setSchemeRid(Long schemeRid) {
        this.schemeRid = schemeRid;
    }

    public Long getCmpRid(){
        return cmpRid;
    }
    public void setCmpRid(Long cmpRid) {
        this.cmpRid = cmpRid;
    }

    public Long getTaskRid(){
        return taskRid;
    }
    public void setTaskRid(Long taskRid) {
        this.taskRid = taskRid;
    }

    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
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