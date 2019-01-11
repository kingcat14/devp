package com.yunkang.saas.platform.services.core.client.platform.account;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountAddDto;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountCondition;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountEditDto;
import com.yunkang.saas.platform.services.core.business.platform.account.vo.AccountVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 账号客户端
 * @author icode
 */
@FeignClient(name = "SAAS-CORE-MICROSERVICE", path = "/core/platform/account/account", decode404 = true)
//@FeignClient(name = "SAAS-CORE-MICROSERVICE", path = "/core/platform/account/account", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface AccountClient {


    /**
     * 新增账号
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<AccountVO> add(@RequestBody AccountAddDto addDto);


    /**
    * 删除账号
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<AccountVO> delete(@PathVariable("id") Long id);

    /**
    * 更新账号
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<AccountVO> update(@PathVariable("id") Long id, @RequestBody AccountEditDto editDto);

    /**
    * 根据ID查询账号
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<AccountVO> get(@PathVariable("id") Long id);

    /**
    * 查询账号列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<AccountVO>> list(@RequestBody PageSearchRequest<AccountCondition> pageSearchRequest);

}
