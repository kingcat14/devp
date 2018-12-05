package net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.entity;


import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.ModelConfig;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 领域模型的模型配置
 * Created by gonghongrui on 14/11/4.
 */
@Setter
@Getter
public class EntityConfig extends ModelConfig {

	private boolean rootModel = true;

    private List<EntityConfig> childModelList = new ArrayList<>();

    private EntityConfig parentModel;

    private String tableName;

    private List<EntityActionVO> actionList;

    @Override
    public String getModelType() {
        return "EO";
    }

    private EntityPropertyConfig primaryKey;

    /**属性引用到的其他对象(重复的对象只存在一份)*/
    private List<EntityConfig> reference = new ArrayList<>();

    /**属性(引用组合并成一个属性)*/
    private List<EntityPropertyConfig> attribute = new ArrayList<>();

    private List<EntityPropertyConfig> referenceProperty = new ArrayList<>();

    private List<EntityPropertyConfig> simpleConditionList = new ArrayList<>();
    private List<EntityPropertyConfig> visiblePropertyList = new ArrayList<>();
    private List<EntityPropertyConfig> addablePropertyList = new ArrayList<>();
    private List<EntityPropertyConfig> editablePropertyList = new ArrayList<>();
    private List<EntityPropertyConfig> gridPropertyList = new ArrayList<>();
    private List<EntityPropertyConfig> searchPropertyList = new ArrayList<>();

    private List<EntityPropertyConfig> requiredPropertyList = new ArrayList<>();
    private List<EntityPropertyConfig> persistPropertyList = new ArrayList<>();


    public void addReference(EntityConfig newReference){

        /*判断一下,重复的就不再添加了*/
        for(EntityConfig config : reference){
            if(config.getId().equals(newReference.getId())){
                return;
            }
        }
        reference.add(newReference);
    }

    public void setAttribute(List<EntityPropertyConfig> attributeList) {

        for(EntityPropertyConfig propertyConfig : attributeList ) {

            if (propertyConfig.getPrimaryKey()) {
                this.primaryKey = propertyConfig;
                continue;
            }

            if (propertyConfig.getVisible()) {
                visiblePropertyList.add(propertyConfig);
            }

            if((propertyConfig.getViewConfig() != null && (propertyConfig.getViewConfig().getSearchCondition()
            ))){
                searchPropertyList.add(propertyConfig);
            }

            if((propertyConfig.getViewConfig() != null && (propertyConfig.getViewConfig().getAddable()
                    ||propertyConfig.getViewConfig().getAddRequired()
                    ||propertyConfig.getViewConfig().getAddViewable()
            ))){
                addablePropertyList.add(propertyConfig);
            }

            if((propertyConfig.getViewConfig() != null && (propertyConfig.getViewConfig().getEditable()
                    ||propertyConfig.getViewConfig().getEditRequired()
                    ||propertyConfig.getViewConfig().getEditViewable()
            ))||propertyConfig.getEditable()){
                editablePropertyList.add(propertyConfig);
            }

            if(propertyConfig.getViewConfig() != null && propertyConfig.getViewConfig().getGirdColumn()) {
                gridPropertyList.add(propertyConfig);
            }
            if(propertyConfig.getViewConfig() != null && propertyConfig.getViewConfig().getSimpleSearchCondition()) {
                simpleConditionList.add(propertyConfig);
            }

            if (propertyConfig.getRequired()){
                requiredPropertyList.add(propertyConfig);
            }

            if (propertyConfig.getPersist()){
                persistPropertyList.add(propertyConfig);
            }

            this.attribute.add(propertyConfig);

            //如果属性是引用, 则将属性加入引用
            if(propertyConfig.isReferenceFlag()){
                this.addReference(propertyConfig.getReference());
                this.referenceProperty.add(propertyConfig);
            }

        }


    }

    public List<EntityPropertyConfig> getDbColumn() {
        return visiblePropertyList;
    }

    public List<EntityPropertyConfig> getTextAttribute(){
        List<EntityPropertyConfig> sc = new ArrayList<>();
        for (EntityPropertyConfig attr:attribute){
            if("Text".equalsIgnoreCase(attr.getType())){
                sc.add(attr);
            }
        }
        return sc;
    }
    public boolean hashReferenceProperty(){
        for (EntityPropertyConfig attr:attribute){
            if(attr.isReferenceFlag()){
                return true;
            }
        }
        return false;
    }

    public boolean hasChildModel(){
        return CollectionUtils.isNotEmpty(this.childModelList);
    }



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
