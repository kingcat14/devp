package net.aicoder.speedcloud.icode.client.project;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentEditDto;
import net.aicoder.speedcloud.icode.client.project.result.ComponentPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentResult;
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
 * 系统组件客户端
 * @author icode
 */
@Service
public class ComponentRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentRibbon.class);

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
     * 新增系统组件
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ComponentResult add(ComponentAddDto addDto) {
        String url = "http://"+host+"/icode/project/component";
        return restTemplate.postForObject(url, addDto, ComponentResult.class);
    }
    private ComponentResult addFail(ComponentAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除系统组件
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ComponentResult delete(String id) {
        String url = "http://"+host+"/icode/project/component/"+id;
        ResponseEntity<ComponentResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ComponentResult>() {});
        return response.getBody();
    }
    private ComponentResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新系统组件
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ComponentResult update(String id, ComponentEditDto editDto) {
        String url = "http://"+host+"/icode/project/component/"+id;
        ResponseEntity<ComponentResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ComponentResult>() {});
        return response.getBody();
    }

    public ComponentResult updateFail(String id, ComponentEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询系统组件
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ComponentResult get(String id) {
        String url = "http://"+host+"/icode/project/component/"+id;
        return restTemplate.getForObject(url, ComponentResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ComponentResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询系统组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ComponentPageResult list(PageSearchRequest<ComponentCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/project/component/list";
        return restTemplate.postForObject(url, pageSearchRequest, ComponentPageResult.class);
    }
    public ComponentPageResult listFail(PageSearchRequest<ComponentCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ComponentPageResult result = new ComponentPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ComponentResult errorResult(){
        ComponentResult result = new ComponentResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
