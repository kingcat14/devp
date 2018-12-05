package net.aicoder.speedcloud.client.app;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeBaseInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 代码基本信息客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/codebaseinfo", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/codebaseinfo", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface CodeBaseInfoClient {


    /**
     * 新增代码基本信息
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<CodeBaseInfoVO> add(@RequestBody CodeBaseInfoAddDto addDto);


    /**
    * 删除代码基本信息
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<CodeBaseInfoVO> delete(@PathVariable("id") String id);

    /**
    * 更新代码基本信息
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<CodeBaseInfoVO> update(@PathVariable("id") String id, @RequestBody CodeBaseInfoEditDto editDto);

    /**
    * 根据ID查询代码基本信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<CodeBaseInfoVO> get(@PathVariable("id") String id);

    /**
    * 查询代码基本信息列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<CodeBaseInfoVO>> list(@RequestBody PageSearchRequest<CodeBaseInfoCondition> pageSearchRequest);

}
