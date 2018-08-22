package net.aicoder.maintenance.business.product.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdProductCondition;
import net.aicoder.devp.business.product.dto.DevpPrdProductEditDto;
import net.aicoder.devp.business.product.vo.DevpPrdProductVO;
import net.aicoder.devp.business.sys.dto.DevpSysCmpCondition;
import net.aicoder.devp.business.sys.vo.DevpSysCmpVO;
import net.aicoder.maintenance.business.product.product.service.DevpPrdProductRibbonService;
import net.aicoder.maintenance.business.product.product.valid.DevpPrdProductValidator;
import net.aicoder.maintenance.business.product.sys.service.DevpSysCmpRibbonService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 管理产品定义
 * @author icode
 */
@Api(description = "产品定义", tags = "DevpPrdProduct")
@RestController
@RequestMapping(value = "/product/devpPrdProduct")
public class DevpPrdProductTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductTreeController.class);


	@Autowired
	private DevpPrdProductRibbonService devpPrdProductRibbonService;

	@Autowired
	DevpPrdProductValidator devpPrdProductValidator;

	@Autowired
	private DevpSysCmpRibbonService devpSysCmpRibbonService;


	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpPrdProductValidator);
	}



	/**
	 * 查询产品及组件
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品定义列表", httpMethod = "POST")
	@PostMapping("/tree")
	public PageContent<DevpPrdProductVO> tree(@RequestBody PageSearchRequest<DevpPrdProductCondition> pageSearchRequest){


		PageContent<DevpPrdProductVO> pageContent = devpPrdProductRibbonService.list(pageSearchRequest);

		for(DevpPrdProductVO vo : pageContent.getContent()){
			vo.setDevpSysCmpList(this.findCmpForProduct(vo.getId()));
		}
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}


	public List<DevpSysCmpVO> findCmpForProduct(Long productId){

		DevpSysCmpCondition cmpCondition = new DevpSysCmpCondition();
		cmpCondition.setPrdRid(productId);

		PageSearchRequest<DevpSysCmpCondition> pageSearchRequest = new PageSearchRequest<>();
		pageSearchRequest.setPage(0);
		pageSearchRequest.setLimit(Integer.MAX_VALUE);
		pageSearchRequest.setSearchCondition(cmpCondition);

		List<DevpSysCmpVO> result = new ArrayList<>();
		HashMap<Long, DevpSysCmpVO> voHashMap = new HashMap<>();
		List<DevpSysCmpVO> devpSysCmpVOList = devpSysCmpRibbonService.list(pageSearchRequest).getContent();

		for(DevpSysCmpVO vo : devpSysCmpVOList){
			voHashMap.put(vo.getId(), vo);
		}
		for(DevpSysCmpVO vo : devpSysCmpVOList){
			if(vo.getParentRid() == null){
				result.add(vo);
			}else if(voHashMap.containsKey(vo.getId())){
				DevpSysCmpVO parent = voHashMap.get(vo.getId());
				if(CollectionUtils.isEmpty(parent.getDevpSysCmpList())){
					parent.setDevpSysCmpList(new ArrayList<>());
				}
				parent.getDevpSysCmpList().add(vo);
			}
		}

		return result;
	}


}
