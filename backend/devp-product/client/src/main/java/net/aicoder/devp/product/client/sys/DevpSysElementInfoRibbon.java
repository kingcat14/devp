package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.result.DevpSysElementInfoPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElementInfoResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoEditDto;
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
 * 系统元素扩充信息客户端
 * @author icode
 */
@Service
public class DevpSysElementInfoRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementInfoRibbon.class);

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
     * 新增系统元素扩充信息
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysElementInfoResult add(DevpSysElementInfoAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysElementInfo";
        return restTemplate.postForObject(url, addDto, DevpSysElementInfoResult.class);
    }
    private DevpSysElementInfoResult addFail(DevpSysElementInfoAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysElementInfoResult result = new DevpSysElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统元素扩充信息
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysElementInfoResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysElementInfo/"+id;
        ResponseEntity<DevpSysElementInfoResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysElementInfoResult>() {});
        return response.getBody();
    }
    private DevpSysElementInfoResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysElementInfoResult result = new DevpSysElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统元素扩充信息
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysElementInfoResult update(Long id, DevpSysElementInfoEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysElementInfo/"+id;
        ResponseEntity<DevpSysElementInfoResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysElementInfoResult>() {});
        return response.getBody();
    }

    public DevpSysElementInfoResult updateFail(Long id, DevpSysElementInfoEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysElementInfoResult result = new DevpSysElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统元素扩充信息
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysElementInfoResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysElementInfo/"+id;
        return restTemplate.getForObject(url, DevpSysElementInfoResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysElementInfoResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysElementInfoResult result = new DevpSysElementInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysElementInfoPageResult list(PageSearchRequest<DevpSysElementInfoCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysElementInfo/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysElementInfoPageResult.class);
    }
    public DevpSysElementInfoPageResult listFail(PageSearchRequest<DevpSysElementInfoCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysElementInfoPageResult result = new DevpSysElementInfoPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
