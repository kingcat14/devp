package net.aicoder.speedcloud.asset.client.asset.info;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetPropertyPageResult;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetPropertyResult;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyEditDto;
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
 * 资产属性客户端
 * @author icode
 */
@Service
public class AssetPropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetPropertyRibbon.class);

    private String host = "SPEEDCLOUD-ASSET-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增资产属性
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AssetPropertyResult add(AssetPropertyAddDto addDto) {
        String url = "http://"+host+"/asset/asset/info/assetproperty";
        return restTemplate.postForObject(url, addDto, AssetPropertyResult.class);
    }
    private AssetPropertyResult addFail(AssetPropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资产属性
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AssetPropertyResult delete(Long id) {
        String url = "http://"+host+"/asset/asset/info/assetproperty/"+id;
        ResponseEntity<AssetPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AssetPropertyResult>() {});
        return response.getBody();
    }
    private AssetPropertyResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新资产属性
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AssetPropertyResult update(Long id, AssetPropertyEditDto editDto) {
        String url = "http://"+host+"/asset/asset/info/assetproperty/"+id;
        ResponseEntity<AssetPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AssetPropertyResult>() {});
        return response.getBody();
    }

    public AssetPropertyResult updateFail(Long id, AssetPropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资产属性
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AssetPropertyResult get(Long id) {
        String url = "http://"+host+"/asset/asset/info/assetproperty/"+id;
        return restTemplate.getForObject(url, AssetPropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AssetPropertyResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资产属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AssetPropertyPageResult list(PageSearchRequest<AssetPropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/asset/asset/info/assetproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, AssetPropertyPageResult.class);
    }
    public AssetPropertyPageResult listFail(PageSearchRequest<AssetPropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AssetPropertyPageResult result = new AssetPropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AssetPropertyResult errorResult(){
        AssetPropertyResult result = new AssetPropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
