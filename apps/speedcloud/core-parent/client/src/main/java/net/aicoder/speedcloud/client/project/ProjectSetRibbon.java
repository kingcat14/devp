package net.aicoder.speedcloud.client.project;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.project.result.ProjectSetPageResult;
import net.aicoder.speedcloud.client.project.result.ProjectSetResult;
import net.aicoder.speedcloud.business.project.dto.ProjectSetAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectSetCondition;
import net.aicoder.speedcloud.business.project.dto.ProjectSetEditDto;
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
 * 项目集客户端
 * @author icode
 */
@Service
public class ProjectSetRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectSetRibbon.class);

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
     * 新增项目集
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ProjectSetResult add(ProjectSetAddDto addDto) {
        String url = "http://"+host+"/speedcloud/project/projectset";
        return restTemplate.postForObject(url, addDto, ProjectSetResult.class);
    }
    private ProjectSetResult addFail(ProjectSetAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除项目集
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ProjectSetResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/project/projectset/"+id;
        ResponseEntity<ProjectSetResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ProjectSetResult>() {});
        return response.getBody();
    }
    private ProjectSetResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新项目集
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ProjectSetResult update(Long id, ProjectSetEditDto editDto) {
        String url = "http://"+host+"/speedcloud/project/projectset/"+id;
        ResponseEntity<ProjectSetResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ProjectSetResult>() {});
        return response.getBody();
    }

    public ProjectSetResult updateFail(Long id, ProjectSetEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询项目集
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ProjectSetResult get(Long id) {
        String url = "http://"+host+"/speedcloud/project/projectset/"+id;
        return restTemplate.getForObject(url, ProjectSetResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ProjectSetResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询项目集列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ProjectSetPageResult list(PageSearchRequest<ProjectSetCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/project/projectset/list";
        return restTemplate.postForObject(url, pageSearchRequest, ProjectSetPageResult.class);
    }
    public ProjectSetPageResult listFail(PageSearchRequest<ProjectSetCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ProjectSetPageResult result = new ProjectSetPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ProjectSetResult errorResult(){
        ProjectSetResult result = new ProjectSetResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
