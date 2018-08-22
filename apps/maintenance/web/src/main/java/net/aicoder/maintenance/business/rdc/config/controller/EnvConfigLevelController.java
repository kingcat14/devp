package net.aicoder.maintenance.business.rdc.config.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.maintenance.business.rdc.config.domain.EnvConfigLevel;
import net.aicoder.maintenance.business.rdc.config.dto.EnvConfigLevelAddDto;
import net.aicoder.maintenance.business.rdc.config.dto.EnvConfigLevelCondition;
import net.aicoder.maintenance.business.rdc.config.dto.EnvConfigLevelEditDto;
import net.aicoder.maintenance.business.rdc.config.service.EnvConfigLevelService;
import net.aicoder.maintenance.business.rdc.config.valid.EnvConfigLevelValidator;
import net.aicoder.maintenance.business.rdc.config.vo.EnvConfigLevelVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理环境级别
 * @author icode
 */
@Api(description = "环境级别", tags = "EnvConfigLevel")
@RestController
@RequestMapping(value = "/rdc/config/envConfigLevel")
public class EnvConfigLevelController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvConfigLevelController.class);


	@Autowired
	private EnvConfigLevelService envConfigLevelService;


	@Autowired
	private EnvConfigLevelValidator envConfigLevelValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(envConfigLevelValidator);
	}

	/**
	 * 新增环境级别
	 * @param envConfigLevelAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增环境级别", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public EnvConfigLevelVO add(@RequestBody @Valid EnvConfigLevelAddDto envConfigLevelAddDto){
		EnvConfigLevel envConfigLevel = new EnvConfigLevel();
		BeanUtils.copyProperties(envConfigLevelAddDto, envConfigLevel);

		envConfigLevelService.add(envConfigLevel);

		return  initViewProperty(envConfigLevel);
	}

	/**
	 * 删除环境级别,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除环境级别", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete envConfigLevel :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			envConfigLevelService.delete(String.valueOf(id));
		}

	}

	/**
	 * 更新环境级别
	 * @param envConfigLevelEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产环境级别(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	EnvConfigLevelVO update(@RequestBody @Valid EnvConfigLevelEditDto envConfigLevelEditDto, @PathVariable String id){
		EnvConfigLevel envConfigLevel = new EnvConfigLevel();
		BeanUtils.copyProperties(envConfigLevelEditDto, envConfigLevel);
		envConfigLevel.setId(id);
		envConfigLevelService.merge(envConfigLevel);

		EnvConfigLevelVO vo = initViewProperty(envConfigLevel);
		return  vo;
	}

	/**
	 * 根据ID查询环境级别
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询环境级别", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  EnvConfigLevelVO get(@PathVariable String id) {

		EnvConfigLevel envConfigLevel = envConfigLevelService.find(id);

		EnvConfigLevelVO vo = initViewProperty(envConfigLevel);
		return vo;
	}

	/**
	 * 查询环境级别列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询环境级别列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<EnvConfigLevelVO> list(@RequestBody PageSearchRequest<EnvConfigLevelCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<EnvConfigLevel> page = envConfigLevelService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EnvConfigLevelVO> voList = new ArrayList<>();
		for(EnvConfigLevel envConfigLevel : page.getContent()){
			voList.add(initViewProperty(envConfigLevel));
		}

		PageContent<EnvConfigLevelVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private EnvConfigLevelVO initViewProperty(EnvConfigLevel envConfigLevel){
	    EnvConfigLevelVO vo = new EnvConfigLevelVO();

        BeanUtils.copyProperties(envConfigLevel, vo);

	    //初始化其他对象
        return vo;
	}


}
