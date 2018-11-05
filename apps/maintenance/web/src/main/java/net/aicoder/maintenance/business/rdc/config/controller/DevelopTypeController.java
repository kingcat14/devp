package net.aicoder.maintenance.business.rdc.config.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.maintenance.business.rdc.config.domain.DevelopType;
import net.aicoder.maintenance.business.rdc.config.dto.DevelopTypeAddDto;
import net.aicoder.maintenance.business.rdc.config.dto.DevelopTypeCondition;
import net.aicoder.maintenance.business.rdc.config.dto.DevelopTypeEditDto;
import net.aicoder.maintenance.business.rdc.config.service.DevelopTypeService;
import net.aicoder.maintenance.business.rdc.config.valid.DevelopTypeValidator;
import net.aicoder.maintenance.business.rdc.config.vo.DevelopTypeVO;

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
 * 管理开发模式
 * @author icode
 */
@Api(description = "开发模式", tags = "DevelopType")
@RestController
@RequestMapping(value = "/rdc/config/developType")
public class DevelopTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevelopTypeController.class);


	@Autowired
	private DevelopTypeService developTypeService;


	@Autowired
	private DevelopTypeValidator developTypeValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(developTypeValidator);
	}

	/**
	 * 新增开发模式
	 * @param developTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增开发模式", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevelopTypeVO add(@RequestBody @Valid DevelopTypeAddDto developTypeAddDto){
		DevelopType developType = new DevelopType();
		BeanUtils.copyProperties(developTypeAddDto, developType);

		developTypeService.add(developType);

		return  initViewProperty(developType);
	}

	/**
	 * 删除开发模式,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除开发模式", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete developType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			developTypeService.delete(String.valueOf(id));
		}

	}

	/**
	 * 更新开发模式
	 * @param developTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产开发模式(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevelopTypeVO update(@RequestBody @Valid DevelopTypeEditDto developTypeEditDto, @PathVariable String id){
		DevelopType developType = new DevelopType();
		BeanUtils.copyProperties(developTypeEditDto, developType);
		developType.setId(id);
		developTypeService.merge(developType);

		DevelopTypeVO vo = initViewProperty(developType);
		return  vo;
	}

	/**
	 * 根据ID查询开发模式
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询开发模式", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevelopTypeVO get(@PathVariable String id) {

		DevelopType developType = developTypeService.find(id);

		DevelopTypeVO vo = initViewProperty(developType);
		return vo;
	}

	/**
	 * 查询开发模式列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询开发模式列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevelopTypeVO> list(@RequestBody PageSearchRequest<DevelopTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<DevelopType> page = developTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevelopTypeVO> voList = new ArrayList<>();
		for(DevelopType developType : page.getContent()){
			voList.add(initViewProperty(developType));
		}

		PageContent<DevelopTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevelopTypeVO initViewProperty(DevelopType developType){
	    DevelopTypeVO vo = new DevelopTypeVO();

        BeanUtils.copyProperties(developType, vo);

	    //初始化其他对象
        return vo;
	}


}
