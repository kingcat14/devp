package com.kingzoo.kingcat.project.icode4.business.database.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.database.controller.vo.DatabaseTableInfoAddRequest;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseTableInfo;
import com.kingzoo.kingcat.project.icode4.business.database.service.DatabaseTableInfoService;
import com.kingzoo.kingcat.project.icode4.business.database.valid.DatabaseTableInfoValidator;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseTableInfoCondition;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseTableInfoVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理数据库表信息
 * @author icode
 */
@RestController
@RequestMapping(value = "/database/databaseTableInfo")
public class DatabaseTableInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseTableInfoController.class);


	@Autowired
	private DatabaseTableInfoService databaseTableInfoService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DatabaseTableInfoValidator());
	}

	/**
	 * 新增数据库表信息
	 * @param databaseTableInfoAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DatabaseTableInfoVO add(@RequestBody @Valid DatabaseTableInfoAddRequest databaseTableInfoAddRequest){
		DatabaseTableInfo databaseTableInfo = new DatabaseTableInfo();
		BeanUtils.copyProperties(databaseTableInfoAddRequest, databaseTableInfo);

		databaseTableInfoService.add(databaseTableInfo);

		return  initViewProperty(databaseTableInfo);
	}

	/**
	 * 删除数据库表信息,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete databaseTableInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			databaseTableInfoService.delete(id);
		}

	}

	/**
	 * 更新数据库表信息
	 * @param databaseTableInfo
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public DatabaseTableInfo update(@RequestBody @Valid DatabaseTableInfo databaseTableInfo, @PathVariable String id){
		databaseTableInfo.setId(id);
		databaseTableInfoService.merge(databaseTableInfo);

		initViewProperty(databaseTableInfo);
		return  databaseTableInfo;
	}

	/**
	 * 根据ID查询数据库表信息
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public DatabaseTableInfo get(@PathVariable String id) {

		DatabaseTableInfo databaseTableInfo = databaseTableInfoService.find(id);

		initViewProperty(databaseTableInfo);
		return databaseTableInfo;
	}

	/**
	 * 查询数据库表信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DatabaseTableInfoVO> list(@RequestBody PageSearchRequest<DatabaseTableInfoCondition> pageSearchRequest){


		DatabaseTableInfoCondition tableModuleCondition = pageSearchRequest.getSearchCondition();
		if(tableModuleCondition == null || StringUtils.isEmpty(tableModuleCondition.getConnectionId())){

			LOGGER.debug("no connection id, return empty result");
			return new PageContent<>(new ArrayList<DatabaseTableInfoVO>(), 0);
		}

		List<DatabaseTableInfo> tableInfoList = databaseTableInfoService.findTableInfoList(tableModuleCondition.getConnectionId());

		List<DatabaseTableInfoVO> voList = new ArrayList<>();
		for(DatabaseTableInfo databaseTableInfo : tableInfoList){
			voList.add(initViewProperty(databaseTableInfo));
		}

		PageContent<DatabaseTableInfoVO> pageContent = new PageContent<>(voList, CollectionUtils.size(voList));
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DatabaseTableInfoVO initViewProperty(DatabaseTableInfo databaseTableInfo){
	    DatabaseTableInfoVO vo = new DatabaseTableInfoVO();

        BeanUtils.copyProperties(databaseTableInfo, vo);

	    //初始化其他对象
        return vo;
	}




}
