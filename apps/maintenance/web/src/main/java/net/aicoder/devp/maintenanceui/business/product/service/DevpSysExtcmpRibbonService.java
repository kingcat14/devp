package net.aicoder.devp.maintenanceui.business.product.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.client.DevpSysExtcmpRibbon;
import net.aicoder.devp.product.business.product.client.result.DevpSysExtcmpPageResult;
import net.aicoder.devp.product.business.product.client.result.DevpSysExtcmpResult;
import net.aicoder.devp.product.business.product.domain.DevpSysExtcmp;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpAddDto;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpCondition;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpEditDto;
import net.aicoder.devp.product.business.product.vo.DevpSysExtcmpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysExtcmpRibbonService")
public class DevpSysExtcmpRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysExtcmp.class);


	@Autowired
	private DevpSysExtcmpRibbon devpSysExtcmpRibbon;


	public DevpSysExtcmpVO add(DevpSysExtcmpAddDto addDto){
		DevpSysExtcmpResult result = devpSysExtcmpRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysExtcmpResult result = devpSysExtcmpRibbon.delete(id);
	}
	public DevpSysExtcmpVO merge(Long id, DevpSysExtcmpEditDto editDto){
		DevpSysExtcmpResult result = devpSysExtcmpRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysExtcmpVO find(Long id){
		DevpSysExtcmpResult result = devpSysExtcmpRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysExtcmpVO> list(PageSearchRequest<DevpSysExtcmpCondition> pageSearchRequest) {
		DevpSysExtcmpPageResult result = devpSysExtcmpRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
