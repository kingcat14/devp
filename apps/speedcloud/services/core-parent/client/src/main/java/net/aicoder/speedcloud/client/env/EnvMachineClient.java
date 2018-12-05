package net.aicoder.speedcloud.client.env;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.dto.EnvMachineAddDto;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import net.aicoder.speedcloud.business.env.dto.EnvMachineEditDto;
import net.aicoder.speedcloud.business.env.vo.EnvMachineVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 环境设备关联客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/env/envmachine", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/env/envmachine", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface EnvMachineClient {


    /**
     * 新增环境设备关联
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<EnvMachineVO> add(@RequestBody EnvMachineAddDto addDto);


    /**
    * 删除环境设备关联
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<EnvMachineVO> delete(@PathVariable("id") Long id);

    /**
    * 更新环境设备关联
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<EnvMachineVO> update(@PathVariable("id") Long id, @RequestBody EnvMachineEditDto editDto);

    /**
    * 根据ID查询环境设备关联
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<EnvMachineVO> get(@PathVariable("id") Long id);

    /**
    * 查询环境设备关联列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<EnvMachineVO>> list(@RequestBody PageSearchRequest<EnvMachineCondition> pageSearchRequest);

}
