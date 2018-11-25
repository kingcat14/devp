package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterPropertyPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityActionParameterPropertyResult;
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
 * 领域对象行为参数属性客户端
 * @author icode
 */
@Service
public class EntityActionParameterPropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityActionParameterPropertyRibbon.class);

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
     * 新增领域对象行为参数属性
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public EntityActionParameterPropertyResult add(EntityActionParameterPropertyAddDto addDto) {
        String url = "http://"+host+"/icode/domain/entityactionparameterproperty";
        return restTemplate.postForObject(url, addDto, EntityActionParameterPropertyResult.class);
    }
    private EntityActionParameterPropertyResult addFail(EntityActionParameterPropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除领域对象行为参数属性
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public EntityActionParameterPropertyResult delete(String id) {
        String url = "http://"+host+"/icode/domain/entityactionparameterproperty/"+id;
        ResponseEntity<EntityActionParameterPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<EntityActionParameterPropertyResult>() {});
        return response.getBody();
    }
    private EntityActionParameterPropertyResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新领域对象行为参数属性
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public EntityActionParameterPropertyResult update(String id, EntityActionParameterPropertyEditDto editDto) {
        String url = "http://"+host+"/icode/domain/entityactionparameterproperty/"+id;
        ResponseEntity<EntityActionParameterPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<EntityActionParameterPropertyResult>() {});
        return response.getBody();
    }

    public EntityActionParameterPropertyResult updateFail(String id, EntityActionParameterPropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询领域对象行为参数属性
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public EntityActionParameterPropertyResult get(String id) {
        String url = "http://"+host+"/icode/domain/entityactionparameterproperty/"+id;
        return restTemplate.getForObject(url, EntityActionParameterPropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private EntityActionParameterPropertyResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询领域对象行为参数属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public EntityActionParameterPropertyPageResult list(PageSearchRequest<EntityActionParameterPropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/entityactionparameterproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, EntityActionParameterPropertyPageResult.class);
    }
    public EntityActionParameterPropertyPageResult listFail(PageSearchRequest<EntityActionParameterPropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        EntityActionParameterPropertyPageResult result = new EntityActionParameterPropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private EntityActionParameterPropertyResult errorResult(){
        EntityActionParameterPropertyResult result = new EntityActionParameterPropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
