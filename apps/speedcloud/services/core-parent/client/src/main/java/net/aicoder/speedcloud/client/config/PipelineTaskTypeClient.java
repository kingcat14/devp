package net.aicoder.speedcloud.client.config;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.PipelineTaskTypeVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 任务类型客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/config/pipelinetasktype", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/config/pipelinetasktype", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface PipelineTaskTypeClient {


    /**
     * 新增任务类型
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<PipelineTaskTypeVO> add(@RequestBody PipelineTaskTypeAddDto addDto);


    /**
    * 删除任务类型
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<PipelineTaskTypeVO> delete(@PathVariable("id") String id);

    /**
    * 更新任务类型
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<PipelineTaskTypeVO> update(@PathVariable("id") String id, @RequestBody PipelineTaskTypeEditDto editDto);

    /**
    * 根据ID查询任务类型
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<PipelineTaskTypeVO> get(@PathVariable("id") String id);

    /**
    * 查询任务类型列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<PipelineTaskTypeVO>> list(@RequestBody PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest);

}
