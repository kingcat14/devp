package net.aicoder.speedcloud.icode.client.project;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationCondition;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationEditDto;
import net.aicoder.speedcloud.icode.client.project.result.ComponentLocalLocationPageResult;
import net.aicoder.speedcloud.icode.client.project.result.ComponentLocalLocationResult;
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
 * 组件本地路径客户端
 * @author icode
 */
@Service
public class ComponentLocalLocationRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentLocalLocationRibbon.class);

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
     * 新增组件本地路径
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ComponentLocalLocationResult add(ComponentLocalLocationAddDto addDto) {
        String url = "http://"+host+"/icode/project/componentlocallocation";
        return restTemplate.postForObject(url, addDto, ComponentLocalLocationResult.class);
    }
    private ComponentLocalLocationResult addFail(ComponentLocalLocationAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除组件本地路径
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ComponentLocalLocationResult delete(String id) {
        String url = "http://"+host+"/icode/project/componentlocallocation/"+id;
        ResponseEntity<ComponentLocalLocationResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ComponentLocalLocationResult>() {});
        return response.getBody();
    }
    private ComponentLocalLocationResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新组件本地路径
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ComponentLocalLocationResult update(String id, ComponentLocalLocationEditDto editDto) {
        String url = "http://"+host+"/icode/project/componentlocallocation/"+id;
        ResponseEntity<ComponentLocalLocationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ComponentLocalLocationResult>() {});
        return response.getBody();
    }

    public ComponentLocalLocationResult updateFail(String id, ComponentLocalLocationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询组件本地路径
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ComponentLocalLocationResult get(String id) {
        String url = "http://"+host+"/icode/project/componentlocallocation/"+id;
        return restTemplate.getForObject(url, ComponentLocalLocationResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ComponentLocalLocationResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询组件本地路径列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ComponentLocalLocationPageResult list(PageSearchRequest<ComponentLocalLocationCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/project/componentlocallocation/list";
        return restTemplate.postForObject(url, pageSearchRequest, ComponentLocalLocationPageResult.class);
    }
    public ComponentLocalLocationPageResult listFail(PageSearchRequest<ComponentLocalLocationCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ComponentLocalLocationPageResult result = new ComponentLocalLocationPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ComponentLocalLocationResult errorResult(){
        ComponentLocalLocationResult result = new ComponentLocalLocationResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
