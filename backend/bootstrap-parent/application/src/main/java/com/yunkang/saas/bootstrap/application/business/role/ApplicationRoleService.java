package com.yunkang.saas.bootstrap.application.business.role;

import com.yunkang.saas.bootstrap.application.business.resource.service.ResourceConstants;
import com.yunkang.saas.bootstrap.business.platform.security.domain.RoleResourceRelation;
import com.yunkang.saas.bootstrap.business.platform.security.service.RoleResourceRelationService;
import com.yunkang.saas.bootstrap.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.business.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationRoleService {

    @Autowired
    private RoleResourceRelationService roleResourceRelationService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 检查某个角色是否包含了应用管理员的资源
     * @param roleId
     */
    public void checkApplicationManagerResource(Long roleId){
        Long[] resourceIds = {ResourceConstants.CODE_YYGL, ResourceConstants.CODE_YYGL_AQPZ, ResourceConstants.CODE_YYGL_JSQL, ResourceConstants.CODE_YYGL_ZHGL, ResourceConstants.CODE_CSPZ, ResourceConstants.CODE_CSPZ_YWCSPZ};

        for(Long code : resourceIds){
            Resource resource = resourceService.findByCode(code);
            int count = roleResourceRelationService.countByRoleIdAndResourceId(roleId, resource.getId());
            if(count <= 0){
                RoleResourceRelation relation = new RoleResourceRelation();
                relation.setResourceId(resource.getId());
                relation.setRoleId(roleId);
                roleResourceRelationService.add(relation);
            }
        }

    }

}
