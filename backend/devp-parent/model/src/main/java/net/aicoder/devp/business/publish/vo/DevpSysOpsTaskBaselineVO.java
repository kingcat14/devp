package net.aicoder.devp.business.publish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 基线设置的值对象
*/
@ApiModel(value = "展现基线设置的值对象")
public class DevpSysOpsTaskBaselineVO {

    @ApiModelProperty(value = "记录id")
    private Long id;


    @ApiModelProperty(value = "租户编号", notes = "[租户编号]")
    private Long tid;


    /**元素类型*/
    @ApiModelProperty(value = "元素类型", notes = "[元素类型]-SYS_OPS_TASK_CONFIG // 生成配置任务")
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
    @ApiModelProperty(value = "类型", notes = "[类型]")
    private String type;


    /**子类型*/
    @ApiModelProperty(value = "子类型", notes = "[子类型]")
    private String subType;


    @ApiModelProperty(value = "采用相同代码仓库", notes = "[采用相同代码仓库]-代码仓库与产品部署方案配置相同")
    private Integer cmSameas;


    /**代码仓库类型*/
    @ApiModelProperty(value = "代码仓库类型", notes = "[代码仓库类型]-svn/github/gitlib")
    private String cmType;


    /**仓库地址*/
    @ApiModelProperty(value = "仓库地址", notes = "[仓库地址]")
    private String cmRepository;


    /**分支标识*/
    @ApiModelProperty(value = "分支标识", notes = "[分支标识]")
    private String cmTag;


    /**用户名*/
    @ApiModelProperty(value = "用户名", notes = "[用户名]")
    private String cmUser;


    /**密码*/
    @ApiModelProperty(value = "密码", notes = "[密码]")
    private String cmPassword;


    /**子目录*/
    @ApiModelProperty(value = "子目录", notes = "[子目录]")
    private String subDir;


    /**基线标识*/
    @ApiModelProperty(value = "基线标识", notes = "[基线标识]")
    private String baseline;


    /**来源代码路径*/
    @ApiModelProperty(value = "来源代码路径", notes = "[来源代码路径]")
    private String svnFromUrl;


    /**目标基线路径*/
    @ApiModelProperty(value = "目标基线路径", notes = "[目标基线路径]")
    private String svnToUrl;


    /**操作方式*/
    @ApiModelProperty(value = "操作方式", notes = "[操作方式]-当前标识基线存在时的处理方式：提示/忽略/覆盖")
    private String operType;


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

    public Integer getCmSameas(){
        return cmSameas;
    }
    public void setCmSameas(Integer cmSameas) {
        this.cmSameas = cmSameas;
    }

    public String getCmType(){
        return cmType;
    }
    public void setCmType(String cmType) {
        this.cmType = cmType;
    }

    public String getCmRepository(){
        return cmRepository;
    }
    public void setCmRepository(String cmRepository) {
        this.cmRepository = cmRepository;
    }

    public String getCmTag(){
        return cmTag;
    }
    public void setCmTag(String cmTag) {
        this.cmTag = cmTag;
    }

    public String getCmUser(){
        return cmUser;
    }
    public void setCmUser(String cmUser) {
        this.cmUser = cmUser;
    }

    public String getCmPassword(){
        return cmPassword;
    }
    public void setCmPassword(String cmPassword) {
        this.cmPassword = cmPassword;
    }

    public String getSubDir(){
        return subDir;
    }
    public void setSubDir(String subDir) {
        this.subDir = subDir;
    }

    public String getBaseline(){
        return baseline;
    }
    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getSvnFromUrl(){
        return svnFromUrl;
    }
    public void setSvnFromUrl(String svnFromUrl) {
        this.svnFromUrl = svnFromUrl;
    }

    public String getSvnToUrl(){
        return svnToUrl;
    }
    public void setSvnToUrl(String svnToUrl) {
        this.svnToUrl = svnToUrl;
    }

    public String getOperType(){
        return operType;
    }
    public void setOperType(String operType) {
        this.operType = operType;
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