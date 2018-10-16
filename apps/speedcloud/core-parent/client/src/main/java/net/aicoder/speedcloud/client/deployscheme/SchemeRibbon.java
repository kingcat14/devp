package net.aicoder.speedcloud.client.deployscheme;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.deployscheme.result.SchemePageResult;
import net.aicoder.speedcloud.client.deployscheme.result.SchemeResult;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeEditDto;
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
 * 部署方案客户端
 * @author icode
 */
@Service
public class SchemeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchemeRibbon.class);

    private String host = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增部署方案
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public SchemeResult add(SchemeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/scheme";
        return restTemplate.postForObject(url, addDto, SchemeResult.class);
    }
    private SchemeResult addFail(SchemeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除部署方案
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public SchemeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/scheme/"+id;
        ResponseEntity<SchemeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<SchemeResult>() {});
        return response.getBody();
    }
    private SchemeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新部署方案
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public SchemeResult update(Long id, SchemeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/deployscheme/scheme/"+id;
        ResponseEntity<SchemeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<SchemeResult>() {});
        return response.getBody();
    }

    public SchemeResult updateFail(Long id, SchemeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询部署方案
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public SchemeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/deployscheme/scheme/"+id;
        return restTemplate.getForObject(url, SchemeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private SchemeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询部署方案列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public SchemePageResult list(PageSearchRequest<SchemeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/deployscheme/scheme/list";
        return restTemplate.postForObject(url, pageSearchRequest, SchemePageResult.class);
    }
    public SchemePageResult listFail(PageSearchRequest<SchemeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        SchemePageResult result = new SchemePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private SchemeResult errorResult(){
        SchemeResult result = new SchemeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
