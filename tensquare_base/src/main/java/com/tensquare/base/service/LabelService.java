package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基础微服务-业务逻辑处理层
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存标签数据
     *
     * @param label
     */
    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 删除
     */
    public void deleteById(String labelId) {
        labelDao.deleteById(labelId);
    }

    /**
     * 改
     */
    public void updateById(Label label, String labelId) {
        label.setId(labelId);
        labelDao.save(label);
        //1.查询
        //2.如果存在 则更新
        //3.如果不存在 则报错
    }

    /**
     * 查询所有
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据id查询
     */
    public Label findById(String labelId) {
        return labelDao.findById(labelId).get();
    }

    /**
     * 根据条件查询标签信息
     *
     * @param map
     * @return
     */
    public List<Label> search(Map map) {


       /* Optional<T> findOne(@Nullable Specification<T> var1);   根据条件查询单个对象
        List<T> findAll(@Nullable Specification<T> var1);  根据条件查询集合
        Page<T> findAll(@Nullable Specification<T> var1, Pageable var2); 根据条件分页查询集合
        List<T> findAll(@Nullable Specification<T> var1, Sort var2);  根据条件和排序查询集合
        long count(@Nullable Specification<T> var1);   根据条件进行统计*/

        //匿名类方式拼接参数
       /* Specification<Label> specification = new Specification<Label>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //toPredicate:拼接具体的条件
                //root   根对象  用此对象获取属性 拼接条件
                //criteriaQuery  顶级查询
                //criteriaBuilder 构建查询条件
                ///定义一个集合存放条件
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(map.get("labelname"))) {
                    Predicate p1 = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + map.get("labelname") + "%");//labelname like
                    predicateList.add(p1);
                }

                if (!StringUtils.isEmpty(map.get("state"))) {
                    Predicate p2 = criteriaBuilder.equal(root.get("state").as(String.class), map.get("state"));//labelname like
                    predicateList.add(p2);
                }
                if(predicateList ==null || predicateList.size() ==0){
                    return null;
                }
                //定一个数组对象类型  最终list就转成此数组类型
                *//*Predicate[] predicates = new Predicate[predicateList.size()];
                //将predicateList转Predicate数组对象
                Predicate[] predicates1 = predicateList.toArray(predicates);
                //最终通过and 将条件拼接进行查询
                return criteriaBuilder.and(predicates1);*//*

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };*/

        Specification<Label> specification = getSpecification(map);
        List<Label> labelList = labelDao.findAll(specification);
        return labelList;
    }


    /**
     * 抽取公共条件查询的代码
     */

    public Specification<Label> getSpecification(Map map){
        return new Specification<Label>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //toPredicate:拼接具体的条件
                //root   根对象  用此对象获取属性 拼接条件
                //criteriaQuery  顶级查询
                //criteriaBuilder 构建查询条件
                ///定义一个集合存放条件
                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(map.get("labelname"))) {
                    Predicate p1 = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + map.get("labelname") + "%");//labelname like
                    predicateList.add(p1);
                }

                if (!StringUtils.isEmpty(map.get("state"))) {
                    Predicate p2 = criteriaBuilder.equal(root.get("state").as(String.class), map.get("state"));//labelname like
                    predicateList.add(p2);
                }
                if(predicateList ==null || predicateList.size() ==0){
                    return null;
                }
                //定一个数组对象类型  最终list就转成此数组类型
                /*Predicate[] predicates = new Predicate[predicateList.size()];
                //将predicateList转Predicate数组对象
                Predicate[] predicates1 = predicateList.toArray(predicates);
                //最终通过and 将条件拼接进行查询
                return criteriaBuilder.and(predicates1);*/

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     *条件分页查询
     * @param map
     * @param page
     * @param size
     * @return
     */
    public Page<Label> searchByPage(Map map, int page, int size) {
        Specification<Label> specification = getSpecification(map);
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(specification, pageable);
    }
}
