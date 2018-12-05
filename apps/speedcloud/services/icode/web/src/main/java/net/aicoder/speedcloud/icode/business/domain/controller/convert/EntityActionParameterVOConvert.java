package net.aicoder.speedcloud.icode.business.domain.controller.convert;

import net.aicoder.speedcloud.icode.business.domain.domain.*;
import net.aicoder.speedcloud.icode.business.domain.service.DomainService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityActionParameterPropertyService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityActionService;
import net.aicoder.speedcloud.icode.business.domain.service.EntityService;
import net.aicoder.speedcloud.icode.business.domain.vo.DomainVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterPropertyVO;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityActionParameterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityActionParameterVOConvert {

    @Autowired
    private EntityService entityService;


    @Autowired
    private EntityActionService entityActionService;

    @Autowired
    private EntityActionParameterPropertyService entityActionParameterPropertyService;

    @Autowired
    private DomainService domainService;

    /**
     * 初始化详细的信息，包含所属领域、属性、行为等
     * @param parameter
     */
    public EntityActionParameterVO initDetail(EntityActionParameter parameter){

        EntityActionParameterVO parameterVO = new EntityActionParameterVO();

        BeanUtils.copyProperties(parameter, parameterVO);

        initParameterProperty(parameterVO);
        initDomain(parameterVO);

        return parameterVO;

    }

    private void initParameterProperty(EntityActionParameterVO parameterVO){

        List<EntityActionParameterProperty> parameterPropertyList = entityActionParameterPropertyService.findByParameter(parameterVO.getId());

        List<EntityActionParameterPropertyVO> parameterPropertyVOList = new ArrayList<>();

        for(EntityActionParameterProperty parameterProperty : parameterPropertyList){

            EntityActionParameterPropertyVO parameterPropertyVO = new EntityActionParameterPropertyVO();
            BeanUtils.copyProperties(parameterProperty, parameterPropertyVO);

            parameterPropertyVOList.add(parameterPropertyVO);

        }
        parameterVO.setPropertyList(parameterPropertyVOList);
    }

    private void initDomain(EntityActionParameterVO parameterVO){

        EntityAction entityAction = entityActionService.find(parameterVO.getEntityAction());
        Entity entity = entityService.find(entityAction.getEntity());

        Domain domain = domainService.find(entity.getDomain());
        if(domain == null){
            return;
        }
        DomainVO domainVO = new DomainVO();
        BeanUtils.copyProperties(domain, domainVO);

        //设置包的全路径
        domainVO.setCodePath(domainService.getCodePath(entity.getDomain()));
        domainVO.setTopCode(domainService.findTopDomain(entity.getDomain()).getCode());

        parameterVO.setDomainVO(domainVO);

    }
}
