package net.aicoder.speedcloud.client.app;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeAddDto;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeCondition;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeEditDto;
import net.aicoder.speedcloud.business.app.vo.ApplicationTypeVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 应用类型客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/applicationtype", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/applicationtype", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface ApplicationTypeClient {


    /**
     * 新增应用类型
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ApplicationTypeVO> add(@RequestBody ApplicationTypeAddDto addDto);


    /**
    * 删除应用类型
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ApplicationTypeVO> delete(@PathVariable("id") String id);

    /**
    * 更新应用类型
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ApplicationTypeVO> update(@PathVariable("id") String id, @RequestBody ApplicationTypeEditDto editDto);

    /**
    * 根据ID查询应用类型
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ApplicationTypeVO> get(@PathVariable("id") String id);

    /**
    * 查询应用类型列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ApplicationTypeVO>> list(@RequestBody PageSearchRequest<ApplicationTypeCondition> pageSearchRequest);

}
