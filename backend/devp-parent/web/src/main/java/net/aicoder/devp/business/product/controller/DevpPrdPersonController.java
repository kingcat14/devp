package net.aicoder.devp.business.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.devp.business.product.domain.DevpPrdPerson;
import net.aicoder.devp.business.product.dto.DevpPrdPersonAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdPersonCondition;
import net.aicoder.devp.business.product.dto.DevpPrdPersonEditDto;
import net.aicoder.devp.business.product.service.DevpPrdPersonService;
import net.aicoder.devp.business.product.valid.DevpPrdPersonValidator;
import net.aicoder.devp.business.product.vo.DevpPrdPersonVO;

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
 * 管理产品干系人
 * @author icode
 */
@Api(description = "产品干系人", tags = "DevpPrdPerson")
@RestController
@RequestMapping(value = "/product/devpPrdPerson")
public class DevpPrdPersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPersonController.class);


	@Autowired
	private DevpPrdPersonService devpPrdPersonService;


	@Autowired
	private DevpPrdPersonValidator devpPrdPersonValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpPrdPersonValidator);
	}

	/**
	 * 新增产品干系人
	 * @param devpPrdPersonAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品干系人", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdPersonVO add(@RequestBody @Valid DevpPrdPersonAddDto devpPrdPersonAddDto){
		DevpPrdPerson devpPrdPerson = new DevpPrdPerson();
		BeanUtils.copyProperties(devpPrdPersonAddDto, devpPrdPerson);

		devpPrdPersonService.add(devpPrdPerson);

		return  initViewProperty(devpPrdPerson);
	}

	/**
	 * 删除产品干系人,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品干系人", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdPerson :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdPersonService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品干系人
	 * @param devpPrdPersonEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品干系人(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpPrdPersonVO update(@RequestBody @Valid DevpPrdPersonEditDto devpPrdPersonEditDto, @PathVariable Long id){
		DevpPrdPerson devpPrdPerson = new DevpPrdPerson();
		BeanUtils.copyProperties(devpPrdPersonEditDto, devpPrdPerson);
		devpPrdPerson.setId(id);
		devpPrdPersonService.merge(devpPrdPerson);

		DevpPrdPersonVO vo = initViewProperty(devpPrdPerson);
		return  vo;
	}

	/**
	 * 根据ID查询产品干系人
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品干系人", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpPrdPersonVO get(@PathVariable Long id) {

		DevpPrdPerson devpPrdPerson = devpPrdPersonService.find(id);

		DevpPrdPersonVO vo = initViewProperty(devpPrdPerson);
		return vo;
	}

	/**
	 * 查询产品干系人列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品干系人列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdPersonVO> list(@RequestBody PageSearchRequest<DevpPrdPersonCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpPrdPerson> page = devpPrdPersonService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpPrdPersonVO> voList = new ArrayList<>();
		for(DevpPrdPerson devpPrdPerson : page.getContent()){
			voList.add(initViewProperty(devpPrdPerson));
		}

		PageContent<DevpPrdPersonVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpPrdPersonVO initViewProperty(DevpPrdPerson devpPrdPerson){
	    DevpPrdPersonVO vo = new DevpPrdPersonVO();

        BeanUtils.copyProperties(devpPrdPerson, vo);

	    //初始化其他对象
        return vo;
	}


}
