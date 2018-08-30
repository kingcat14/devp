package com.kingzoo.kingcat.project.icode4.business.view.controller.vo;

import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModelProperty;
import org.apache.commons.lang3.StringUtils;

/**
 */
public class ViewModelTreeNode {

    public static final String TYPE_SYSTEM = "SYSTEM";
    public static final String TYPE_MODULE = "MODULE";
    public static final String TYPE_DOMAIN_MODEL = "VIEW_MODEL";
	public static final String TYPE_CHILDMODEL = "childModel";
	public static final String TYPE_PROPERTY = "PROPERTY";
	/**
	 * Constructor for ResourceTreeNode.
	 * @param project Project
	 */
	public ViewModelTreeNode(System project){
		this.id = project.getId();
		this.name= StringUtils.isEmpty(project.getName())?project.getCode():project.getName();
		this.leaf = false;
		this.iconCls = "folder_page";
        this.type = TYPE_SYSTEM;
        this.objectId = project.getId()+"";
	}

    public ViewModelTreeNode(Module domain){
        this.id = domain.getId()+"";
        this.name= StringUtils.isNotEmpty(domain.getName())?domain.getName():domain.getCode();
        this.leaf = false;
        this.iconCls = "package_obj";
        this.type = TYPE_MODULE;
        this.objectId = domain.getId()+"";
        this.parentModuleId = domain.getParentModuleId();
    }

    public ViewModelTreeNode(ViewModel rootModel){
        this.id = rootModel.getId()+"";
        this.name= rootModel.getName();
        this.leaf = false;
        this.iconCls = "class_obj";
        this.type = TYPE_DOMAIN_MODEL;
        this.objectId = rootModel.getId()+"";
//	    this.systemId = rootModel.getSystemId();
	    this.moduleId = rootModel.getModuleId();
    }

	public ViewModelTreeNode(ViewModelProperty property){
		this.id = property.getId()+"";
		this.name= property.getName();
		this.leaf = true;
		this.iconCls = "property_obj";
		this.type = TYPE_PROPERTY;
		this.objectId = property.getId();
		this.domainModelId = property.getDomainModelId();
	}

    private String id;
    private String parentId;
    private String name;
    protected boolean leaf;
	protected String iconCls;
    private String type;
    private String objectId;
    private String parentModuleId;
    private String moduleId;
    private String systemId;
    private String domainModelId;

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
}
