package net.aicoder.speedcloud.icode.business.project.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunkang.saas.bootstrap.jms.message.TenantMessage;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.business.project.vo.ProjectVO;
import net.aicoder.speedcloud.icode.business.platformmapping.domain.ProductMapping;
import net.aicoder.speedcloud.icode.business.platformmapping.service.ProductMappingService;
import net.aicoder.speedcloud.icode.business.project.domain.Product;
import net.aicoder.speedcloud.icode.business.project.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 监听产品相关事件
 */
@Component
@EnableJms
@Slf4j
public class ProductionEventListener {

    private static final String VirtualTopic = "VirtualTopic.";

    @Value("${spring.application.name}")
    private String clientId;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMappingService productMappingService;


    @JmsListener(destination="Consumer.${spring.application.name}.VirtualTopic.#{T(net.aicoder.speedcloud.business.project.event.ProjectEventTopic).UPDATE.getTopic()}", containerFactory = "queueListenerFactory")
    public void listenProjectCreate(String json){
        try {

            TenantMessage<ProjectVO> tenantMessage = objectMapper.readValue(json, new TypeReference<TenantMessage<ProjectVO>> () {});
            log.info("receive event:speedcloud.project, type:{}", tenantMessage.getEventType());

            ProjectVO projectVO = tenantMessage.getMessage();



            ProductMapping mapping = productMappingService.findByPlatformProductId(projectVO.getId());
            if(mapping == null){

                //没有映射关系，就先创建一个产品
                Product product = new Product();
                product.setDescription(projectVO.getDescription());
                product.setProductCode(StringUtils.trimToEmpty(projectVO.getCode()));
                product.setProductName(projectVO.getName());
                product.setTid(tenantMessage.getTid());
                product.setId(projectVO.getId()+"");
                product.setDisabled(false);

                productService.merge(product);

                mapping = new ProductMapping();
                mapping.setId(projectVO.getId());
                mapping.setPlatformProductId(projectVO.getId());
                mapping.setProduct(product.getId());

            }

            mapping.setPlatformProductName(projectVO.getName());
            productMappingService.merge(mapping);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }


    }
//    @JmsListener(destination="speedcloud.project.update", containerFactory = "queueListenerFactory")
//    public void listenProjectUpdate(Object o){
//        log.info(o.toString());
//    }


}
