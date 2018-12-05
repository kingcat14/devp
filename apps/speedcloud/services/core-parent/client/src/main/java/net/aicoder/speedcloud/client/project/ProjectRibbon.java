package net.aicoder.speedcloud.client.project;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.project.dto.ProjectAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectCondition;
import net.aicoder.speedcloud.business.project.dto.ProjectEditDto;
import net.aicoder.speedcloud.client.project.result.ProjectPageResult;
import net.aicoder.speedcloud.client.project.result.ProjectResult;
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
 * 项目客户端
 * @author icode
 */
@Service
public class ProjectRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRibbon.class);

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
     * 新增项目
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ProjectResult add(ProjectAddDto addDto) {
        String url = "http://"+host+"/speedcloud/project/project";
        return restTemplate.postForObject(url, addDto, ProjectResult.class);
    }
    private ProjectResult addFail(ProjectAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除项目
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ProjectResult delete(String id) {
        String url = "http://"+host+"/speedcloud/project/project/"+id;
        ResponseEntity<ProjectResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ProjectResult>() {});
        return response.getBody();
    }
    private ProjectResult deleteFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新项目
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ProjectResult update(String id, ProjectEditDto editDto) {
        String url = "http://"+host+"/speedcloud/project/project/"+id;
        ResponseEntity<ProjectResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ProjectResult>() {});
        return response.getBody();
    }

    public ProjectResult updateFail(String id, ProjectEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询项目
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ProjectResult get(String id) {
        String url = "http://"+host+"/speedcloud/project/project/"+id;
        return restTemplate.getForObject(url, ProjectResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ProjectResult getFail(String id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询项目列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ProjectPageResult list(PageSearchRequest<ProjectCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/project/project/list";
        return restTemplate.postForObject(url, pageSearchRequest, ProjectPageResult.class);
    }
    public ProjectPageResult listFail(PageSearchRequest<ProjectCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ProjectPageResult result = new ProjectPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ProjectResult errorResult(){
        ProjectResult result = new ProjectResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
