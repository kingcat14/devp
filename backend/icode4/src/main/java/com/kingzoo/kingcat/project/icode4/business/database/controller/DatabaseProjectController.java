package com.kingzoo.kingcat.project.icode4.business.database.controller;

import com.kingzoo.kingcat.commons.framework.spring.controller.PageContent;
import com.kingzoo.kingcat.commons.framework.spring.data.PageRequest;
import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.database.controller.vo.DatabaseProjectAddRequest;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseProject;
import com.kingzoo.kingcat.project.icode4.business.database.service.DatabaseProjectService;
import com.kingzoo.kingcat.project.icode4.business.database.valid.DatabaseProjectValidator;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseProjectCondition;
import com.kingzoo.kingcat.project.icode4.business.database.vo.DatabaseProjectVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理数据库项目
 * @author icode
 */
@RestController
@RequestMapping(value = "/database/databaseProject")
public class DatabaseProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseProjectController.class);


	@Autowired
	private DatabaseProjectService databaseProjectService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DatabaseProjectValidator());
	}

	/**
	 * 新增数据库项目
	 * @param databaseProjectAddRequest
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DatabaseProjectVO add(@RequestBody @Valid DatabaseProjectAddRequest databaseProjectAddRequest){
		DatabaseProject databaseProject = new DatabaseProject();
		BeanUtils.copyProperties(databaseProjectAddRequest, databaseProject);

		databaseProjectService.add(databaseProject);

		return  initViewProperty(databaseProject);
	}

	/**
	 * 删除数据库项目,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete databaseProject :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			databaseProjectService.delete(id);
		}

	}

	/**
	 * 更新数据库项目
	 * @param databaseProject
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public DatabaseProject update(@RequestBody @Valid DatabaseProject databaseProject, @PathVariable String id){
		databaseProject.setId(id);
		databaseProjectService.merge(databaseProject);

		initViewProperty(databaseProject);
		return  databaseProject;
	}

	/**
	 * 根据ID查询数据库项目
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public DatabaseProject get(@PathVariable String id) {

		DatabaseProject databaseProject = databaseProjectService.find(id);

		initViewProperty(databaseProject);
		return databaseProject;
	}

	/**
	 * 查询数据库项目列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DatabaseProjectVO> list(@RequestBody PageSearchRequest<DatabaseProjectCondition> pageSearchRequest){


		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		Page<DatabaseProject> page = databaseProjectService.find(pageRequest, pageSearchRequest.getSearchCondition());

		List<DatabaseProjectVO> voList = new ArrayList<>();
		for(DatabaseProject databaseProject : page.getContent()){
			voList.add(initViewProperty(databaseProject));
		}

		PageContent<DatabaseProjectVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DatabaseProjectVO initViewProperty(DatabaseProject databaseProject){
	    DatabaseProjectVO vo = new DatabaseProjectVO();

        BeanUtils.copyProperties(databaseProject, vo);

	    //初始化其他对象
        return vo;
	}




}
