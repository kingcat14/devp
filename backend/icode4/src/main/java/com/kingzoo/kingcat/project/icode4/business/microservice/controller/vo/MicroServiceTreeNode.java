package com.kingzoo.kingcat.project.icode4.business.microservice.controller.vo;

import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroService;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.MicroServiceItfc;
import com.kingzoo.kingcat.project.icode4.business.microservice.domain.TransModel;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import com.kingzoo.kingcat.project.icode4.business.system.domain.System;
import org.apache.commons.lang3.StringUtils;

/**
 */
public class MicroServiceTreeNode {

    public static final String TYPE_SYSTEM = "SYSTEM";
    public static final String TYPE_MODULE = "MODULE";
    public static final String TYPE_MICROSERVICE = "MICROSERVICE";
	public static final String TYPE_MICROSERVICE_ITFC = "MICROSERVICEITFC";
	public static final String TYPE_TRANS_MODEL = "TRANS_MODEL";
	/**
	 * Constructor for ResourceTreeNode.
	 * @param project Project
	 */
	public MicroServiceTreeNode(System project){
		this.id = project.getId();
		this.name= StringUtils.isEmpty(project.getName())?project.getCode():project.getName();
		this.leaf = false;
		this.iconCls = "folder_wrench";
        this.type = TYPE_SYSTEM;
        this.objectId = project.getId()+"";
	}

    public MicroServiceTreeNode(Module module){
        this.id = module.getId()+"";
        this.name= StringUtils.isNotEmpty(module.getName())?module.getName():module.getCode();
        this.leaf = false;
        this.iconCls = "package_obj";
        this.type = TYPE_MODULE;
        this.objectId = module.getId()+"";
        this.parentModuleId = module.getParentModuleId();
    }

    public MicroServiceTreeNode(MicroService microService){
        this.id = microService.getId()+"";
        this.name= microService.getName();
        this.leaf = false;
        this.iconCls = "service_obj";
        this.type = TYPE_MICROSERVICE;
        this.objectId = microService.getId()+"";
//	    this.systemId = rootModel.getSystemId();
	    this.moduleId = microService.getModuleId();
    }

	public MicroServiceTreeNode(MicroServiceItfc property){
		this.id = property.getId()+"";
		this.name= property.getName();
		this.leaf = false;
		this.iconCls = "connect";
		this.type = TYPE_MICROSERVICE_ITFC;
		this.objectId = property.getId();
//		this.domainModelId = property.getDomainModelId();
	}

	public MicroServiceTreeNode(TransModel transModel){
		this.id = transModel.getId()+"";
		this.name= transModel.getName();
		this.leaf = true;
		this.iconCls = "class_obj";
		this.type = TYPE_TRANS_MODEL;
		this.objectId = transModel.getId();
//		this.domainModelId = property.getDomainModelId();
	}
	public MicroServiceTreeNode(String itfcId , TransModel transModel){
		this.id = itfcId+"_"+transModel.getId()+"";
		this.name= transModel.getName();
		this.leaf = true;
		this.iconCls = "class_obj";
		this.type = TYPE_TRANS_MODEL;
		this.objectId = transModel.getId();
//		this.domainModelId = property.getDomainModelId();
	}
	public MicroServiceTreeNode(String itfcId , String type, TransModel transModel){
		this.id = itfcId+"_"+type+"_"+transModel.getId()+"";
		this.name= transModel.getName();
		this.leaf = true;
		this.iconCls = "class_obj";
		this.type = TYPE_TRANS_MODEL;
		this.objectId = transModel.getId();
//		this.domainModelId = property.getDomainModelId();
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
