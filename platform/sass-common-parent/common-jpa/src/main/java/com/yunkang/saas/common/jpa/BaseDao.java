package com.yunkang.saas.common.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;


/**
 *
 * @author icode
 */
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> , JpaSpecificationExecutor<T> {


}
