package net.aicoder.speedcloud.business.deployscheme.listener;

import net.aicoder.speedcloud.business.app.event.AppBaseInfoEvent;
import net.aicoder.speedcloud.business.app.event.AppBaseInfoEventTopic;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceType;
import net.aicoder.speedcloud.business.deployscheme.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppBaseInfoEventListener {

    @Autowired
    private ResourceTypeService resourceTypeService;

//    @EventListener
    public void appBaseInfoEvent(AppBaseInfoEvent event){

        if(AppBaseInfoEventTopic.DELETE.equals(event.getTopic())){
            return;
        }
        AppBaseInfoVO vo = event.getVo();
        ResourceType resourceType = resourceTypeService.findByCode(vo.getCode());

        if(resourceType == null){
            resourceType = new ResourceType();
            resourceType.setCategory("COMPONENT");
            resourceType.setCode(vo.getCode());
            resourceType.setTid(event.getTid());
            resourceType.fillId();
        }

        resourceType.setName(vo.getName());
        resourceTypeService.merge(resourceType);
    }
}
