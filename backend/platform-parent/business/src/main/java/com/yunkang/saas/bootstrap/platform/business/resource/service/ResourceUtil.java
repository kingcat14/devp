package com.yunkang.saas.bootstrap.platform.business.resource.service;

import com.yunkang.saas.bootstrap.platform.business.resource.vo.ResourceTreeNode;
import com.yunkang.saas.bootstrap.platform.business.resource.domain.Resource;
import com.yunkang.saas.bootstrap.platform.business.resource.vo.ResourceVO;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * 资源工具类
 */

public class ResourceUtil {

    /**
     * 根据parentId,index排序
     * @param resourceList
     */
    public static void sortResourceList(List<Resource> resourceList){
        Collections.sort(resourceList, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {

                o1.setOrderIndex(o1.getOrderIndex()==null?0:o1.getOrderIndex());
                o2.setOrderIndex(o2.getOrderIndex()==null?0:o2.getOrderIndex());
                //先按父节点ID排序
                if(o1.getParentCode().compareTo(o2.getParentCode()) != 0){
                    return o1.getParentCode().compareTo(o2.getParentCode());
                }

                //如果父节点ID一样。比较排序
                if( o1.getOrderIndex().compareTo(o2.getOrderIndex()) != 0){
                    return  o1.getOrderIndex().compareTo(o2.getOrderIndex());
                }
                //如果排序相等，则比较Code
                o1.setCode(o1.getCode()==null?0:o1.getCode());
                o2.setCode(o2.getCode()==null?0:o2.getCode());
                return o1.getCode().compareTo(o2.getCode());
            }
        });
    }

    /**
     * 转换成树
     * @param resourceList
     * @return
     */
    public static List<ResourceTreeNode> convert(List<Resource> resourceList){

        sortResourceList(resourceList);

        HashMap<String, ResourceTreeNode> hashMap = new HashMap<>();
        List<ResourceTreeNode> allResource = new ArrayList<>();
        List<ResourceTreeNode> result = new ArrayList<>();

        for(Resource resource : resourceList){
            ResourceVO vo = new ResourceVO();
            BeanUtils.copyProperties(resource, vo);

            ResourceTreeNode resourceTreeNode = new ResourceTreeNode(vo);

            if(vo.getParentCode()== -1){
                result.add(resourceTreeNode);
            }
            allResource.add(resourceTreeNode);
            hashMap.put(resourceTreeNode.getAppCode()+"-"+resourceTreeNode.getCode(), resourceTreeNode);
        }

        for(ResourceTreeNode node : allResource){
            if(hashMap.containsKey(node.getAppCode()+"-"+node.getParentCode())){
                hashMap.get(node.getAppCode()+"-"+node.getParentCode()).addChild(node);
            }
        }

        return result;
    }
}
