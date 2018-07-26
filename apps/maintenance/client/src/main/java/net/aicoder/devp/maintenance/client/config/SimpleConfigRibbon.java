package net.aicoder.devp.maintenance.client.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.client.config.result.SimpleConfigPageResult;
import net.aicoder.devp.maintenance.client.config.result.SimpleConfigResult;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigAddDto;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigCondition;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigEditDto;
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
 * 通用配置客户端
 * @author icode
 */
@Service
public class SimpleConfigRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConfigRibbon.class);

    private String host = "MAINTENANCE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增通用配置
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public SimpleConfigResult add(SimpleConfigAddDto addDto) {
        String url = "http://"+host+"/config/simpleConfig";
        return restTemplate.postForObject(url, addDto, SimpleConfigResult.class);
    }
    private SimpleConfigResult addFail(SimpleConfigAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        SimpleConfigResult result = new SimpleConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除通用配置
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public SimpleConfigResult delete(Long id) {
        String url = "http://"+host+"/config/simpleConfig/"+id;
        ResponseEntity<SimpleConfigResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<SimpleConfigResult>() {});
        return response.getBody();
    }
    private SimpleConfigResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        SimpleConfigResult result = new SimpleConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新通用配置
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public SimpleConfigResult update(Long id, SimpleConfigEditDto editDto) {
        String url = "http://"+host+"/config/simpleConfig/"+id;
        ResponseEntity<SimpleConfigResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<SimpleConfigResult>() {});
        return response.getBody();
    }

    public SimpleConfigResult updateFail(Long id, SimpleConfigEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        SimpleConfigResult result = new SimpleConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询通用配置
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public SimpleConfigResult get(Long id) {
        String url = "http://"+host+"/config/simpleConfig/"+id;
        return restTemplate.getForObject(url, SimpleConfigResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private SimpleConfigResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        SimpleConfigResult result = new SimpleConfigResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询通用配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public SimpleConfigPageResult list(PageSearchRequest<SimpleConfigCondition> pageSearchRequest) {
        String url = "http://"+host+"/config/simpleConfig/list";
        return restTemplate.postForObject(url, pageSearchRequest, SimpleConfigPageResult.class);
    }
    public SimpleConfigPageResult listFail(PageSearchRequest<SimpleConfigCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        SimpleConfigPageResult result = new SimpleConfigPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
