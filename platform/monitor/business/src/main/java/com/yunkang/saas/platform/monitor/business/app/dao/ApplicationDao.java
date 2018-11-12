package com.yunkang.saas.platform.monitor.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 程序的数据库操作
 * @author icode
 */
@Repository("applicationDao")
public interface ApplicationDao extends BaseDao<Application, String>{

    int countByCode(String code);

    boolean existsByCode(String code);


    @Query("select code from Application where aliveCount > 0 ")
    List<String> findAliveCode();

}
