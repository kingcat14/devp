package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionResult;
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
 * 领域对象行为客户端
 * @author icode
 */
@Service
public class EntityActionRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionRibbon.class);

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
     * 新增领域对象行为
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public EntityActionResult add(EntityActionAddDto addDto) {
        String url = "http://"+host+"/icode/domain/entityaction";
        return restTemplate.postForObject(url, addDto, EntityActionResult.class);
    }
    private EntityActionResult addFail(EntityActionAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除领域对象行为
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public EntityActionResult delete(String id) {
        String url = "http://"+host+"/icode/domain/entityaction/"+id;
        ResponseEntity<EntityActionResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<EntityActionResult>() {});
        return response.getBody();
    }
    private EntityActionResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新领域对象行为
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public EntityActionResult update(String id, EntityActionEditDto editDto) {
        String url = "http://"+host+"/icode/domain/entityaction/"+id;
        ResponseEntity<EntityActionResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<EntityActionResult>() {});
        return response.getBody();
    }

    public EntityActionResult updateFail(String id, EntityActionEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询领域对象行为
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public EntityActionResult get(String id) {
        String url = "http://"+host+"/icode/domain/entityaction/"+id;
        return restTemplate.getForObject(url, EntityActionResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private EntityActionResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询领域对象行为列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public EntityActionPageResult list(PageSearchRequest<EntityActionCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/entityaction/list";
        return restTemplate.postForObject(url, pageSearchRequest, EntityActionPageResult.class);
    }
    public EntityActionPageResult listFail(PageSearchRequest<EntityActionCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        EntityActionPageResult result = new EntityActionPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private EntityActionResult errorResult(){
        EntityActionResult result = new EntityActionResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
