package net.aicoder.speedcloud.console.business.icode.codegen.controller.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.aicoder.speedcloud.icode.business.project.vo.ComponentVO;
import net.aicoder.speedcloud.icode.business.project.vo.ProductVO;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 */
@Getter
@Setter
@ToString
public class ComponentTplFileTreeNode {

    public static final String TYPE_PRODUCT = "PRODUCT";
    public static final String TYPE_COMPONENT = "COMPONENT";
    public static final String TYPE_TPL_CODE = "TPL_CODE";
	public static final String TYPE_TPL_TYPE = "TPL_TYPE";
	/**
	 * Constructor for ResourceTreeNode.
	 * @param product Project
	 */
    public ComponentTplFileTreeNode(ProductVO product){
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

    public ComponentTplFileTreeNode(ComponentVO component){
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

    public ComponentTplFileTreeNode(String componentId, TplCodeVO tplCode){
        this.id = componentId + "_" + tplCode.getId()+"";
        this.name= StringUtils.isNotEmpty(tplCode.getName())?tplCode.getName():tplCode.getCode();
        this.leaf = true;
        this.iconCls = "page_code";
        this.type = TYPE_TPL_CODE;
        this.objectId = tplCode.getId()+"";
        this.componentId = componentId;

    }

    public ComponentTplFileTreeNode(String componentId, String typeName){
        this.id = componentId + "_" + typeName;
        this.name= typeName;
        this.leaf = false;
        this.iconCls = null;
        this.type = TYPE_TPL_TYPE;
        this.objectId = componentId;
        this.componentId = componentId;
    }


    private String id;
    private String parentId;
    private String name;
    protected boolean leaf;
	protected String iconCls;
    private String type;
    private String code;
    private String objectId;
    private Object object;
    private String componentId;
    private List<ComponentTplFileTreeNode> children;


    public void addChild(ComponentTplFileTreeNode node){
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(node);
        node.setParentId(this.getId());
    }

}
