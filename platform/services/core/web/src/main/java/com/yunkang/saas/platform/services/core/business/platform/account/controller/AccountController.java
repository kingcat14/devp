package com.yunkang.saas.platform.services.core.business.platform.account.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.services.core.business.platform.account.domain.Account;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountAddDto;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountCondition;
import com.yunkang.saas.platform.services.core.business.platform.account.dto.AccountEditDto;
import com.yunkang.saas.platform.services.core.business.platform.account.service.AccountService;
import com.yunkang.saas.platform.services.core.business.platform.account.valid.AccountValidator;
import com.yunkang.saas.platform.services.core.business.platform.account.vo.AccountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理账号
 * @author icode
 */
@Api(description = "账号", tags = "Account")
@RestController
@RequestMapping(value = "/core/platform/account/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);


	@Autowired
	private AccountService accountService;



	@Autowired
	private AccountValidator accountValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(accountValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增账号
	 * @param accountAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增账号", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "core.platform.account.account.add", count = true)
	public AccountVO add(@RequestBody @Valid AccountAddDto accountAddDto){
		Account account = new Account();
		BeanUtils.copyProperties(accountAddDto, account);

		accountService.add(account);

		return  initViewProperty(account);
	}

	/**
	 * 删除账号,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除账号", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "core.platform.account.account.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete account :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			accountService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新账号
	 * @param accountEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改账号(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "core.platform.account.account.update", count = true)
	public	AccountVO update(@RequestBody @Valid AccountEditDto accountEditDto, @PathVariable Long id){
		Account account = accountService.find(id);
		BeanUtils.copyProperties(accountEditDto, account);
		account.setId(id);
		accountService.merge(account);

		AccountVO vo = initViewProperty(account);
		return  vo;
	}

	/**
	 * 根据ID查询账号
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询账号", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "core.platform.account.account.get")
	public  AccountVO get(@PathVariable Long id) {

		Account account = accountService.find(id);
		if(account == null){
			throw new ResourceNotFoundException("找不到指定的账号，请检查ID");
		}
		AccountVO vo = initViewProperty(account);
		return vo;
	}

	/**
	 * 查询账号列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询账号列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "core.platform.account.account.list")
	public PageContent<AccountVO> list(@RequestBody PageSearchRequest<AccountCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<Account> page = accountService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<AccountVO> voList = new ArrayList<>();
		for(Account account : page.getContent()){
			voList.add(initViewProperty(account));
		}

		PageContent<AccountVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出账号列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出账号列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(AccountCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<AccountCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<AccountVO> content = this.list(pageSearchRequest);

        List<AccountVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(AccountVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("nickName" ,"昵称");
        headMap.put("name" ,"姓名");
        headMap.put("accountName" ,"账号");
        headMap.put("accountPassword" ,"密码");
        headMap.put("mobile" ,"手机号");
        headMap.put("email" ,"邮箱");
        headMap.put("enable" ,"已启用");
        headMap.put("expired" ,"已过期");
        headMap.put("locked" ,"已锁定");
        headMap.put("deleted" ,"已删除");

        String title = new String("账号");
        String fileName = new String(("账号_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private AccountVO initViewProperty(Account account){

	    AccountVO vo = new AccountVO();
        BeanUtils.copyProperties(account, vo);


	    //初始化其他对象
        return vo;

	}


}

