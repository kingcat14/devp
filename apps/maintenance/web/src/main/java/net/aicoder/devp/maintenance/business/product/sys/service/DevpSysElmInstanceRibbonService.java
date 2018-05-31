package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysElmInstanceRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstancePageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstanceResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmInstanceVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysElmInstanceRibbonService")
public class DevpSysElmInstanceRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstanceRibbonService.class);


	@Autowired
	private DevpSysElmInstanceRibbon devpSysElmInstanceRibbon;


	public DevpSysElmInstanceVO add(DevpSysElmInstanceAddDto addDto){
		DevpSysElmInstanceResult result = devpSysElmInstanceRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysElmInstanceResult result = devpSysElmInstanceRibbon.delete(id);
	}
	public DevpSysElmInstanceVO merge(Long id, DevpSysElmInstanceEditDto editDto){
		DevpSysElmInstanceResult result = devpSysElmInstanceRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysElmInstanceVO find(Long id){
		DevpSysElmInstanceResult result = devpSysElmInstanceRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysElmInstanceVO> list(PageSearchRequest<DevpSysElmInstanceCondition> pageSearchRequest) {
		DevpSysElmInstancePageResult result = devpSysElmInstanceRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
