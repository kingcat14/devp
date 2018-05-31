package net.aicoder.devp.product.business.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;




/**
* 系统元素实例的值对象
*/
@ApiModel(value = "展现系统元素实例的值对象")
public class DevpSysElmInstInfoVO {

    @ApiModelProperty(value = "记录id")
    private Long id;

    @ApiModelProperty(value = "租户编号")
    /**
    * 租户编号
    * [租户编号]
    */
    private Long tid;

    @ApiModelProperty(value = "扩展信息代码")
    /**
    * 扩展信息代码
    * [扩展信息代码]
    */
    private String code;

    @ApiModelProperty(value = "扩展信息名称")
    /**
    * 扩展信息名称
    * [扩展信息名称]-显示名称
    */
    private String name;

    @ApiModelProperty(value = "扩展信息别名")
    /**
    * 扩展信息别名
    * [扩展信息别名]
    */
    private String alias;

    @ApiModelProperty(value = "扩展信息描述")
    /**
    * 扩展信息描述
    * [扩展信息描述]-对应当前属性值
    */
    private String description;

    @ApiModelProperty(value = "产品编号")
    /**
    * 产品编号
    * [产品编号]
    */
    private Long prdRid;

    @ApiModelProperty(value = "关联连接编号")
    /**
    * 关联连接编号
    * [关联连接编号]
    */
    private Long contRid;

    @ApiModelProperty(value = "来源产品编号")
    /**
    * 来源产品编号
    * [来源产品编号]
    */
    private Long sprdRid;

    @ApiModelProperty(value = "来源系统元素编号")
    /**
    * 来源系统元素编号
    * [来源系统元素编号]
    */
    private Long selmRid;

    @ApiModelProperty(value = "目标产品编号")
    /**
    * 目标产品编号
    * [目标产品编号]
    */
    private Long dprdRid;

    @ApiModelProperty(value = "目标系统元素编号")
    /**
    * 目标系统元素编号
    * [目标系统元素编号]
    */
    private Long delmRid;

    @ApiModelProperty(value = "来源系统元素实例编号")
    /**
    * 来源系统元素实例编号
    * [来源系统元素实例编号]-缺省值为0
    */
    private Long sinstRid;

    @ApiModelProperty(value = "目标系统元素实例编号")
    /**
    * 目标系统元素实例编号
    * [目标系统元素实例编号]-缺省值为0
    */
    private Long dinstRid;

    @ApiModelProperty(value = "顺序号")
    /**
    * 顺序号
    * [顺序号]
    */
    private Integer seq;

    @ApiModelProperty(value = "设值方式")
    /**
    * 设值方式
    * [设值方式]-S-仅对来源,D-仅对目标,Bi-双方,N-均无效
    */
    private String type;

    @ApiModelProperty(value = "信息值1")
    /**
    * 信息值1
    * [信息值1]
    */
    private String infoValue1;

    @ApiModelProperty(value = "信息值2")
    /**
    * 信息值2
    * [信息值2]
    */
    private String infoValue2;

    @ApiModelProperty(value = "信息值3")
    /**
    * 信息值3
    * [信息值3]
    */
    private String infoValue3;

    @ApiModelProperty(value = "信息值4")
    /**
    * 信息值4
    * [信息值4]
    */
    private String infoValue4;

    @ApiModelProperty(value = "信息值5")
    /**
    * 信息值5
    * [信息值5]
    */
    private String infoValue5;

    @ApiModelProperty(value = "备注")
    /**
    * 备注
    * [备注]
    */
    private String notes;

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
    public Long getPrdRid(){
        return prdRid;
    }
    public void setPrdRid(Long prdRid) {
        this.prdRid = prdRid;
    }
    public Long getContRid(){
        return contRid;
    }
    public void setContRid(Long contRid) {
        this.contRid = contRid;
    }
    public Long getSprdRid(){
        return sprdRid;
    }
    public void setSprdRid(Long sprdRid) {
        this.sprdRid = sprdRid;
    }
    public Long getSelmRid(){
        return selmRid;
    }
    public void setSelmRid(Long selmRid) {
        this.selmRid = selmRid;
    }
    public Long getDprdRid(){
        return dprdRid;
    }
    public void setDprdRid(Long dprdRid) {
        this.dprdRid = dprdRid;
    }
    public Long getDelmRid(){
        return delmRid;
    }
    public void setDelmRid(Long delmRid) {
        this.delmRid = delmRid;
    }
    public Long getSinstRid(){
        return sinstRid;
    }
    public void setSinstRid(Long sinstRid) {
        this.sinstRid = sinstRid;
    }
    public Long getDinstRid(){
        return dinstRid;
    }
    public void setDinstRid(Long dinstRid) {
        this.dinstRid = dinstRid;
    }
    public Integer getSeq(){
        return seq;
    }
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public String getType(){
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getInfoValue1(){
        return infoValue1;
    }
    public void setInfoValue1(String infoValue1) {
        this.infoValue1 = infoValue1;
    }
    public String getInfoValue2(){
        return infoValue2;
    }
    public void setInfoValue2(String infoValue2) {
        this.infoValue2 = infoValue2;
    }
    public String getInfoValue3(){
        return infoValue3;
    }
    public void setInfoValue3(String infoValue3) {
        this.infoValue3 = infoValue3;
    }
    public String getInfoValue4(){
        return infoValue4;
    }
    public void setInfoValue4(String infoValue4) {
        this.infoValue4 = infoValue4;
    }
    public String getInfoValue5(){
        return infoValue5;
    }
    public void setInfoValue5(String infoValue5) {
        this.infoValue5 = infoValue5;
    }
    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
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