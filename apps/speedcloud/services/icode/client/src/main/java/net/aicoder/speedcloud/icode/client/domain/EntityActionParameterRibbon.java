package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterResult;
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
 * 领域对象行为参数客户端
 * @author icode
 */
@Service
public class EntityActionParameterRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterRibbon.class);

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
     * 新增领域对象行为参数
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public EntityActionParameterResult add(EntityActionParameterAddDto addDto) {
        String url = "http://"+host+"/icode/domain/entityactionparameter";
        return restTemplate.postForObject(url, addDto, EntityActionParameterResult.class);
    }
    private EntityActionParameterResult addFail(EntityActionParameterAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除领域对象行为参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public EntityActionParameterResult delete(String id) {
        String url = "http://"+host+"/icode/domain/entityactionparameter/"+id;
        ResponseEntity<EntityActionParameterResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<EntityActionParameterResult>() {});
        return response.getBody();
    }
    private EntityActionParameterResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新领域对象行为参数
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public EntityActionParameterResult update(String id, EntityActionParameterEditDto editDto) {
        String url = "http://"+host+"/icode/domain/entityactionparameter/"+id;
        ResponseEntity<EntityActionParameterResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<EntityActionParameterResult>() {});
        return response.getBody();
    }

    public EntityActionParameterResult updateFail(String id, EntityActionParameterEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询领域对象行为参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public EntityActionParameterResult get(String id) {
        String url = "http://"+host+"/icode/domain/entityactionparameter/"+id;
        return restTemplate.getForObject(url, EntityActionParameterResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private EntityActionParameterResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询领域对象行为参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public EntityActionParameterPageResult list(PageSearchRequest<EntityActionParameterCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/entityactionparameter/list";
        return restTemplate.postForObject(url, pageSearchRequest, EntityActionParameterPageResult.class);
    }
    public EntityActionParameterPageResult listFail(PageSearchRequest<EntityActionParameterCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        EntityActionParameterPageResult result = new EntityActionParameterPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private EntityActionParameterResult errorResult(){
        EntityActionParameterResult result = new EntityActionParameterResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
