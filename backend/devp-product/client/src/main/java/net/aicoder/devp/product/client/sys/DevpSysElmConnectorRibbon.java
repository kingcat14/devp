package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.result.DevpSysElmConnectorPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElmConnectorResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorEditDto;
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
 * 系统元素间关系客户端
 * @author icode
 */
@Service
public class DevpSysElmConnectorRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmConnectorRibbon.class);

    private String host = "PRODUCT";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增系统元素间关系
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysElmConnectorResult add(DevpSysElmConnectorAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysElmConnector";
        return restTemplate.postForObject(url, addDto, DevpSysElmConnectorResult.class);
    }
    private DevpSysElmConnectorResult addFail(DevpSysElmConnectorAddDto addDto) {
        DevpSysElmConnectorResult result = new DevpSysElmConnectorResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统元素间关系
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysElmConnectorResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysElmConnector/"+id;
        ResponseEntity<DevpSysElmConnectorResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysElmConnectorResult>() {});
        return response.getBody();
    }
    private DevpSysElmConnectorResult deleteFail(Long id) {
        DevpSysElmConnectorResult result = new DevpSysElmConnectorResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统元素间关系
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysElmConnectorResult update(Long id, DevpSysElmConnectorEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysElmConnector/"+id;
        ResponseEntity<DevpSysElmConnectorResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysElmConnectorResult>() {});
        return response.getBody();
    }

    public DevpSysElmConnectorResult updateFail(Long id, DevpSysElmConnectorEditDto updateRequest) {
        DevpSysElmConnectorResult result = new DevpSysElmConnectorResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统元素间关系
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysElmConnectorResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysElmConnector/"+id;
        return restTemplate.getForObject(url, DevpSysElmConnectorResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysElmConnectorResult getFail(Long id) {
        DevpSysElmConnectorResult result = new DevpSysElmConnectorResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统元素间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysElmConnectorPageResult list(PageSearchRequest<DevpSysElmConnectorCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysElmConnector/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysElmConnectorPageResult.class);
    }
    public DevpSysElmConnectorPageResult listFail(PageSearchRequest<DevpSysElmConnectorCondition> pageSearchRequest) {
        DevpSysElmConnectorPageResult result = new DevpSysElmConnectorPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
