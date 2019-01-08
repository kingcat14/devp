package net.aicoder.speedcloud.client.pipeline.template;


import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskCondition;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskEditDto;
import net.aicoder.speedcloud.business.pipeline.template.vo.PipelineTemplateTaskVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 任务模板客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/template/pipelinetemplatetask", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/pipeline/template/pipelinetemplatetask", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface PipelineTemplateTaskClient {


    /**
     * 新增任务模板
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<PipelineTemplateTaskVO> add(@RequestBody PipelineTemplateTaskAddDto addDto);


    /**
    * 删除任务模板
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<PipelineTemplateTaskVO> delete(@PathVariable("id") String id);

    /**
    * 更新任务模板
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<PipelineTemplateTaskVO> update(@PathVariable("id") String id, @RequestBody PipelineTemplateTaskEditDto editDto);

    /**
    * 根据ID查询任务模板
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<PipelineTemplateTaskVO> get(@PathVariable("id") String id);

    /**
    * 查询任务模板列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<PipelineTemplateTaskVO>> list(@RequestBody PageSearchRequest<PipelineTemplateTaskCondition> pageSearchRequest);

}
