package com.kingzoo.kingcat.project.icode4.business.system.controller.vo;

import com.kingzoo.kingcat.project.icode4.business.system.domain.*;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import org.apache.commons.lang3.StringUtils;

/**
 */
public class DomainModelTreeNode {

    public static final String TYPE_PRODUCT = "PRODUCT";
    public static final String TYPE_SYSTEM = "SYSTEM";
    public static final String TYPE_MODULE = "MODULE";
    public static final String TYPE_DOMAIN_MODEL = "DOMAIN_MODEL";
	public static final String TYPE_CHILD_MODEL = "DOMAIN_CHILD_MODEL";
	public static final String TYPE_PROPERTY = "PROPERTY";


    public DomainModelTreeNode(Product product){
        this.id = product.getId();
        this.name= StringUtils.isEmpty(product.getProductName())?product.getProductCode():product.getProductName();
        this.leaf = false;
//		this.iconCls = "project_obj";
        this.iconCls = "folder_brick";
        this.type = TYPE_PRODUCT;
        this.objectId = product.getId()+"";
        this.code = product.getProductCode();
        this.object = product;
    }

	public DomainModelTreeNode(System system){
		this.id = system.getId();
		this.name= StringUtils.isEmpty(system.getName())?system.getCode():system.getName();
		this.leaf = false;
//		this.iconCls = "project_obj";
		this.iconCls = "bricks";
        this.type = TYPE_SYSTEM;
        this.objectId = system.getId()+"";
        this.code = system.getCode();
        this.object = system;
	}

    public DomainModelTreeNode(Module module){
        this.id = module.getId()+"";
        this.name= StringUtils.isNotEmpty(module.getName())?module.getName():module.getCode();
        this.leaf = false;
        this.iconCls = "package_obj";
        this.type = TYPE_MODULE;
        this.objectId = module.getId()+"";
        this.parentModuleId = module.getParentModuleId();
	    this.code = module.getCode();
        this.object = module;
    }

    public DomainModelTreeNode(DomainModel domainModel){
        this.id = domainModel.getId()+"";
        this.name= domainModel.getName();
        this.leaf = false;
        this.iconCls = "class_obj";
        this.type = TYPE_DOMAIN_MODEL;
        this.objectId = domainModel.getId()+"";
	    this.systemId = domainModel.getSystemId();
	    this.moduleId = domainModel.getModuleId();
	    this.code = domainModel.getCode();
        this.object = domainModel;
    }

	public DomainModelTreeNode(DomainChildModel domainChildModel){
		this.id = domainChildModel.getId()+"";
		this.name= domainChildModel.getName();
		this.leaf = false;
		this.iconCls = "class_obj";
		this.type = TYPE_CHILD_MODEL;
		this.domainModelId = domainChildModel.getDomainModelId();
		this.objectId = domainChildModel.getId()+"";
		this.code = domainChildModel.getCode();
        this.object = domainChildModel;
	}


	public DomainModelTreeNode(DomainModelProperty property){
		this.id = property.getId()+"";
		this.name= property.getName();
		this.leaf = true;
		this.iconCls = "property_obj";
		this.type = TYPE_PROPERTY;
		this.objectId = property.getId();
		this.domainModelId = property.getDomainModelId();
		this.code = property.getCode();
        this.object = property;
	}

    private String id;
    private String parentId;
    private String name;
    protected boolean leaf;
	protected String iconCls;
    private String type;
    private String objectId;
    private String code;
    private String parentModuleId;
	private String domainModelId;
    private String moduleId;
    private String systemId;
    private Object object;

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

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
}
