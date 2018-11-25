package net.aicoder.speedcloud.icode.client.domain;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeEditDto;
import net.aicoder.speedcloud.icode.client.domain.result.ModelTypePageResult;
import net.aicoder.speedcloud.icode.client.domain.result.ModelTypeResult;
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
 * 模型类型客户端
 * @author icode
 */
@Service
public class ModelTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelTypeRibbon.class);

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
     * 新增模型类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ModelTypeResult add(ModelTypeAddDto addDto) {
        String url = "http://"+host+"/icode/domain/modeltype";
        return restTemplate.postForObject(url, addDto, ModelTypeResult.class);
    }
    private ModelTypeResult addFail(ModelTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除模型类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ModelTypeResult delete(String id) {
        String url = "http://"+host+"/icode/domain/modeltype/"+id;
        ResponseEntity<ModelTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ModelTypeResult>() {});
        return response.getBody();
    }
    private ModelTypeResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新模型类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ModelTypeResult update(String id, ModelTypeEditDto editDto) {
        String url = "http://"+host+"/icode/domain/modeltype/"+id;
        ResponseEntity<ModelTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ModelTypeResult>() {});
        return response.getBody();
    }

    public ModelTypeResult updateFail(String id, ModelTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询模型类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ModelTypeResult get(String id) {
        String url = "http://"+host+"/icode/domain/modeltype/"+id;
        return restTemplate.getForObject(url, ModelTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ModelTypeResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询模型类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ModelTypePageResult list(PageSearchRequest<ModelTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/icode/domain/modeltype/list";
        return restTemplate.postForObject(url, pageSearchRequest, ModelTypePageResult.class);
    }
    public ModelTypePageResult listFail(PageSearchRequest<ModelTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ModelTypePageResult result = new ModelTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ModelTypeResult errorResult(){
        ModelTypeResult result = new ModelTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
