package net.aicoder.devp.business.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.devp.business.product.domain.DevpPrdPrdline;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineCondition;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineEditDto;
import net.aicoder.devp.business.product.service.DevpPrdPrdlineService;
import net.aicoder.devp.business.product.valid.DevpPrdPrdlineValidator;
import net.aicoder.devp.business.product.vo.DevpPrdPrdlineVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理产品线定义
 * @author icode
 */
@Api(description = "产品线定义", tags = "DevpPrdPrdline")
@RestController
@RequestMapping(value = "/product/devpPrdPrdline")
public class DevpPrdPrdlineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPrdlineController.class);


	@Autowired
	private DevpPrdPrdlineService devpPrdPrdlineService;


	@Autowired
	private DevpPrdPrdlineValidator devpPrdPrdlineValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpPrdPrdlineValidator);
	}

	/**
	 * 新增产品线定义
	 * @param devpPrdPrdlineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品线定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdPrdlineVO add(@RequestBody @Valid DevpPrdPrdlineAddDto devpPrdPrdlineAddDto){
		DevpPrdPrdline devpPrdPrdline = new DevpPrdPrdline();
		BeanUtils.copyProperties(devpPrdPrdlineAddDto, devpPrdPrdline);

		devpPrdPrdlineService.add(devpPrdPrdline);

		return  initViewProperty(devpPrdPrdline);
	}

	/**
	 * 删除产品线定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品线定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdPrdline :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdPrdlineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品线定义
	 * @param devpPrdPrdlineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品线定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpPrdPrdlineVO update(@RequestBody @Valid DevpPrdPrdlineEditDto devpPrdPrdlineEditDto, @PathVariable Long id){
		DevpPrdPrdline devpPrdPrdline = new DevpPrdPrdline();
		BeanUtils.copyProperties(devpPrdPrdlineEditDto, devpPrdPrdline);
		devpPrdPrdline.setId(id);
		devpPrdPrdlineService.merge(devpPrdPrdline);

		DevpPrdPrdlineVO vo = initViewProperty(devpPrdPrdline);
		return  vo;
	}

	/**
	 * 根据ID查询产品线定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品线定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpPrdPrdlineVO get(@PathVariable Long id) {

		DevpPrdPrdline devpPrdPrdline = devpPrdPrdlineService.find(id);

		DevpPrdPrdlineVO vo = initViewProperty(devpPrdPrdline);
		return vo;
	}

	/**
	 * 查询产品线定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品线定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdPrdlineVO> list(@RequestBody PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpPrdPrdline> page = devpPrdPrdlineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpPrdPrdlineVO> voList = new ArrayList<>();
		for(DevpPrdPrdline devpPrdPrdline : page.getContent()){
			voList.add(initViewProperty(devpPrdPrdline));
		}

		PageContent<DevpPrdPrdlineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpPrdPrdlineVO initViewProperty(DevpPrdPrdline devpPrdPrdline){
	    DevpPrdPrdlineVO vo = new DevpPrdPrdlineVO();

        BeanUtils.copyProperties(devpPrdPrdline, vo);

	    //初始化其他对象
        return vo;
	}


}
