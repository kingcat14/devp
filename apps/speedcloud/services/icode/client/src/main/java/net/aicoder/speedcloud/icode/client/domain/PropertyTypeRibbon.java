package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.PropertyTypeEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.PropertyTypeListResult;
import net.aicoder.speedcloud.icode.client.domain.result.PropertyTypePageResult;
import net.aicoder.speedcloud.icode.client.domain.result.PropertyTypeResult;
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
 * 属性类型客户端
 * @author icode
 */
@Service
public class PropertyTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyTypeRibbon.class);

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
     * 新增属性类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PropertyTypeResult add(PropertyTypeAddDto addDto) {
        String url = "http://"+host+"/icode/domain/propertytype";
        return restTemplate.postForObject(url, addDto, PropertyTypeResult.class);
    }
    private PropertyTypeResult addFail(PropertyTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除属性类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PropertyTypeResult delete(String id) {
        String url = "http://"+host+"/icode/domain/propertytype/"+id;
        ResponseEntity<PropertyTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PropertyTypeResult>() {});
        return response.getBody();
    }
    private PropertyTypeResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新属性类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PropertyTypeResult update(String id, PropertyTypeEditDto editDto) {
        String url = "http://"+host+"/icode/domain/propertytype/"+id;
        ResponseEntity<PropertyTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PropertyTypeResult>() {});
        return response.getBody();
    }

    public PropertyTypeResult updateFail(String id, PropertyTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
     * 根据ID查询属性类型
     * @param componentId 组件ID
     * @return
     */
    @HystrixCommand(fallbackMethod = "referencePropertyFail")
    public PropertyTypeListResult referenceProperty(String componentId) {
        String url = "http://"+host+"/icode/domain/propertytype/reference/"+componentId;
        return restTemplate.getForObject(url, PropertyTypeListResult.class);
    }
    private PropertyTypeListResult referencePropertyFail(String id, Throwable throwable) {
        LOGGER.error("", throwable);
        PropertyTypeListResult result = new PropertyTypeListResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    /**
	 * 根据ID查询属性类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PropertyTypeResult get(String id) {
        String url = "http://"+host+"/icode/domain/propertytype/"+id;
        return restTemplate.getForObject(url, PropertyTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PropertyTypeResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询属性类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PropertyTypePageResult list(PageSearchRequest<PropertyTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/propertytype/list";
        return restTemplate.postForObject(url, pageSearchRequest, PropertyTypePageResult.class);
    }
    public PropertyTypePageResult listFail(PageSearchRequest<PropertyTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PropertyTypePageResult result = new PropertyTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PropertyTypeResult errorResult(){
        PropertyTypeResult result = new PropertyTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
