package net.aicoder.speedcloud.icode.client.project;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationEditDto;
import net.aicoder.speedcloud.icode.client.project.result.ComponentDomainRelationPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentDomainRelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * 组件-领域-关系客户端
 * @author icode
 */
@Service
public class ComponentDomainRelationRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentDomainRelationRibbon.class);

    private String host = "SPEEDCLOUD-ICODE-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增组件-领域-关系
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ComponentDomainRelationResult add(ComponentDomainRelationAddDto addDto) {
        String url = "http://"+host+"/icode/project/componentdomainrelation";
        return restTemplate.postForObject(url, addDto, ComponentDomainRelationResult.class);
    }
    private ComponentDomainRelationResult addFail(ComponentDomainRelationAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除组件-领域-关系
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ComponentDomainRelationResult delete(String id) {
        String url = "http://"+host+"/icode/project/componentdomainrelation/"+id;
        ResponseEntity<ComponentDomainRelationResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ComponentDomainRelationResult>() {});
        return response.getBody();
    }
    private ComponentDomainRelationResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新组件-领域-关系
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ComponentDomainRelationResult update(String id, ComponentDomainRelationEditDto editDto) {
        String url = "http://"+host+"/icode/project/componentdomainrelation/"+id;
        ResponseEntity<ComponentDomainRelationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ComponentDomainRelationResult>() {});
        return response.getBody();
    }

    public ComponentDomainRelationResult updateFail(String id, ComponentDomainRelationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询组件-领域-关系
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ComponentDomainRelationResult get(String id) {
        String url = "http://"+host+"/icode/project/componentdomainrelation/"+id;
        return restTemplate.getForObject(url, ComponentDomainRelationResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ComponentDomainRelationResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询组件-领域-关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ComponentDomainRelationPageResult list(PageSearchRequest<ComponentDomainRelationCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/project/componentdomainrelation/list";
        return restTemplate.postForObject(url, pageSearchRequest, ComponentDomainRelationPageResult.class);
    }
    public ComponentDomainRelationPageResult listFail(PageSearchRequest<ComponentDomainRelationCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ComponentDomainRelationPageResult result = new ComponentDomainRelationPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ComponentDomainRelationResult errorResult(){
        ComponentDomainRelationResult result = new ComponentDomainRelationResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
