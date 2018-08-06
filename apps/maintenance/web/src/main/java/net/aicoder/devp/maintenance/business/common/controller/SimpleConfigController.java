package net.aicoder.devp.maintenance.business.common.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.security.local.business.service.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.maintenance.business.common.domain.SimpleConfig;
import net.aicoder.devp.maintenance.business.common.dto.SimpleConfigCondition;
import net.aicoder.devp.maintenance.business.common.dto.SimpleConfigAddDto;
import net.aicoder.devp.maintenance.business.common.dto.SimpleConfigEditDto;
import net.aicoder.devp.maintenance.business.common.service.SimpleConfigService;
import net.aicoder.devp.maintenance.business.common.valid.SimpleConfigValidator;
import net.aicoder.devp.maintenance.business.common.vo.SimpleConfigVO;

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
 * 管理通用配置
 * @author icode
 */
@Api(description = "通用配置", tags = "SimpleConfig")
@RestController
@RequestMapping(value = "/common/simpleConfig")
public class SimpleConfigController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConfigController.class);


	@Autowired
	private SimpleConfigService simpleConfigService;


	@Autowired
	private SimpleConfigValidator simpleConfigValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(simpleConfigValidator);
	}

	/**
	 * 新增通用配置
	 * @param simpleConfigAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增通用配置", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public SimpleConfigVO add(@RequestBody @Valid SimpleConfigAddDto simpleConfigAddDto){
		SimpleConfig simpleConfig = new SimpleConfig();
		BeanUtils.copyProperties(simpleConfigAddDto, simpleConfig);

		simpleConfigAddDto.setTid(SecurityUtil.getAccount().getTenantId());
		simpleConfigService.add(simpleConfig);

		return  initViewProperty(simpleConfig);
	}

	/**
	 * 删除通用配置,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除通用配置", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete simpleConfig :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			simpleConfigService.delete(Long.valueOf(id));
		}

	}

	/**
	 * 更新通用配置
	 * @param simpleConfigEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产通用配置(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	SimpleConfigVO update(@RequestBody @Valid SimpleConfigEditDto simpleConfigEditDto, @PathVariable Long id){
		SimpleConfig simpleConfig = new SimpleConfig();
		BeanUtils.copyProperties(simpleConfigEditDto, simpleConfig);
		simpleConfig.setId(id);
		simpleConfigService.merge(simpleConfig);

		SimpleConfigVO vo = initViewProperty(simpleConfig);
		return  vo;
	}

	/**
	 * 根据ID查询通用配置
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询通用配置", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  SimpleConfigVO get(@PathVariable Long id) {

		SimpleConfig simpleConfig = simpleConfigService.find(id);

		SimpleConfigVO vo = initViewProperty(simpleConfig);
		return vo;
	}

	/**
	 * 查询通用配置列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询通用配置列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<SimpleConfigVO> list(@RequestBody PageSearchRequest<SimpleConfigCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<SimpleConfig> page = simpleConfigService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<SimpleConfigVO> voList = new ArrayList<>();
		for(SimpleConfig simpleConfig : page.getContent()){
			voList.add(initViewProperty(simpleConfig));
		}

		PageContent<SimpleConfigVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private SimpleConfigVO initViewProperty(SimpleConfig simpleConfig){
	    SimpleConfigVO vo = new SimpleConfigVO();

        BeanUtils.copyProperties(simpleConfig, vo);

	    //初始化其他对象
        return vo;
	}


}
