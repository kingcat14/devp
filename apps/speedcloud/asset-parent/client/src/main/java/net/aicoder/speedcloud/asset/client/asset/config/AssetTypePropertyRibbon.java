package net.aicoder.speedcloud.asset.client.asset.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypePropertyPageResult;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypePropertyResult;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyEditDto;
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
 * 资产分类属性客户端
 * @author icode
 */
@Service
public class AssetTypePropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypePropertyRibbon.class);

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
     * 新增资产分类属性
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AssetTypePropertyResult add(AssetTypePropertyAddDto addDto) {
        String url = "http://"+host+"/asset/asset/config/assettypeproperty";
        return restTemplate.postForObject(url, addDto, AssetTypePropertyResult.class);
    }
    private AssetTypePropertyResult addFail(AssetTypePropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资产分类属性
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AssetTypePropertyResult delete(Long id) {
        String url = "http://"+host+"/asset/asset/config/assettypeproperty/"+id;
        ResponseEntity<AssetTypePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AssetTypePropertyResult>() {});
        return response.getBody();
    }
    private AssetTypePropertyResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新资产分类属性
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AssetTypePropertyResult update(Long id, AssetTypePropertyEditDto editDto) {
        String url = "http://"+host+"/asset/asset/config/assettypeproperty/"+id;
        ResponseEntity<AssetTypePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AssetTypePropertyResult>() {});
        return response.getBody();
    }

    public AssetTypePropertyResult updateFail(Long id, AssetTypePropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资产分类属性
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AssetTypePropertyResult get(Long id) {
        String url = "http://"+host+"/asset/asset/config/assettypeproperty/"+id;
        return restTemplate.getForObject(url, AssetTypePropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AssetTypePropertyResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资产分类属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AssetTypePropertyPageResult list(PageSearchRequest<AssetTypePropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/asset/asset/config/assettypeproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, AssetTypePropertyPageResult.class);
    }
    public AssetTypePropertyPageResult listFail(PageSearchRequest<AssetTypePropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AssetTypePropertyPageResult result = new AssetTypePropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AssetTypePropertyResult errorResult(){
        AssetTypePropertyResult result = new AssetTypePropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
