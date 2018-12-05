package net.aicoder.speedcloud.icode.business.domain.controller.convert;

import net.aicoder.speedcloud.icode.business.domain.domain.*;
import net.aicoder.speedcloud.icode.business.domain.service.*;
import net.aicoder.speedcloud.icode.business.domain.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityVOConvert {

    @Autowired
    private EntityService entityService;

    @Autowired
    private EntityPropertyService entityPropertyService;

    @Autowired
    private EntityViewPropertyService entityViewPropertyService;

    @Autowired
    private EntityActionService entityActionService;

    @Autowired
    private EntityActionParameterService entityActionParameterService;

    @Autowired
    private EntityActionParameterPropertyService entityActionParameterPropertyService;

    @Autowired
    private DomainService domainService;

    /**
     * 初始化详细的信息，包含所属领域、属性、行为等
     * @param entity
     */
    public EntityVO initDetail(Entity entity){

        EntityVO entityVO = new EntityVO();
        BeanUtils.copyProperties(entity, entityVO);
        //初始化其他对象
        initDomain(entityVO);

        //初始化属性（属性和展现属性）
        initPropertyList(entityVO);

        //初始化行为
        initEntityActionList(entityVO);

        return entityVO;
    }

    private void initDomain(EntityVO entityVO){

        Domain domain = domainService.find(entityVO.getDomain());
        if(domain == null){
            return;
        }
        DomainVO domainVO = new DomainVO();
        BeanUtils.copyProperties(domain, domainVO);

        //设置包的全路径
        String domainCodePath = domainService.getCodePath(entityVO.getDomain());
        domainVO.setCodePath(domainCodePath);
        domainVO.setTopCode(domainService.findTopDomain(entityVO.getDomain()).getCode());

        entityVO.setDomainVO(domainVO);

    }

    private void initPropertyList(EntityVO entityVO){
        //初始化全部属性
        List<EntityPropertyVO> entityPropertyVOList = new ArrayList<>();
        List<EntityProperty> entityPropertyList = entityPropertyService.findByEntity(entityVO.getId());

        for(EntityProperty entityProperty : entityPropertyList){

            EntityPropertyVO entityPropertyVO = initProperty(entityProperty);

            entityPropertyVOList.add(entityPropertyVO);
        }
        entityVO.setPropertyList(entityPropertyVOList);
    }

    private EntityPropertyVO initProperty(EntityProperty entityProperty){
        EntityPropertyVO vo = new EntityPropertyVO();
        BeanUtils.copyProperties(entityProperty, vo);

        //初始化应用属性
        initReferenceProperty(vo);



        //初始化展现属性
        EntityViewPropertyVO viewPropertyVO = getViewProperty(entityProperty);
        vo.setViewProperty(viewPropertyVO);

        return vo;
    }

    private void initReferenceProperty(EntityPropertyVO vo){

        //引用属性为空, 则啥也不处理
        String refPropertyId = vo.getRelatedEntityPropertyId();
        if(StringUtils.isEmpty(refPropertyId) || StringUtils.equals("-1", refPropertyId)){
            vo.setRelatedEntityPropertyId(null);
            vo.setRelatedEntityId(null);
            return;
        }

        //处理引用属性
        EntityProperty refProperty = entityPropertyService.find(refPropertyId);
        EntityPropertyVO refPropertyVO = new EntityPropertyVO();
        BeanUtils.copyProperties(refProperty, refPropertyVO);
        vo.setRelatedEntityProperty(refPropertyVO);

        //得到引用对象
        String relatedEntityId = vo.getRelatedEntityId();
        Entity relatedEntity = entityService.find(relatedEntityId);

        EntityVO relatedEntityVO = new EntityVO();
        BeanUtils.copyProperties(relatedEntity, relatedEntityVO);

        //初始化引用对象的领域属性
        initDomain(relatedEntityVO);

        vo.setRelatedEntity(relatedEntityVO);

        //得到引用对象的主键
        EntityProperty referencePrimaryKey = entityPropertyService.findPrimaryKeyFormEntity(relatedEntityId);
        vo.setRelatedEntityPrimaryKeyType(referencePrimaryKey.getType());

    }

    private EntityViewPropertyVO getViewProperty(EntityProperty entityProperty){

        EntityViewProperty viewProperty = entityViewPropertyService.find(entityProperty.getId());

        if(viewProperty == null){
            viewProperty = entityViewPropertyService.create(entityProperty);
        }

        EntityViewPropertyVO vo = new EntityViewPropertyVO();
        BeanUtils.copyProperties(viewProperty, vo);
        return vo;
    }

    private void initEntityActionList(EntityVO entityVO){
        List<EntityAction> entityActionList = entityActionService.findByEntity(entityVO.getId());

        List<EntityActionVO> entityActionVOList = new ArrayList<>();
        for(EntityAction entityAction : entityActionList){
            EntityActionVO entityActionVO = initEntityAction(entityAction);
            entityActionVOList.add(entityActionVO);
        }

        entityVO.setActionList(entityActionVOList);

    }

    private EntityActionVO initEntityAction(EntityAction entityAction){

        EntityActionVO entityActionVO = new EntityActionVO();
        BeanUtils.copyProperties(entityAction, entityActionVO);

        if(StringUtils.isNotEmpty(entityAction.getRequest())){
            EntityActionParameter request = entityActionParameterService.find(entityAction.getRequest());
            EntityActionParameterVO requestVO = initParameterVO(request);
            entityActionVO.setRequestParameter(requestVO);
        }

        if(StringUtils.isNotEmpty(entityAction.getResponse())){
            EntityActionParameter response = entityActionParameterService.find(entityAction.getResponse());
            EntityActionParameterVO responseVO = initParameterVO(response);
            entityActionVO.setResponseParameter(responseVO);
        }

        return entityActionVO;

    }

    private EntityActionParameterVO initParameterVO(EntityActionParameter parameter){

        EntityActionParameterVO parameterVO = new EntityActionParameterVO();

        BeanUtils.copyProperties(parameter, parameterVO);

        List<EntityActionParameterProperty> parameterPropertyList = entityActionParameterPropertyService.findByParameter(parameter.getId());

        List<EntityActionParameterPropertyVO> parameterPropertyVOList = new ArrayList<>();

        for(EntityActionParameterProperty parameterProperty : parameterPropertyList){

            EntityActionParameterPropertyVO parameterPropertyVO = new EntityActionParameterPropertyVO();
            BeanUtils.copyProperties(parameterProperty, parameterPropertyVO);

            parameterPropertyVOList.add(parameterPropertyVO);

        }
        parameterVO.setPropertyList(parameterPropertyVOList);

        return parameterVO;

    }


}
