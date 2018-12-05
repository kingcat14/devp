package net.aicoder.speedcloud.client.app;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.vo.AppBaseInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 应用（系统）客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/appbaseinfo", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/appbaseinfo", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface AppBaseInfoClient {


    /**
     * 新增应用（系统）
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<AppBaseInfoVO> add(@RequestBody AppBaseInfoAddDto addDto);


    /**
    * 删除应用（系统）
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<AppBaseInfoVO> delete(@PathVariable("id") String id);

    /**
    * 更新应用（系统）
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<AppBaseInfoVO> update(@PathVariable("id") String id, @RequestBody AppBaseInfoEditDto editDto);

    /**
    * 根据ID查询应用（系统）
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<AppBaseInfoVO> get(@PathVariable("id") String id);

    /**
    * 查询应用（系统）列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<AppBaseInfoVO>> list(@RequestBody PageSearchRequest<AppBaseInfoCondition> pageSearchRequest);

}
