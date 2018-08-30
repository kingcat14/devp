package com.kingzoo.kingcat.project.icode4.business.codegen.controller.vo;

import com.kingzoo.kingcat.project.icode4.business.system.domain.DomainModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Product;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import com.kingzoo.kingcat.project.icode4.business.view.domain.ViewModel;
import org.apache.commons.lang3.StringUtils;

/**
 */
public class ModelTreeNode {

    public static final String TYPE_PRODUCT = "PRODUCT";
    public static final String TYPE_SYSTEM = "SYSTEM";
    public static final String TYPE_MODULE = "MODULE";
    public static final String TYPE_DOMAIN_MODEL = "DOMAIN_MODEL";
	public static final String TYPE_VIEW_MODEL = "VIEW_MODEL";

	public static final String TYPE_CHILDMODEL = "childModel";
	public static final String TYPE_PROPERTY = "PROPERTY";

    public ModelTreeNode(Product product){
        this.id = product.getId();
        this.name= StringUtils.isEmpty(product.getProductName())?product.getProductCode():product.getProductName();
        this.leaf = false;
//		this.iconCls = "project_obj";
        this.iconCls = "folder_brick";
        this.type = TYPE_PRODUCT;
        this.objectId = product.getId()+"";
        this.code = product.getProductCode();
    }
	/**
	 * Constructor for ResourceTreeNode.
	 * @param project Project
	 */
	public ModelTreeNode(System project){
		this.id = project.getId();
		this.name= StringUtils.isEmpty(project.getName())?project.getCode():project.getName();
		this.leaf = false;
//		this.iconCls = "project_obj";
		this.iconCls = "bricks";
        this.type = TYPE_SYSTEM;
        this.objectId = project.getId()+"";
        this.code = project.getCode();
	}

    public ModelTreeNode(Module module){
        this.id = module.getId()+"";
        this.name= StringUtils.isNotEmpty(module.getName())?module.getName():module.getCode();
        this.leaf = false;
        this.iconCls = "package_obj";
        this.type = TYPE_MODULE;
        this.objectId = module.getId()+"";
        this.parentModuleId = module.getParentModuleId();
	    this.code = module.getCode();
    }

    public ModelTreeNode(DomainModel domainModel){
        this.id = domainModel.getId()+"";
        this.name= "(EO)"+domainModel.getName();
        this.leaf = false;
        this.iconCls = "class_obj";
        this.type = TYPE_DOMAIN_MODEL;
        this.objectId = domainModel.getId()+"";
	    this.systemId = domainModel.getSystemId();
	    this.moduleId = domainModel.getModuleId();
	    this.code = domainModel.getCode();
    }


	public ModelTreeNode(ViewModel domainModel){
		this.id = domainModel.getId()+"";
		this.name= "(VO)"+domainModel.getName();
		this.leaf = false;
		this.iconCls = "page_white";
		this.type = TYPE_VIEW_MODEL;
		this.objectId = domainModel.getId()+"";
		this.moduleId = domainModel.getModuleId();
		this.code = domainModel.getCode();
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


}
