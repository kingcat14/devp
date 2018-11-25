package net.aicoder.speedcloud.icode.business.project.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkang.saas.bootstrap.jms.message.TenantMessage;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
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

    @JmsListener(destination="Consumer.${spring.application.name}.VirtualTopic.#{T(net.aicoder.speedcloud.business.app.event.AppBaseInfoEventTopic).UPDATE.getTopic()}", containerFactory = "queueListenerFactory")
    public void listenProjectCreate(String json){
        try {
            TenantMessage<AppBaseInfoVO> tenantMessage = objectMapper.readValue(json, new TypeReference<TenantMessage<AppBaseInfoVO>>() {});
            log.info("receive event:speedcloud.project, type:{}", tenantMessage.getEventType());
            AppBaseInfoVO vo = tenantMessage.getMessage();

            Component component = componentService.find(vo.getId());
            if(component == null){
                component = new Component();
                component.setId(vo.getId());
                component.setTid(tenantMessage.getTid());
                component.setProduct(vo.getProject());
            }


            component.setName(vo.getName());
            component.setDescription(vo.getDescription());

            componentService.merge(component);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }


    }



}
