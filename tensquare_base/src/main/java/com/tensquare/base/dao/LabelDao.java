package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * spring data jpa
 * JpaRepository:基础CRUD操作
 * JpaSpecificationExecutor:复杂查询
 *
 */
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label>{
}
