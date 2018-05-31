package net.aicoder.devp.product.client.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.product.result.DevpPrdLinePrdPageResult;
import net.aicoder.devp.product.client.product.result.DevpPrdLinePrdResult;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdEditDto;
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
 * 产品所属产品线定义客户端
 * @author icode
 */
@Service
public class DevpPrdLinePrdRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdLinePrdRibbon.class);

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
     * 新增产品所属产品线定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpPrdLinePrdResult add(DevpPrdLinePrdAddDto addDto) {
        String url = "http://"+host+"/product/devpPrdLinePrd";
        return restTemplate.postForObject(url, addDto, DevpPrdLinePrdResult.class);
    }
    private DevpPrdLinePrdResult addFail(DevpPrdLinePrdAddDto addDto) {
        DevpPrdLinePrdResult result = new DevpPrdLinePrdResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除产品所属产品线定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpPrdLinePrdResult delete(Long id) {
        String url = "http://"+host+"/product/devpPrdLinePrd/"+id;
        ResponseEntity<DevpPrdLinePrdResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpPrdLinePrdResult>() {});
        return response.getBody();
    }
    private DevpPrdLinePrdResult deleteFail(Long id) {
        DevpPrdLinePrdResult result = new DevpPrdLinePrdResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新产品所属产品线定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpPrdLinePrdResult update(Long id, DevpPrdLinePrdEditDto editDto) {
        String url = "http://"+host+"/product/devpPrdLinePrd/"+id;
        ResponseEntity<DevpPrdLinePrdResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpPrdLinePrdResult>() {});
        return response.getBody();
    }

    public DevpPrdLinePrdResult updateFail(Long id, DevpPrdLinePrdEditDto updateRequest) {
        DevpPrdLinePrdResult result = new DevpPrdLinePrdResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询产品所属产品线定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpPrdLinePrdResult get(Long id) {
        String url = "http://"+host+"/product/devpPrdLinePrd/"+id;
        return restTemplate.getForObject(url, DevpPrdLinePrdResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpPrdLinePrdResult getFail(Long id) {
        DevpPrdLinePrdResult result = new DevpPrdLinePrdResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询产品所属产品线定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpPrdLinePrdPageResult list(PageSearchRequest<DevpPrdLinePrdCondition> pageSearchRequest) {
        String url = "http://"+host+"/product/devpPrdLinePrd/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpPrdLinePrdPageResult.class);
    }
    public DevpPrdLinePrdPageResult listFail(PageSearchRequest<DevpPrdLinePrdCondition> pageSearchRequest) {
        DevpPrdLinePrdPageResult result = new DevpPrdLinePrdPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
