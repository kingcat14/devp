package net.aicoder.speedcloud.business.deploy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;


/**
* 方案资源间关系的值对象
*/
@ApiModel(value = "展现方案资源间关系的值对象")
public class DevpSysDpyResourceTreeNode {



    @ApiModelProperty(value = "是否叶子节点")
    protected boolean leaf;
    @ApiModelProperty(value = "标签")
    protected String iconCls;
    @ApiModelProperty(value = "是否展开")
    protected boolean expanded;
    @ApiModelProperty(value = "关联的记录ID")
    private String objId;
    @ApiModelProperty(value = "子元素")
    private List<DevpSysDpyResourceTreeNode> children;

    @ApiModelProperty(value = "节点ID")
    private String id;

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


    /**对应关系ID*/
    @ApiModelProperty(value = "对应关系代码", notes = "[对应关系代码]")
    private String relationId;

    /**对应关系代码*/
    @ApiModelProperty(value = "对应关系代码", notes = "[对应关系代码]")
    private String relationCode;


    /**对应关系名称*/
    @ApiModelProperty(value = "对应关系名称", notes = "[对应关系名称]")
    private String relationName;

    /**对应关系别名*/
    @ApiModelProperty(value = "对应关系别名", notes = "[对应关系别名]")
    private String relationAlias;



    /**对应关系描述*/
    @ApiModelProperty(value = "对应关系描述", notes = "[对应关系描述]")
    private String relationDesc;


    @ApiModelProperty(value = "顺序号", notes = "[顺序号]")
    private Integer relationSeq;

    /**对应关系方向*/
    @ApiModelProperty(value = "对应关系方向", notes = "[方向]-(保留)")
    private String relationDirection;


    public String getRelationId() {
        return relationId;
    }
    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getRelationCode() {
        return relationCode;
    }
    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public String getRelationName() {
        return relationName;
    }
    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationAlias() {
        return relationAlias;
    }
    public void setRelationAlias(String relationAlias) {
        this.relationAlias = relationAlias;
    }

    public List<DevpSysDpyResourceTreeNode> getChildren() {
        return children;
    }
    public void setChildren(List<DevpSysDpyResourceTreeNode> children) {
        this.children = children;
    }
    public void addChild(DevpSysDpyResourceTreeNode node){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(node);
        expanded = true;
        leaf = false;
    }

    public String getObjId() {
        return objId;
    }
    public void setObjId(String objId) {
        this.objId = objId;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }



    public String getRelationDesc() {
        return relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    public Integer getRelationSeq() {
        return relationSeq;
    }

    public void setRelationSeq(Integer relationSeq) {
        this.relationSeq = relationSeq;
    }

    public String getRelationDirection() {
        return relationDirection;
    }

    public void setRelationDirection(String relationDirection) {
        this.relationDirection = relationDirection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public DevpSysDpyResourcesCategoryVO getCategoryVO() {
        return categoryVO;
    }

    public void setCategoryVO(DevpSysDpyResourcesCategoryVO categoryVO) {
        this.categoryVO = categoryVO;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public DevpSysDpyResourcesTypeVO getTypeVO() {
        return typeVO;
    }

    public void setTypeVO(DevpSysDpyResourcesTypeVO typeVO) {
        this.typeVO = typeVO;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}