package net.aicoder.speedcloud.business.pipeline.exec.controller;

import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.speedcloud.business.pipeline.exec.domain.Exec;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.ExecValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ExecVO;


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
 * 管理运行实例
 * @author icode
 */
@Api(description = "运行实例", tags = "Exec")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/exec")
public class ExecController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecController.class);


	@Autowired
	private ExecService execService;



	@Autowired
	private ExecValidator execValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(execValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例
	 * @param execAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public ExecVO add(@RequestBody @Valid ExecAddDto execAddDto){
		Exec exec = new Exec();
		BeanUtils.copyProperties(execAddDto, exec);

		execService.add(exec);

		return  initViewProperty(exec);
	}

	/**
	 * 删除运行实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete exec :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			execService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运行实例
	 * @param execEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运行实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	ExecVO update(@RequestBody @Valid ExecEditDto execEditDto, @PathVariable Long id){
		Exec exec = new Exec();
		BeanUtils.copyProperties(execEditDto, exec);
		exec.setId(id);
		execService.merge(exec);

		ExecVO vo = initViewProperty(exec);
		return  vo;
	}

	/**
	 * 根据ID查询运行实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  ExecVO get(@PathVariable Long id) {

		Exec exec = execService.find(id);

		ExecVO vo = initViewProperty(exec);
		return vo;
	}

	/**
	 * 查询运行实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<ExecVO> list(@RequestBody PageSearchRequest<ExecCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<Exec> page = execService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<ExecVO> voList = new ArrayList<>();
		for(Exec exec : page.getContent()){
			voList.add(initViewProperty(exec));
		}

		PageContent<ExecVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运行实例列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运行实例列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(ExecCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<ExecCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<ExecVO> content = this.list(pageSearchRequest);

        List<ExecVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(ExecVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("code" ,"编号");
            headMap.put("runnerId" ,"运行主体");
            headMap.put("runnerType" ,"运行类型");
            headMap.put("status" ,"运行状态");
            headMap.put("result" ,"运行结果");
            headMap.put("startTime" ,"开始时间");

        String title = new String("运行实例");
        String fileName = new String(("运行实例_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private ExecVO initViewProperty(Exec exec){

	    ExecVO vo = new ExecVO();
        BeanUtils.copyProperties(exec, vo);


	    //初始化其他对象
        return vo;

	}


}

