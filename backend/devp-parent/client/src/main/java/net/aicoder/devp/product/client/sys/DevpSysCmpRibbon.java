package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpEditDto;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpResult;
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
 * 系统组件客户端
 * @author icode
 */
@Service
public class DevpSysCmpRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpRibbon.class);

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
     * 新增系统组件
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysCmpResult add(DevpSysCmpAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysCmp";
        return restTemplate.postForObject(url, addDto, DevpSysCmpResult.class);
    }
    private DevpSysCmpResult addFail(DevpSysCmpAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpResult result = new DevpSysCmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统组件
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysCmpResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysCmp/"+id;
        ResponseEntity<DevpSysCmpResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysCmpResult>() {});
        return response.getBody();
    }
    private DevpSysCmpResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpResult result = new DevpSysCmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统组件
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysCmpResult update(Long id, DevpSysCmpEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysCmp/"+id;
        ResponseEntity<DevpSysCmpResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysCmpResult>() {});
        return response.getBody();
    }

    public DevpSysCmpResult updateFail(Long id, DevpSysCmpEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpResult result = new DevpSysCmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统组件
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysCmpResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysCmp/"+id;
        return restTemplate.getForObject(url, DevpSysCmpResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysCmpResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpResult result = new DevpSysCmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysCmpPageResult list(PageSearchRequest<DevpSysCmpCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysCmp/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysCmpPageResult.class);
    }
    public DevpSysCmpPageResult listFail(PageSearchRequest<DevpSysCmpCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpPageResult result = new DevpSysCmpPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
