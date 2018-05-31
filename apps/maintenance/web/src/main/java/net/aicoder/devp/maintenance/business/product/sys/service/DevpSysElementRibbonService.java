package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysElementRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysElementPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElementResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElementVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysElementRibbonService")
public class DevpSysElementRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementRibbonService.class);


	@Autowired
	private DevpSysElementRibbon devpSysElementRibbon;


	public DevpSysElementVO add(DevpSysElementAddDto addDto){
		DevpSysElementResult result = devpSysElementRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysElementResult result = devpSysElementRibbon.delete(id);
	}
	public DevpSysElementVO merge(Long id, DevpSysElementEditDto editDto){
		DevpSysElementResult result = devpSysElementRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysElementVO find(Long id){
		DevpSysElementResult result = devpSysElementRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysElementVO> list(PageSearchRequest<DevpSysElementCondition> pageSearchRequest) {
		DevpSysElementPageResult result = devpSysElementRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
