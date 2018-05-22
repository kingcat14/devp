package net.aicoder.devp.product.business.product.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.client.DevpPrdPrdlineRibbon;
import net.aicoder.devp.product.business.product.client.result.DevpPrdPrdlinePageResult;
import net.aicoder.devp.product.business.product.client.result.DevpPrdPrdlineResult;
import net.aicoder.devp.product.business.product.domain.DevpPrdPrdline;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineCondition;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineEditDto;
import net.aicoder.devp.product.business.product.vo.DevpPrdPrdlineVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpPrdPrdlineRibbonService")
public class DevpPrdPrdlineRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPrdline.class);


	@Autowired
	private DevpPrdPrdlineRibbon devpPrdPrdlineRibbon;


	public DevpPrdPrdlineVO add(DevpPrdPrdlineAddDto addDto){
		DevpPrdPrdlineResult result = devpPrdPrdlineRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpPrdPrdlineResult result = devpPrdPrdlineRibbon.delete(id);
	}
	public DevpPrdPrdlineVO merge(Long id, DevpPrdPrdlineEditDto editDto){
		DevpPrdPrdlineResult result = devpPrdPrdlineRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpPrdPrdlineVO find(Long id){
		DevpPrdPrdlineResult result = devpPrdPrdlineRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpPrdPrdlineVO> list(PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest) {
		DevpPrdPrdlinePageResult result = devpPrdPrdlineRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
