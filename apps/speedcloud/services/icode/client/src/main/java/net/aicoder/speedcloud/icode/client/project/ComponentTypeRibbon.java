package net.aicoder.speedcloud.icode.client.project;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeEditDto;
import net.aicoder.speedcloud.icode.client.project.result.ComponentTypePageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentTypeResult;
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
 * 组件类型客户端
 * @author icode
 */
@Service
public class ComponentTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentTypeRibbon.class);

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
     * 新增组件类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ComponentTypeResult add(ComponentTypeAddDto addDto) {
        String url = "http://"+host+"/icode/project/componenttype";
        return restTemplate.postForObject(url, addDto, ComponentTypeResult.class);
    }
    private ComponentTypeResult addFail(ComponentTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除组件类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ComponentTypeResult delete(String id) {
        String url = "http://"+host+"/icode/project/componenttype/"+id;
        ResponseEntity<ComponentTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ComponentTypeResult>() {});
        return response.getBody();
    }
    private ComponentTypeResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新组件类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ComponentTypeResult update(String id, ComponentTypeEditDto editDto) {
        String url = "http://"+host+"/icode/project/componenttype/"+id;
        ResponseEntity<ComponentTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ComponentTypeResult>() {});
        return response.getBody();
    }

    public ComponentTypeResult updateFail(String id, ComponentTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询组件类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ComponentTypeResult get(String id) {
        String url = "http://"+host+"/icode/project/componenttype/"+id;
        return restTemplate.getForObject(url, ComponentTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ComponentTypeResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询组件类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ComponentTypePageResult list(PageSearchRequest<ComponentTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/project/componenttype/list";
        return restTemplate.postForObject(url, pageSearchRequest, ComponentTypePageResult.class);
    }
    public ComponentTypePageResult listFail(PageSearchRequest<ComponentTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ComponentTypePageResult result = new ComponentTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ComponentTypeResult errorResult(){
        ComponentTypeResult result = new ComponentTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
