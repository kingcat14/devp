package com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo;

import com.kingzoo.kingcat.project.icode4.business.codegen.domain.Project;
import com.kingzoo.kingcat.project.icode4.business.tplfile.domain.TplCode;
import org.apache.commons.lang3.StringUtils;

/**
 */
public class ProjectTplFileTreeNode {

    public static final String TYPE_PROJECT = "TPL_PROJECT";
    public static final String TYPE_TPL_CODE = "TPL_CODE";
	public static final String TYPE_TPL_TYPE = "TPL_TYPE";
	/**
	 * Constructor for ResourceTreeNode.
	 * @param project Project
	 */
    public ProjectTplFileTreeNode(Project project){
        this.id = project.getId();
        this.name = "("+project.getGroupCode()+")"+(StringUtils.isEmpty(project.getName())?project.getCode():project.getName());
        this.leaf = false;
        this.iconCls = null;
        this.type = TYPE_PROJECT;
        this.objectId = project.getId()+"";
    }


    public ProjectTplFileTreeNode(String projectId, TplCode tplCode){
        this.id = projectId + "_" + tplCode.getId()+"";
        this.name= StringUtils.isNotEmpty(tplCode.getName())?tplCode.getName():tplCode.getCode();
        this.leaf = false;
        this.iconCls = "page_code";
        this.type = TYPE_TPL_CODE;
        this.objectId = tplCode.getId()+"";
        this.projectId = projectId;

    }

	public ProjectTplFileTreeNode(String projectId, String typeName){
		this.id = projectId + "_" + typeName;
		this.name= typeName;
		this.leaf = false;
		this.iconCls = null;
		this.type = TYPE_TPL_TYPE;
		this.objectId = projectId;
		this.projectId = projectId;
	}



    private String id;
    private String parentId;
    private String name;
    protected boolean leaf;
	protected String iconCls;
    private String type;
    private String objectId;
    private String projectId;

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

    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
