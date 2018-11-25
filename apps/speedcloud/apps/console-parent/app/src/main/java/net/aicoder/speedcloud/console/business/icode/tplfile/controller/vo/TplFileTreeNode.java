package net.aicoder.speedcloud.console.business.icode.tplfile.controller.vo;


import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplCodeVO;
import net.aicoder.speedcloud.icode.business.tplfile.vo.TplSetVO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Getter @Setter
public class TplFileTreeNode {

    public static final String TYPE_TPL_SET = "TPL_SET";
    public static final String TYPE_TPL_CODE = "TPL_CODE";
	public static final String TYPE_TPL_TYPE = "TPL_TYPE";
	/**
	 * Constructor for ResourceTreeNode.
	 * @param project Project
	 */
	public TplFileTreeNode(TplSetVO project){
		this.id = project.getId();
		this.name= StringUtils.isEmpty(project.getName())?project.getCode():project.getName();
		this.leaf = false;
		this.iconCls = null;
        this.type = TYPE_TPL_SET;
        this.tplSetId = project.getId();
        this.objectId = project.getId()+"";
	}

    public TplFileTreeNode(TplCodeVO tplCode){
        this.id = tplCode.getId();
        this.name= StringUtils.isNotEmpty(tplCode.getName())?tplCode.getName():tplCode.getCode();
        this.leaf = true;
        this.iconCls = getIconCls(tplCode.getFileName());
        this.type = TYPE_TPL_CODE;
        this.objectId = tplCode.getId();
        this.object = tplCode;
        this.tplSetId = tplCode.getTplSet();
    }


    public TplFileTreeNode(String tplSetId, String filePath){
        this.id = tplSetId+"_"+filePath.replaceAll("/", "-");
        this.name= filePath;
        this.leaf = false;
        this.iconCls = null;
        this.type = TYPE_TPL_TYPE;
        this.objectId = filePath;
        this.tplSetId = tplSetId;
    }


    private String id;
    private String parentId;
    private String name;
    protected boolean leaf;
	protected String iconCls;
    private String type;
    private String objectId;
    private Object object;
    private String tplSetId;
    private List<TplFileTreeNode> children;


    public void addChild(TplFileTreeNode node){
	    if(this.children == null){
	        this.children = new ArrayList<>();
        }
        this.children.add(node);
	    node.setParentId(this.getId());
    }

    private String getIconCls(String name){

	    String result = "page_code";
	    if(name.endsWith("yml")){
            result = "fas fa-leaf green";
        }
        if(name.endsWith("java")){
//            result = "class_obj";
            result = "far fa-file-code red";

        }
        if(name.endsWith("js")){
            result = "fab fa-node-js orange";
        }
        if(name.endsWith("xml")){
            result = "far fa-file-code xml";
        }
        if(name.endsWith("pom.xml")){
            result = "fab fa-medium-m blue";
        }
        return  result;

    }
}
