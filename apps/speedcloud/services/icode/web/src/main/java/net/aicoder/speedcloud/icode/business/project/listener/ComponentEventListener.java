package net.aicoder.speedcloud.icode.business.project.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkang.saas.bootstrap.jms.message.TenantMessage;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import net.aicoder.speedcloud.icode.business.platformmapping.domain.ComponentMapping;
import net.aicoder.speedcloud.icode.business.platformmapping.domain.ProductMapping;
import net.aicoder.speedcloud.icode.business.platformmapping.service.ComponentMappingService;
import net.aicoder.speedcloud.icode.business.platformmapping.service.ProductMappingService;
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 监听组件相关时间
 */
@Service
@Slf4j
public class ComponentEventListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ComponentMappingService componentMappingService;

    @Autowired
    private ProductMappingService productMappingService;

    @JmsListener(destination="Consumer.${spring.application.name}.VirtualTopic.#{T(net.aicoder.speedcloud.business.app.event.AppBaseInfoEventTopic).UPDATE.getTopic()}", containerFactory = "queueListenerFactory")
    public void listenProjectCreate(String json){
        try {
            TenantMessage<AppBaseInfoVO> tenantMessage = objectMapper.readValue(json, new TypeReference<TenantMessage<AppBaseInfoVO>>() {});
            log.info("receive event:speedcloud.project, type:{}", tenantMessage.getEventType());
            AppBaseInfoVO vo = tenantMessage.getMessage();

            //如果没有这个产品, 则不处理组件

            ComponentMapping mapping = componentMappingService.find(vo.getId());

            Component component = componentService.find(vo.getId());
            if(mapping == null){

                component = new Component();
                component.setId(vo.getId());
                component.setTid(tenantMessage.getTid());
                component.setRunnable(true);


                mapping = new ComponentMapping();
                mapping.setId(vo.getId());
                mapping.setComponent(component.getId());
                mapping.setPlatformComponentId(vo.getId());
            }



            //如果改组件在平台所属的产品在这里也有，则优先关联到平台产品中
            ProductMapping productMapping = productMappingService.findByPlatformProductId(vo.getProject());
            if(productMapping != null){
                component.setProduct(productMapping.getProduct());
            }
            component.setName(vo.getName());
            component.setDescription(vo.getDescription());
            componentService.merge(component);

            mapping.setPlatformComponentName(vo.getName());
            componentMappingService.merge(mapping);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
