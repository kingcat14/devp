package net.aicoder.devp.business.product.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.product.domain.DevpPrdProduct;
import net.aicoder.devp.business.product.dto.DevpPrdProductCondition;
import net.aicoder.devp.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdProductEditDto;
import net.aicoder.devp.business.product.service.DevpPrdProductService;
import net.aicoder.devp.business.product.valid.DevpPrdProductValidator;
import net.aicoder.devp.business.product.vo.DevpPrdProductVO;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理产品
 * @author icode
 */
@Api(description = "产品", tags = "DevpPrdProduct")
@RestController
@RequestMapping(value = "/product/devpPrdProduct")
public class DevpPrdProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductController.class);


	@Autowired
	private DevpPrdProductService devpPrdProductService;


	@Autowired
	private DevpPrdProductValidator devpPrdProductValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpPrdProductValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增产品
	 * @param devpPrdProductAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增产品", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpPrdProductVO add(@RequestBody @Valid DevpPrdProductAddDto devpPrdProductAddDto){
		DevpPrdProduct devpPrdProduct = new DevpPrdProduct();
		BeanUtils.copyProperties(devpPrdProductAddDto, devpPrdProduct);

		devpPrdProductService.add(devpPrdProduct);

		return  initViewProperty(devpPrdProduct);
	}

	/**
	 * 删除产品,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除产品", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpPrdProduct :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpPrdProductService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新产品
	 * @param devpPrdProductEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产产品(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpPrdProductVO update(@RequestBody @Valid DevpPrdProductEditDto devpPrdProductEditDto, @PathVariable Long id){
		DevpPrdProduct devpPrdProduct = new DevpPrdProduct();
		BeanUtils.copyProperties(devpPrdProductEditDto, devpPrdProduct);
		devpPrdProduct.setId(id);
		devpPrdProductService.merge(devpPrdProduct);

		DevpPrdProductVO vo = initViewProperty(devpPrdProduct);
		return  vo;
	}

	/**
	 * 根据ID查询产品
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询产品", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpPrdProductVO get(@PathVariable Long id) {

		DevpPrdProduct devpPrdProduct = devpPrdProductService.find(id);

		DevpPrdProductVO vo = initViewProperty(devpPrdProduct);
		return vo;
	}

	/**
	 * 查询产品列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpPrdProductVO> list(@RequestBody PageSearchRequest<DevpPrdProductCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpPrdProduct> page = devpPrdProductService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpPrdProductVO> voList = new ArrayList<>();
		for(DevpPrdProduct devpPrdProduct : page.getContent()){
			voList.add(initViewProperty(devpPrdProduct));
		}

		PageContent<DevpPrdProductVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出产品列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出产品列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(DevpPrdProductCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<DevpPrdProductCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<DevpPrdProductVO> content = this.list(pageSearchRequest);

        List<DevpPrdProductVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(DevpPrdProductVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("tid" ,"租户编号");
            headMap.put("etype" ,"etype");
            headMap.put("name" ,"产品名称");
            headMap.put("code" ,"产品代码");
            headMap.put("alias" ,"产品别名");
            headMap.put("description" ,"产品描述");
            headMap.put("type" ,"产品类型");
            headMap.put("stereotype" ,"构造型");
            headMap.put("scope" ,"范围");
            headMap.put("prdDept" ,"所属部门");
            headMap.put("prdOwner" ,"产品负责人");
            headMap.put("devManager" ,"开发负责人");
            headMap.put("opsManager" ,"维护负责人");
            headMap.put("bizLine" ,"业务线");
            headMap.put("bizManager" ,"业务代表");
            headMap.put("golive" ,"启用时间");
            headMap.put("majorCust" ,"主要客户");
            headMap.put("custManager" ,"客户代表");
            headMap.put("custUsage" ,"客户使用情况");
            headMap.put("acquisitionMode" ,"获取方式");
            headMap.put("acquisitionDesc" ,"获取方式说明");
            headMap.put("version" ,"版本");
            headMap.put("phase" ,"阶段");
            headMap.put("status" ,"状态");
            headMap.put("notes" ,"备注");
            headMap.put("recordState" ,"记录状态");
            headMap.put("createUcode" ,"创建用户代码");
            headMap.put("createUname" ,"创建用户姓名");
            headMap.put("cmodifyUcode" ,"修改用户代码");
            headMap.put("modifyUname" ,"修改用户姓名");

        String title = new String("产品");
        String fileName = new String(("产品_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private DevpPrdProductVO initViewProperty(DevpPrdProduct devpPrdProduct){

	    DevpPrdProductVO vo = new DevpPrdProductVO();
        BeanUtils.copyProperties(devpPrdProduct, vo);


	    //初始化其他对象
        return vo;


	}


}
