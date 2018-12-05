package net.aicoder.speedcloud.client.app;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.client.app.result.AppBaseInfoPageResult;
import net.aicoder.speedcloud.client.app.result.AppBaseInfoResult;
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
 * 应用客户端
 * @author icode
 */
@Service
public class AppBaseInfoRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppBaseInfoRibbon.class);

    private String host = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增应用
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AppBaseInfoResult add(AppBaseInfoAddDto addDto) {
        String url = "http://"+host+"/speedcloud/app/appbaseinfo";
        return restTemplate.postForObject(url, addDto, AppBaseInfoResult.class);
    }
    private AppBaseInfoResult addFail(AppBaseInfoAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除应用
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AppBaseInfoResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/app/appbaseinfo/"+id;
        ResponseEntity<AppBaseInfoResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AppBaseInfoResult>() {});
        return response.getBody();
    }
    private AppBaseInfoResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新应用
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AppBaseInfoResult update(String id, AppBaseInfoEditDto editDto) {
        String url = "http://"+host+"/speedcloud/app/appbaseinfo/"+id;
        ResponseEntity<AppBaseInfoResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AppBaseInfoResult>() {});
        return response.getBody();
    }

    public AppBaseInfoResult updateFail(String id, AppBaseInfoEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询应用
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AppBaseInfoResult get(Long id) {
        String url = "http://"+host+"/speedcloud/app/appbaseinfo/"+id;
        return restTemplate.getForObject(url, AppBaseInfoResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AppBaseInfoResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询应用列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AppBaseInfoPageResult list(PageSearchRequest<AppBaseInfoCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/app/appbaseinfo/list";
        return restTemplate.postForObject(url, pageSearchRequest, AppBaseInfoPageResult.class);
    }
    public AppBaseInfoPageResult listFail(PageSearchRequest<AppBaseInfoCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AppBaseInfoPageResult result = new AppBaseInfoPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AppBaseInfoResult errorResult(){
        AppBaseInfoResult result = new AppBaseInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
