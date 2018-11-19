package net.aicoder.speedcloud.business.env.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.env.domain.Machine;
import net.aicoder.speedcloud.business.env.dto.MachineAddDto;
import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import net.aicoder.speedcloud.business.env.dto.MachineEditDto;
import net.aicoder.speedcloud.business.env.service.MachineService;
import net.aicoder.speedcloud.business.env.valid.MachineValidator;
import net.aicoder.speedcloud.business.env.vo.MachineVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理服务器
 * @author icode
 */
@Api(description = "服务器", tags = "Machine")
@RestController
@RequestMapping(value = "/speedcloud/env/machine")
public class MachineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineController.class);


	@Autowired
	private MachineService machineService;


	@Autowired
	private MachineValidator machineValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(machineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增服务器
	 * @param machineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增服务器", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public MachineVO add(@RequestBody @Valid MachineAddDto machineAddDto){
		Machine machine = new Machine();
		BeanUtils.copyProperties(machineAddDto, machine);

		machineService.add(machine);

		return  initViewProperty(machine);
	}

	/**
	 * 删除服务器,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除服务器", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete machine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			machineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新服务器
	 * @param machineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产服务器(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	MachineVO update(@RequestBody @Valid MachineEditDto machineEditDto, @PathVariable Long id){
		Machine machine = new Machine();
		BeanUtils.copyProperties(machineEditDto, machine);
		machine.setId(id);
		machineService.merge(machine);

		MachineVO vo = initViewProperty(machine);
		return  vo;
	}

	/**
	 * 根据ID查询服务器
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询服务器", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  MachineVO get(@PathVariable Long id) {

		Machine machine = machineService.find(id);

		MachineVO vo = initViewProperty(machine);
		return vo;
	}

	/**
	 * 查询服务器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询服务器列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<MachineVO> list(@RequestBody PageSearchRequest<MachineCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<Machine> page = machineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<MachineVO> voList = new ArrayList<>();
		for(Machine machine : page.getContent()){
			voList.add(initViewProperty(machine));
		}

		PageContent<MachineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出服务器列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出服务器列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(MachineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<MachineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<MachineVO> content = this.list(pageSearchRequest);

        List<MachineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(MachineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("name" ,"名称");
            headMap.put("ipAddress" ,"IP地址");
            headMap.put("port" ,"端口");

        String title = new String("服务器");
        String fileName = new String(("服务器_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private MachineVO initViewProperty(Machine machine){

	    MachineVO vo = new MachineVO();
        BeanUtils.copyProperties(machine, vo);


	    //初始化其他对象
        return vo;


	}


}
