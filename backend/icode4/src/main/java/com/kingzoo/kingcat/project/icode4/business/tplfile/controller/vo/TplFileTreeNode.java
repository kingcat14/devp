package com.kingzoo.kingcat.project.icode4.business.tplfile.controller.vo;

import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplSet;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class TplFileTreeNode {

    public static final String TYPE_TPL_SET = "TPL_SET";
    public static final String TYPE_TPL_CODE = "TPL_CODE";
	public static final String TYPE_TPL_TYPE = "TPL_TYPE";
	/**
	 * Constructor for ResourceTreeNode.
	 * @param project Project
	 */
	public TplFileTreeNode(TplSet project){
		this.id = project.getId();
		this.name= StringUtils.isEmpty(project.getName())?project.getCode():project.getName();
		this.leaf = false;
		this.iconCls = null;
        this.type = TYPE_TPL_SET;
        this.objectId = project.getId()+"";
	}

    public TplFileTreeNode(TplCode domain){
        this.id = domain.getId()+"";
        this.name= StringUtils.isNotEmpty(domain.getName())?domain.getName():domain.getCode();
        this.leaf = false;
        this.iconCls = "page_code";
        this.type = TYPE_TPL_CODE;
        this.objectId = domain.getId()+"";
    }

	public TplFileTreeNode(String tplSetId, String typeName){
		this.id = tplSetId+"_"+typeName;
		this.name= typeName;
		this.leaf = false;
		this.iconCls = null;
		this.type = TYPE_TPL_TYPE;
		this.objectId = tplSetId;
	}



    private String id;
    private String parentId;
    private String name;
    protected boolean leaf;
	protected String iconCls;
    private String type;
    private String objectId;
    private List<TplFileTreeNode> children;

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

    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<TplFileTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TplFileTreeNode> children) {
        this.children = children;
    }

    public void addChild(TplFileTreeNode node){
	    if(this.children == null){
	        this.children = new ArrayList<>();
        }
        this.children.add(node);
	    node.setParentId(this.getId());
    }
}
