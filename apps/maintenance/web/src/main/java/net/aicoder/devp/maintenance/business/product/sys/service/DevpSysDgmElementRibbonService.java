package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysDgmElementRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysDgmElementPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysDgmElementResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysDgmElementVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDgmElementRibbonService")
public class DevpSysDgmElementRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDgmElementRibbonService.class);


	@Autowired
	private DevpSysDgmElementRibbon devpSysDgmElementRibbon;


	public DevpSysDgmElementVO add(DevpSysDgmElementAddDto addDto){
		DevpSysDgmElementResult result = devpSysDgmElementRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysDgmElementResult result = devpSysDgmElementRibbon.delete(id);
	}
	public DevpSysDgmElementVO merge(Long id, DevpSysDgmElementEditDto editDto){
		DevpSysDgmElementResult result = devpSysDgmElementRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysDgmElementVO find(Long id){
		DevpSysDgmElementResult result = devpSysDgmElementRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysDgmElementVO> list(PageSearchRequest<DevpSysDgmElementCondition> pageSearchRequest) {
		DevpSysDgmElementPageResult result = devpSysDgmElementRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
