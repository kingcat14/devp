package net.aicoder.speedcloud.asset.client.asset.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeEditDto;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypePageResult;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypeResult;
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
 * 资产分类客户端
 * @author icode
 */
@Service
public class AssetTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypeRibbon.class);

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
     * 新增资产分类
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AssetTypeResult add(AssetTypeAddDto addDto) {
        String url = "http://"+host+"/asset/asset/config/assettype";
        return restTemplate.postForObject(url, addDto, AssetTypeResult.class);
    }
    private AssetTypeResult addFail(AssetTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除资产分类
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AssetTypeResult delete(Long id) {
        String url = "http://"+host+"/asset/asset/config/assettype/"+id;
        ResponseEntity<AssetTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AssetTypeResult>() {});
        return response.getBody();
    }
    private AssetTypeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新资产分类
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AssetTypeResult update(Long id, AssetTypeEditDto editDto) {
        String url = "http://"+host+"/asset/asset/config/assettype/"+id;
        ResponseEntity<AssetTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AssetTypeResult>() {});
        return response.getBody();
    }

    public AssetTypeResult updateFail(Long id, AssetTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询资产分类
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AssetTypeResult get(Long id) {
        String url = "http://"+host+"/asset/asset/config/assettype/"+id;
        return restTemplate.getForObject(url, AssetTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AssetTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询资产分类列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AssetTypePageResult list(PageSearchRequest<AssetTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/asset/asset/config/assettype/list";
        return restTemplate.postForObject(url, pageSearchRequest, AssetTypePageResult.class);
    }
    public AssetTypePageResult listFail(PageSearchRequest<AssetTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AssetTypePageResult result = new AssetTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AssetTypeResult errorResult(){
        AssetTypeResult result = new AssetTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
