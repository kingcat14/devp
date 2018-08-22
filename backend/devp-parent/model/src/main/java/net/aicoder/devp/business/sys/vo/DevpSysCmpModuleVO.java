package net.aicoder.devp.business.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
* 组件对应模块的值对象
*/
@ApiModel(value = "展现组件对应模块的值对象")
public class DevpSysCmpModuleVO {

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
    * [元素类型]- SYS_CMP_MDU // 组件对应模块
    */
    @ApiModelProperty(value = "元素类型")
    private String etype;

    /**
    * 对应关系名称
    * [对应关系名称]
    */
    @ApiModelProperty(value = "对应关系名称")
    private String name;

    /**
    * 对应关系代码
    * [对应关系代码]
    */
    @ApiModelProperty(value = "对应关系代码")
    private String code;

    /**
    * 对应关系别名
    * [对应关系别名]
    */
    @ApiModelProperty(value = "对应关系别名")
    private String alias;

    /**
    * 对应关系描述
    * [对应关系描述]
    */
    @ApiModelProperty(value = "对应关系描述")
    private String description;

    /**
    * 记录状态
    * [记录状态]-0-失效;1-生效;缺省为1
    */
    @ApiModelProperty(value = "记录状态")
    private Integer recordState;

    /**
    * 类型
    * [类型]
    */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
    * 子类型
    * [子类型]
    */
    @ApiModelProperty(value = "子类型")
    private String subType;

    /**
    * 产品编号
    * [产品编号]
    */
    @ApiModelProperty(value = "产品编号")
    private Long prdRid;

    /**
    * 组件编号
    * [组件编号]
    */
    @ApiModelProperty(value = "组件编号")
    private Long cmpRid;

    /**
    * 模块编号
    * [模块编号]
    */
    @ApiModelProperty(value = "模块编号")
    private Long mduRid;

    /**
    * 顺序号
    * [顺序号]
    */
    @ApiModelProperty(value = "顺序号")
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