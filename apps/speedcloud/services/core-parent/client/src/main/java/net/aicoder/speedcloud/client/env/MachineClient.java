package net.aicoder.speedcloud.client.env;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.dto.MachineAddDto;
import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import net.aicoder.speedcloud.business.env.dto.MachineEditDto;
import net.aicoder.speedcloud.business.env.vo.MachineVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 服务器客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/env/machine", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/env/machine", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface MachineClient {


    /**
     * 新增服务器
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<MachineVO> add(@RequestBody MachineAddDto addDto);


    /**
    * 删除服务器
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<MachineVO> delete(@PathVariable("id") String id);

    /**
    * 更新服务器
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<MachineVO> update(@PathVariable("id") String id, @RequestBody MachineEditDto editDto);

    /**
    * 根据ID查询服务器
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<MachineVO> get(@PathVariable("id") String id);

    /**
    * 查询服务器列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<MachineVO>> list(@RequestBody PageSearchRequest<MachineCondition> pageSearchRequest);

}
