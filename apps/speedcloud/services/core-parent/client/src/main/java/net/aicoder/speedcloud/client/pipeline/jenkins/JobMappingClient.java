package net.aicoder.speedcloud.client.pipeline.jenkins;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingCondition;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingEditDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.vo.JobMappingVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 任务映射客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/jenkins/jobmapping", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/jenkins/jobmapping", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface JobMappingClient {


    /**
     * 新增任务映射
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<JobMappingVO> add(@RequestBody JobMappingAddDto addDto);


    /**
    * 删除任务映射
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<JobMappingVO> delete(@PathVariable("id") String id);

    /**
    * 更新任务映射
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<JobMappingVO> update(@PathVariable("id") String id, @RequestBody JobMappingEditDto editDto);

    /**
    * 根据ID查询任务映射
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<JobMappingVO> get(@PathVariable("id") String id);

    /**
    * 查询任务映射列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<JobMappingVO>> list(@RequestBody PageSearchRequest<JobMappingCondition> pageSearchRequest);

}
