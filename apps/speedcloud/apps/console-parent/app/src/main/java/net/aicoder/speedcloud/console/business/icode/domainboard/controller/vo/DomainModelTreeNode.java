package net.aicoder.speedcloud.console.business.icode.domainboard.controller.vo;

import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 领域模型树
 */
@Getter @Setter
public class DomainModelTreeNode {

    public static final String TYPE_PRODUCT = "PRODUCT";
    public static final String TYPE_COMPONENT = "COMPONENT";
    public static final String TYPE_MODULE = "DOMAIN";
    public static final String TYPE_ENTITY = "ENTITY";
	public static final String TYPE_ENTITY_ACTION = "ENTITY_ACTION";
    public static final String TYPE_ENTITY_ACTION_PARAMETER = "PARAMETER";


    public DomainModelTreeNode(ProductVO product){
        this.id = UUID.randomUUID().toString();
        this.id = product.getId();
        this.name= StringUtils.isEmpty(product.getProductName())?product.getProductCode():product.getProductName();
        this.leaf = false;
//		this.iconCls = "project_obj";<i class="fab fa-product-hunt"></i>
        this.iconCls = "folder_brick";
//        this.iconCls = "fab fa-product-hunt";
        this.type = TYPE_PRODUCT;
        this.objectId = product.getId()+"";
        this.code = product.getProductCode();
        this.object = product;
    }

	public DomainModelTreeNode(ComponentVO component){
        this.id = UUID.randomUUID().toString();
        this.id = component.getId();
		this.name= StringUtils.isEmpty(component.getName())?component.getCode():component.getName();
		this.leaf = false;
//		this.iconCls = "project_obj";
		this.iconCls = "bricks";
        this.type = TYPE_COMPONENT;
        this.objectId = component.getId()+"";
        this.code = component.getCode();
        this.object = component;
	}

    public DomainModelTreeNode(DomainVO domain){
        this.id = UUID.randomUUID().toString();
        this.id = domain.getId();
        this.name= StringUtils.isNotEmpty(domain.getName())?domain.getName():domain.getCode();
        this.leaf = false;
        this.iconCls = "package_obj";
        this.type = TYPE_MODULE;
        this.objectId = domain.getId();
        this.parentModuleId = domain.getParent();
	    this.code = domain.getCode();
        this.object = domain;
    }

    public DomainModelTreeNode(EntityVO entity){
        this.id = UUID.randomUUID().toString();
        this.id = entity.getId();
        this.name= entity.getName();
        this.leaf = false;
        this.iconCls = "class_obj";
        this.type = TYPE_ENTITY;
        this.objectId = entity.getId()+"";
	    this.code = entity.getCode();
        this.object = entity;
    }

	public DomainModelTreeNode(EntityActionVO action){
        this.id = UUID.randomUUID().toString();
        this.id = action.getId();
		this.name= action.getName();
		this.leaf = false;
		this.iconCls = "service_obj";
		this.type = TYPE_ENTITY_ACTION;
		this.objectId = action.getId()+"";
		this.code = action.getCode();
        this.object = action;
	}

    public DomainModelTreeNode(EntityActionParameterVO parameter, Boolean request){
        this.id = UUID.randomUUID().toString();
        this.id = parameter.getId();
        this.name= request?"(IN)":"(OUT)"+parameter.getName();
        this.leaf = true;
        this.iconCls = "property_obj";
        this.type = TYPE_ENTITY_ACTION_PARAMETER;
        this.objectId = parameter.getId()+"";
        this.code = parameter.getCode();
        this.object = parameter;
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


}
