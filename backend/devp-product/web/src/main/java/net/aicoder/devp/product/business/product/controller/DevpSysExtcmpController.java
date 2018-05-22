package net.aicoder.devp.product.business.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import net.aicoder.devp.product.business.product.domain.DevpSysExtcmp;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpCondition;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpAddDto;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpEditDto;
import net.aicoder.devp.product.business.product.service.DevpSysExtcmpService;
import net.aicoder.devp.product.business.product.valid.DevpSysExtcmpValidator;
import net.aicoder.devp.product.business.product.vo.DevpSysExtcmpVO;

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
 * 管理产品包含的外部组件
 * @author icode
 */
@RestController
@RequestMapping(value = "/product/devpSysExtcmp")
public class DevpSysExtcmpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysExtcmpController.class);


	@Autowired
	private DevpSysExtcmpService devpSysExtcmpService;


    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new DevpSysExtcmpValidator());
	}

	/**
	 * 新增产品包含的外部组件
	 * @param devpSysExtcmpAddDto
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysExtcmpVO add(@RequestBody @Valid DevpSysExtcmpAddDto devpSysExtcmpAddDto){
		DevpSysExtcmp devpSysExtcmp = new DevpSysExtcmp();
		BeanUtils.copyProperties(devpSysExtcmpAddDto, devpSysExtcmp);

		devpSysExtcmpService.add(devpSysExtcmp);

		return  initViewProperty(devpSysExtcmp);
	}

	/**
	 * 删除产品包含的外部组件,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysExtcmp :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysExtcmpService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品包含的外部组件
	 * @param devpSysExtcmpEditDto
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	DevpSysExtcmpVO update(@RequestBody @Valid DevpSysExtcmpEditDto devpSysExtcmpEditDto, @PathVariable Long id){
		DevpSysExtcmp devpSysExtcmp = new DevpSysExtcmp();
		BeanUtils.copyProperties(devpSysExtcmpEditDto, devpSysExtcmp);
		devpSysExtcmp.setId(id);
		devpSysExtcmpService.merge(devpSysExtcmp);

		DevpSysExtcmpVO vo = initViewProperty(devpSysExtcmp);
		return  vo;
	}

	/**
	 * 根据ID查询产品包含的外部组件
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  DevpSysExtcmpVO get(@PathVariable Long id) {

		DevpSysExtcmp devpSysExtcmp = devpSysExtcmpService.find(id);

		DevpSysExtcmpVO vo = initViewProperty(devpSysExtcmp);
		return vo;
	}

	/**
	 * 查询产品包含的外部组件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@PostMapping("/list")
	public PageContent<DevpSysExtcmpVO> list(@RequestBody PageSearchRequest<DevpSysExtcmpCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysExtcmp> page = devpSysExtcmpService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysExtcmpVO> voList = new ArrayList<>();
		for(DevpSysExtcmp devpSysExtcmp : page.getContent()){
			voList.add(initViewProperty(devpSysExtcmp));
		}

		PageContent<DevpSysExtcmpVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysExtcmpVO initViewProperty(DevpSysExtcmp devpSysExtcmp){
	    DevpSysExtcmpVO vo = new DevpSysExtcmpVO();

        BeanUtils.copyProperties(devpSysExtcmp, vo);

	    //初始化其他对象
        return vo;
	}




}
