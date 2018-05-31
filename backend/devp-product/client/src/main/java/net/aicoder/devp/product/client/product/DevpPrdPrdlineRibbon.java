package net.aicoder.devp.product.client.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.product.result.DevpPrdPrdlinePageResult;
import net.aicoder.devp.product.client.product.result.DevpPrdPrdlineResult;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineEditDto;
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
 * 产品线定义客户端
 * @author icode
 */
@Service
public class DevpPrdPrdlineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPrdlineRibbon.class);

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
     * 新增产品线定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpPrdPrdlineResult add(DevpPrdPrdlineAddDto addDto) {
        String url = "http://"+host+"/product/devpPrdPrdline";
        return restTemplate.postForObject(url, addDto, DevpPrdPrdlineResult.class);
    }
    private DevpPrdPrdlineResult addFail(DevpPrdPrdlineAddDto addDto) {
        DevpPrdPrdlineResult result = new DevpPrdPrdlineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除产品线定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpPrdPrdlineResult delete(Long id) {
        String url = "http://"+host+"/product/devpPrdPrdline/"+id;
        ResponseEntity<DevpPrdPrdlineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpPrdPrdlineResult>() {});
        return response.getBody();
    }
    private DevpPrdPrdlineResult deleteFail(Long id) {
        DevpPrdPrdlineResult result = new DevpPrdPrdlineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新产品线定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpPrdPrdlineResult update(Long id, DevpPrdPrdlineEditDto editDto) {
        String url = "http://"+host+"/product/devpPrdPrdline/"+id;
        ResponseEntity<DevpPrdPrdlineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpPrdPrdlineResult>() {});
        return response.getBody();
    }

    public DevpPrdPrdlineResult updateFail(Long id, DevpPrdPrdlineEditDto updateRequest) {
        DevpPrdPrdlineResult result = new DevpPrdPrdlineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询产品线定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpPrdPrdlineResult get(Long id) {
        String url = "http://"+host+"/product/devpPrdPrdline/"+id;
        return restTemplate.getForObject(url, DevpPrdPrdlineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpPrdPrdlineResult getFail(Long id) {
        DevpPrdPrdlineResult result = new DevpPrdPrdlineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询产品线定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpPrdPrdlinePageResult list(PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest) {
        String url = "http://"+host+"/product/devpPrdPrdline/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpPrdPrdlinePageResult.class);
    }
    public DevpPrdPrdlinePageResult listFail(PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest) {
        DevpPrdPrdlinePageResult result = new DevpPrdPrdlinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
