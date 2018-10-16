package net.aicoder.speedcloud.console.business.jointjs.controller;

import com.yunkang.saas.bootstrap.application.business.security.SaaSUtil;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.console.business.jointjs.domain.JointData;
import net.aicoder.speedcloud.console.business.jointjs.dto.JointDataCondition;
import net.aicoder.speedcloud.console.business.jointjs.dto.JointDataAddDto;
import net.aicoder.speedcloud.console.business.jointjs.dto.JointDataEditDto;
import net.aicoder.speedcloud.console.business.jointjs.service.JointDataService;
import net.aicoder.speedcloud.console.business.jointjs.valid.JointDataValidator;
import net.aicoder.speedcloud.console.business.jointjs.vo.JointDataVO;


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
 * 管理图形数据
 * @author icode
 */
@Api(description = "图形数据", tags = "JointData")
@RestController
@RequestMapping(value = "/console/jointjs/jointdata")
public class JointDataController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JointDataController.class);

	@Autowired
	private SaaSUtil saaSUtil;

	@Autowired
	private JointDataService jointDataService;



	@Autowired
	private JointDataValidator jointDataValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(jointDataValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增图形数据
	 * @param jointDataAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增图形数据", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public JointDataVO add(@RequestBody @Valid JointDataAddDto jointDataAddDto){
		JointData jointData = new JointData();
		BeanUtils.copyProperties(jointDataAddDto, jointData);

		jointDataService.add(jointData);

		return  initViewProperty(jointData);
	}

	/**
	 * 删除图形数据,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除图形数据", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete jointData :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			jointDataService.delete(id);
		}

	}

	/**
	 * 更新图形数据
	 * @param jointDataEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产图形数据(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	JointDataVO update(@RequestBody @Valid JointDataEditDto jointDataEditDto, @PathVariable String id){

		JointData jointData = new JointData();
		BeanUtils.copyProperties(jointDataEditDto, jointData);
		jointData.setId(id);
		jointData.setTid(saaSUtil.getAccount().getTenantId());
		jointDataService.merge(jointData);

		JointDataVO vo = initViewProperty(jointData);
		return  vo;
	}

	/**
	 * 根据ID查询图形数据
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询图形数据", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  JointDataVO get(@PathVariable String id) {

		JointData jointData = jointDataService.find(id);

		JointDataVO vo = initViewProperty(jointData);
		return vo;
	}

	/**
	 * 查询图形数据列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询图形数据列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<JointDataVO> list(@RequestBody PageSearchRequest<JointDataCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<JointData> page = jointDataService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<JointDataVO> voList = new ArrayList<>();
		for(JointData jointData : page.getContent()){
			voList.add(initViewProperty(jointData));
		}

		PageContent<JointDataVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出图形数据列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出图形数据列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(JointDataCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<JointDataCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<JointDataVO> content = this.list(pageSearchRequest);

        List<JointDataVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(JointDataVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("scheme" ,"方案ID");
            headMap.put("viewJson" ,"json");
            headMap.put("type" ,"类型");

        String title = new String("图形数据");
        String fileName = new String(("图形数据_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private JointDataVO initViewProperty(JointData jointData){

	    JointDataVO vo = new JointDataVO();
        BeanUtils.copyProperties(jointData, vo);


	    //初始化其他对象
        return vo;

	}


}

