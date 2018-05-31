package net.aicoder.devp.maintenance.business.product.product.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.product.DevpPrdProductRibbon;
import net.aicoder.devp.product.client.product.result.DevpPrdProductPageResult;
import net.aicoder.devp.product.client.product.result.DevpPrdProductResult;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdProductEditDto;
import net.aicoder.devp.product.business.product.vo.DevpPrdProductVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpPrdProductRibbonService")
public class DevpPrdProductRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdProductRibbonService.class);


	@Autowired
	private DevpPrdProductRibbon devpPrdProductRibbon;


	public DevpPrdProductVO add(DevpPrdProductAddDto addDto){
		DevpPrdProductResult result = devpPrdProductRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpPrdProductResult result = devpPrdProductRibbon.delete(id);
	}
	public DevpPrdProductVO merge(Long id, DevpPrdProductEditDto editDto){
		DevpPrdProductResult result = devpPrdProductRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpPrdProductVO find(Long id){
		DevpPrdProductResult result = devpPrdProductRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpPrdProductVO> list(PageSearchRequest<DevpPrdProductCondition> pageSearchRequest) {
		DevpPrdProductPageResult result = devpPrdProductRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
