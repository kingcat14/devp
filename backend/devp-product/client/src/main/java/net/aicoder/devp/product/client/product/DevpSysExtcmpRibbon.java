package net.aicoder.devp.product.client.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.product.result.DevpSysExtcmpPageResult;
import net.aicoder.devp.product.client.product.result.DevpSysExtcmpResult;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpAddDto;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpCondition;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpEditDto;
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
 * 产品包含的外部组件客户端
 * @author icode
 */
@Service
public class DevpSysExtcmpRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysExtcmpRibbon.class);

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
     * 新增产品包含的外部组件
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysExtcmpResult add(DevpSysExtcmpAddDto addDto) {
        String url = "http://"+host+"/product/devpSysExtcmp";
        return restTemplate.postForObject(url, addDto, DevpSysExtcmpResult.class);
    }
    private DevpSysExtcmpResult addFail(DevpSysExtcmpAddDto addDto) {
        DevpSysExtcmpResult result = new DevpSysExtcmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除产品包含的外部组件
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysExtcmpResult delete(Long id) {
        String url = "http://"+host+"/product/devpSysExtcmp/"+id;
        ResponseEntity<DevpSysExtcmpResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysExtcmpResult>() {});
        return response.getBody();
    }
    private DevpSysExtcmpResult deleteFail(Long id) {
        DevpSysExtcmpResult result = new DevpSysExtcmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新产品包含的外部组件
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysExtcmpResult update(Long id, DevpSysExtcmpEditDto editDto) {
        String url = "http://"+host+"/product/devpSysExtcmp/"+id;
        ResponseEntity<DevpSysExtcmpResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysExtcmpResult>() {});
        return response.getBody();
    }

    public DevpSysExtcmpResult updateFail(Long id, DevpSysExtcmpEditDto updateRequest) {
        DevpSysExtcmpResult result = new DevpSysExtcmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询产品包含的外部组件
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysExtcmpResult get(Long id) {
        String url = "http://"+host+"/product/devpSysExtcmp/"+id;
        return restTemplate.getForObject(url, DevpSysExtcmpResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysExtcmpResult getFail(Long id) {
        DevpSysExtcmpResult result = new DevpSysExtcmpResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询产品包含的外部组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysExtcmpPageResult list(PageSearchRequest<DevpSysExtcmpCondition> pageSearchRequest) {
        String url = "http://"+host+"/product/devpSysExtcmp/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysExtcmpPageResult.class);
    }
    public DevpSysExtcmpPageResult listFail(PageSearchRequest<DevpSysExtcmpCondition> pageSearchRequest) {
        DevpSysExtcmpPageResult result = new DevpSysExtcmpPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
