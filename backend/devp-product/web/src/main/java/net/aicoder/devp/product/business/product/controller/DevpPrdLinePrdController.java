package net.aicoder.devp.product.business.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import net.aicoder.devp.product.business.product.domain.DevpPrdLinePrd;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdLinePrdEditDto;
import net.aicoder.devp.product.business.product.service.DevpPrdLinePrdService;
import net.aicoder.devp.product.business.product.valid.DevpPrdLinePrdValidator;
import net.aicoder.devp.product.business.product.vo.DevpPrdLinePrdVO;

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
 * 管理产品所属产品线定义
 * @author icode
 */
@RestController
@RequestMapping(value = "/product/devpPrdLinePrd")
public class DevpPrdLinePrdController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdLinePrdController.class);


	@Autowired
	private DevpPrdLinePrdService devpPrdLinePrdService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DevpPrdLinePrdValidator());
	}

	/**
	 * 新增产品所属产品线定义
	 * @param devpPrdLinePrdAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdLinePrdVO add(@RequestBody @Valid DevpPrdLinePrdAddDto devpPrdLinePrdAddDto){
		DevpPrdLinePrd devpPrdLinePrd = new DevpPrdLinePrd();
		BeanUtils.copyProperties(devpPrdLinePrdAddDto, devpPrdLinePrd);

		devpPrdLinePrdService.add(devpPrdLinePrd);

		return  initViewProperty(devpPrdLinePrd);
	}

	/**
	 * 删除产品所属产品线定义,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdLinePrd :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdLinePrdService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品所属产品线定义
	 * @param devpPrdLinePrdEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	DevpPrdLinePrdVO update(@RequestBody @Valid DevpPrdLinePrdEditDto devpPrdLinePrdEditDto, @PathVariable Long id){
		DevpPrdLinePrd devpPrdLinePrd = new DevpPrdLinePrd();
		BeanUtils.copyProperties(devpPrdLinePrdEditDto, devpPrdLinePrd);
		devpPrdLinePrd.setId(id);
		devpPrdLinePrdService.merge(devpPrdLinePrd);

		DevpPrdLinePrdVO vo = initViewProperty(devpPrdLinePrd);
		return  vo;
	}

	/**
	 * 根据ID查询产品所属产品线定义
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  DevpPrdLinePrdVO get(@PathVariable Long id) {

		DevpPrdLinePrd devpPrdLinePrd = devpPrdLinePrdService.find(id);

		DevpPrdLinePrdVO vo = initViewProperty(devpPrdLinePrd);
		return vo;
	}

	/**
	 * 查询产品所属产品线定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DevpPrdLinePrdVO> list(@RequestBody PageSearchRequest<DevpPrdLinePrdCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpPrdLinePrd> page = devpPrdLinePrdService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpPrdLinePrdVO> voList = new ArrayList<>();
		for(DevpPrdLinePrd devpPrdLinePrd : page.getContent()){
			voList.add(initViewProperty(devpPrdLinePrd));
		}

		PageContent<DevpPrdLinePrdVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpPrdLinePrdVO initViewProperty(DevpPrdLinePrd devpPrdLinePrd){
	    DevpPrdLinePrdVO vo = new DevpPrdLinePrdVO();

        BeanUtils.copyProperties(devpPrdLinePrd, vo);

	    //初始化其他对象
        return vo;
	}




}
