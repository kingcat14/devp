package com.yunkang.saas.platform.business.platform.security.service;


import com.yunkang.saas.platform.business.platform.security.domain.Account;
import com.yunkang.saas.platform.business.platform.security.domain.AccountPassword;
import com.yunkang.saas.platform.business.platform.security.domain.AccountRoleRelation;
import com.yunkang.saas.platform.business.platform.security.domain.RoleResourceRelation;
import com.yunkang.saas.platform.business.platform.security.dto.*;
import com.yunkang.saas.platform.business.resource.domain.Resource;
import com.yunkang.saas.platform.business.resource.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gonghongrui on 2017/7/13.
 */
@Service
public class AccountManageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountManageService.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountPasswordService accountPasswordService;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleResourceRelationService roleResourceRelationService;

	@Autowired
	private AccountRoleRelationService accountRoleRelationService;

	@Transactional
	public void add(Account account, AccountPassword accountPassword){
		accountService.add(account);


		accountPassword.setAccountId(account.getId());
		accountPassword.setPassword(passwordEncoder.encode(accountPassword.getPassword()));
		accountPassword.setWrongCount(0);
		accountPasswordService.add(accountPassword);
	}

	/**
	 * 更新密码
	 * @param updatePasswordRequest
	 * @return
	 */
	public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest){

		UpdatePasswordResponse response = new UpdatePasswordResponse();

		AccountPasswordCondition accountPasswordCondition = new AccountPasswordCondition();
		accountPasswordCondition.setAccountId(updatePasswordRequest.getAccountId());
		AccountPassword accountPassword = accountPasswordService.findOne(accountPasswordCondition);

		if(accountPassword == null){
			response.setSuccess(false);
			response.setMessage("找不到当前用户");
			LOGGER.error("can not find AccountPassword for accountId: {}", updatePasswordRequest.getAccountId());
			return response;
		}

		if(!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), accountPassword.getPassword())){
			response.setSuccess(false);
			response.setMessage("旧密码不匹配");
			return response;
		}

		accountPassword.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
		accountPassword.setWrongCount(0);
		accountPasswordService.merge(accountPassword);


		response.setSuccess(true);
		response.setMessage("密码已更新");
		return response;
	}

	/**
	 * 获取树形资源
	 * @param accountName
	 * @return
	 */
	public List<Resource> findAllResource(String accountName){

		//得到账号
		AccountCondition accountCondition = new AccountCondition();
		accountCondition.setName(accountName);
		Account account = accountService.findOne(accountCondition);

		//得到账号的所有角色
		AccountRoleRelationCondition accountRoleCondition = new AccountRoleRelationCondition();
		accountRoleCondition.setAccountId(account.getId());
		List<AccountRoleRelation> roleMappings = accountRoleRelationService.findAll(accountRoleCondition);


		//开始时准备获取所有资源
		List<Resource> resourceList = new ArrayList<>();

		Set<Long> resourceKeySet = new HashSet<>();

		for(AccountRoleRelation roleMapping : roleMappings){
			RoleResourceRelationCondition roleResourceCondition = new RoleResourceRelationCondition();
			roleResourceCondition.setRoleId(roleMapping.getRoleId());

			//
			List<RoleResourceRelation>  RoleResourceRelationList = roleResourceRelationService.findAll(roleResourceCondition);

			//得到所有额资源ID
			for(RoleResourceRelation RoleResourceRelation:RoleResourceRelationList){
				resourceKeySet.add(RoleResourceRelation.getResourceId());
			}

		}

		for(Long key : resourceKeySet){
			Resource resource = resourceService.find(key);
			if(resource!=null) {
				resourceList.add(resource);
			}
		}

		return resourceList;

//		resourceService.sortResourceList(resourceList);
//
//
//		resourceService.fillResourceTree(resourceList);
//
//
//		//只获取父节点为-1的节点
//		List<Resource> result = new ArrayList<>();
//		for(Resource resource:resourceList){
//			if(resource.getParentId().equals(Resource.TOP_NODE_ID)){
//				result.add(resource);
//			}
//		}
//		resourceService.sortResourceList(result);
//
//
//		return result;

	}

//	public List<Resource> findAllResourceTree(){
//
//		Sort sort = new Sort(Sort.Direction.ASC , Resource.PROPERTY_PARENT_ID);
//		sort.and(new Sort(Sort.Direction.ASC , Resource.PROPERTY_ORDER_INDEX));
//		List<Resource> resourceList = resourceService.findAll(null);
//
////		List<ResourceTreeNode> treeNodeList = new ArrayList<>();
////		for(Resource resource : resourceList){
////			ResourceVO vo = new ResourceVO();
////			BeanUtils.copyProperties(resource, vo);
////			treeNodeList.add(new ResourceTreeNode(vo));
////		}
//
//		//只获取父节点为-1的节点
//		List<Resource> result = new ArrayList<>();
//		for(Resource resource:resourceList){
//			if(resource.getParentId().equals(Resource.TOP_NODE_ID)){
//				result.add(resource);
//			}
//		}
//		resourceService.sortResourceList(result);
//
//
//		return result;
//	}


}
