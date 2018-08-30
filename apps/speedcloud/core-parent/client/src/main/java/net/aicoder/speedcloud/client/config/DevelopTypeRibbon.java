package net.aicoder.speedcloud.client.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.config.result.DevelopTypePageResult;
import net.aicoder.speedcloud.client.config.result.DevelopTypeResult;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeCondition;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeEditDto;
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
 * 开发模式客户端
 * @author icode
 */
@Service
public class DevelopTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevelopTypeRibbon.class);

    private String host = "SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增开发模式
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevelopTypeResult add(DevelopTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/config/developtype";
        return restTemplate.postForObject(url, addDto, DevelopTypeResult.class);
    }
    private DevelopTypeResult addFail(DevelopTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除开发模式
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevelopTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/config/developtype/"+id;
        ResponseEntity<DevelopTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevelopTypeResult>() {});
        return response.getBody();
    }
    private DevelopTypeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新开发模式
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevelopTypeResult update(Long id, DevelopTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/config/developtype/"+id;
        ResponseEntity<DevelopTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevelopTypeResult>() {});
        return response.getBody();
    }

    public DevelopTypeResult updateFail(Long id, DevelopTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询开发模式
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevelopTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/config/developtype/"+id;
        return restTemplate.getForObject(url, DevelopTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevelopTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询开发模式列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevelopTypePageResult list(PageSearchRequest<DevelopTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/config/developtype/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevelopTypePageResult.class);
    }
    public DevelopTypePageResult listFail(PageSearchRequest<DevelopTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevelopTypePageResult result = new DevelopTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private DevelopTypeResult errorResult(){
        DevelopTypeResult result = new DevelopTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
