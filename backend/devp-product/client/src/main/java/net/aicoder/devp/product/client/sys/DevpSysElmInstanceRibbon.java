package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstancePageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstanceResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceEditDto;
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
 * 系统元素实例客户端
 * @author icode
 */
@Service
public class DevpSysElmInstanceRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstanceRibbon.class);

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
     * 新增系统元素实例
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysElmInstanceResult add(DevpSysElmInstanceAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysElmInstance";
        return restTemplate.postForObject(url, addDto, DevpSysElmInstanceResult.class);
    }
    private DevpSysElmInstanceResult addFail(DevpSysElmInstanceAddDto addDto) {
        DevpSysElmInstanceResult result = new DevpSysElmInstanceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统元素实例
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysElmInstanceResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysElmInstance/"+id;
        ResponseEntity<DevpSysElmInstanceResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysElmInstanceResult>() {});
        return response.getBody();
    }
    private DevpSysElmInstanceResult deleteFail(Long id) {
        DevpSysElmInstanceResult result = new DevpSysElmInstanceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统元素实例
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysElmInstanceResult update(Long id, DevpSysElmInstanceEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysElmInstance/"+id;
        ResponseEntity<DevpSysElmInstanceResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysElmInstanceResult>() {});
        return response.getBody();
    }

    public DevpSysElmInstanceResult updateFail(Long id, DevpSysElmInstanceEditDto updateRequest) {
        DevpSysElmInstanceResult result = new DevpSysElmInstanceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统元素实例
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysElmInstanceResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysElmInstance/"+id;
        return restTemplate.getForObject(url, DevpSysElmInstanceResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysElmInstanceResult getFail(Long id) {
        DevpSysElmInstanceResult result = new DevpSysElmInstanceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统元素实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysElmInstancePageResult list(PageSearchRequest<DevpSysElmInstanceCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysElmInstance/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysElmInstancePageResult.class);
    }
    public DevpSysElmInstancePageResult listFail(PageSearchRequest<DevpSysElmInstanceCondition> pageSearchRequest) {
        DevpSysElmInstancePageResult result = new DevpSysElmInstancePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
