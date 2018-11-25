package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.EntityPropertyPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityPropertyResult;
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
 * 领域对象属性客户端
 * @author icode
 */
@Service
public class EntityPropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityPropertyRibbon.class);

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
     * 新增领域对象属性
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public EntityPropertyResult add(EntityPropertyAddDto addDto) {
        String url = "http://"+host+"/icode/domain/entityproperty";
        return restTemplate.postForObject(url, addDto, EntityPropertyResult.class);
    }
    private EntityPropertyResult addFail(EntityPropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除领域对象属性
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public EntityPropertyResult delete(String id) {
        String url = "http://"+host+"/icode/domain/entityproperty/"+id;
        ResponseEntity<EntityPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<EntityPropertyResult>() {});
        return response.getBody();
    }
    private EntityPropertyResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新领域对象属性
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public EntityPropertyResult update(String id, EntityPropertyEditDto editDto) {
        String url = "http://"+host+"/icode/domain/entityproperty/"+id;
        ResponseEntity<EntityPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<EntityPropertyResult>() {});
        return response.getBody();
    }

    public EntityPropertyResult updateFail(String id, EntityPropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询领域对象属性
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public EntityPropertyResult get(String id) {
        String url = "http://"+host+"/icode/domain/entityproperty/"+id;
        return restTemplate.getForObject(url, EntityPropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private EntityPropertyResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询领域对象属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public EntityPropertyPageResult list(PageSearchRequest<EntityPropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/entityproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, EntityPropertyPageResult.class);
    }
    public EntityPropertyPageResult listFail(PageSearchRequest<EntityPropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        EntityPropertyPageResult result = new EntityPropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private EntityPropertyResult errorResult(){
        EntityPropertyResult result = new EntityPropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
