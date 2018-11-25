package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.EntityPageResult;
import net.aicoder.speedcloud.icode.client.domain.result.EntityResult;
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
 * 领域对象客户端
 * @author icode
 */
@Service
public class EntityRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityRibbon.class);

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
     * 新增领域对象
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public EntityResult add(EntityAddDto addDto) {
        String url = "http://"+host+"/icode/domain/entity";
        return restTemplate.postForObject(url, addDto, EntityResult.class);
    }
    private EntityResult addFail(EntityAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除领域对象
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public EntityResult delete(String id) {
        String url = "http://"+host+"/icode/domain/entity/"+id;
        ResponseEntity<EntityResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<EntityResult>() {});
        return response.getBody();
    }
    private EntityResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新领域对象
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public EntityResult update(String id, EntityEditDto editDto) {
        String url = "http://"+host+"/icode/domain/entity/"+id;
        ResponseEntity<EntityResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<EntityResult>() {});
        return response.getBody();
    }

    /**
     * 复制领域对象
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getFail")
    public EntityResult copy(String id) {
        String url = "http://"+host+"/icode/domain/entity/"+id+"/copy";
        ResponseEntity<EntityResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(null), new ParameterizedTypeReference<EntityResult>() {});
        return response.getBody();
    }

    public EntityResult updateFail(String id, EntityEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询领域对象
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public EntityResult get(String id) {
        String url = "http://"+host+"/icode/domain/entity/"+id;
        return restTemplate.getForObject(url, EntityResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private EntityResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询领域对象列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public EntityPageResult list(PageSearchRequest<EntityCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/entity/list";
        return restTemplate.postForObject(url, pageSearchRequest, EntityPageResult.class);
    }
    public EntityPageResult listFail(PageSearchRequest<EntityCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        EntityPageResult result = new EntityPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private EntityResult errorResult(){
        EntityResult result = new EntityResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
