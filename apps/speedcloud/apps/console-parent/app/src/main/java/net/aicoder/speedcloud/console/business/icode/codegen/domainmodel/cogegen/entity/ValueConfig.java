package net.aicoder.speedcloud.console.business.icode.codegen.domainmodel.cogegen.entity;


import lombok.Getter;
import lombok.Setter;
import net.aicoder.speedcloud.console.business.icode.codegen.domain.ModelConfig;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterPropertyVO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 值对象的模型配置
 * Created by gonghongrui on 14/11/4.
 */
@Setter
@Getter
public class ValueConfig extends ModelConfig {

	private boolean rootModel = true;

    private List<ValueConfig> childModelList = new ArrayList<>();
    
    private String description;
    
    @Override
    public String getModelType() {
        return "VO";
    }

    private EntityActionParameterPropertyVO primaryKey;

    /**属性引用到的其他对象(重复的对象只存在一份)*/
    private List<ValueConfig> reference = new ArrayList<>();

    /**属性(引用组合并成一个属性)*/
    private List<EntityActionParameterPropertyVO> attribute = new ArrayList<>();

    private List<EntityActionParameterPropertyVO> referenceProperty = new ArrayList<>();
    
    private List<EntityActionParameterPropertyVO> simpleConditionList = new ArrayList<>();
    private List<EntityActionParameterPropertyVO> visiblePropertyList = new ArrayList<>();
    private List<EntityActionParameterPropertyVO> addablePropertyList = new ArrayList<>();
    private List<EntityActionParameterPropertyVO> editablePropertyList = new ArrayList<>();
    private List<EntityActionParameterPropertyVO> gridPropertyList = new ArrayList<>();
    private List<EntityActionParameterPropertyVO> searchPropertyList = new ArrayList<>();

    private List<EntityActionParameterPropertyVO> requiredPropertyList = new ArrayList<>();
    private List<EntityActionParameterPropertyVO> persistPropertyList = new ArrayList<>();

    
    public void addReference(ValueConfig newReference){

        /*判断一下,重复的就不再添加了*/
        for(ValueConfig config : reference){
            if(config.getId().equals(newReference.getId())){
                return;
            }
        }
        reference.add(newReference);
    }
    
    public void setAttribute(List<EntityActionParameterPropertyVO> attributeList) {

        for(EntityActionParameterPropertyVO propertyConfig : attributeList ) {
            this.attribute.add(propertyConfig);
        }
    }

    public List<EntityActionParameterPropertyVO> getTextAttribute(){
        List<EntityActionParameterPropertyVO> sc = new ArrayList<>();
        for (EntityActionParameterPropertyVO attr : attribute){
            if("Text".equalsIgnoreCase(attr.getType())){
                sc.add(attr);
            }
        }
        return sc;
    }



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
