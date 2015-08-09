package personal.deon.framework.core.service;


import cn.gd.thinkjoy.modules.persistence.SearchFilter;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import personal.deon.framework.core.entity.AbsEntity;
import personal.deon.framework.core.repository.GenericDao;
import personal.deon.framework.core.service.ShiroDbRealm.ShiroUser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Service层基类，实现一些公有的操作
 *
 */
@Transactional(readOnly = true)
public abstract class GenericService<E extends AbsEntity> {
    private Class<E> genericClassE = getClazz();
    protected Logger logger = LoggerFactory.getLogger(getClazz());


    @SuppressWarnings("unchecked")
	protected Class<E> getClazz() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            return (Class<E>) ((ParameterizedType) ((Class) genericSuperclass).getGenericSuperclass()).getActualTypeArguments()[0];
        } else if (genericSuperclass instanceof ParameterizedType) {
            return (Class<E>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        }

        return null;
    }

    /**
     * 查找子类中注入的DAO并且赋值给相应的父DAO以便进行相应的操作
     *
     * @return
     */
    public abstract GenericDao<E> getGenericDao();

    public E get(final String id) {
       return getGenericDao().findOne(id);
    }

    /**
     * 保存实体
     */
    @Transactional(readOnly = false)
    public void save(E entity) {
        getGenericDao().save(entity);
    }

    /**
     * 保存实体
     */
    @Transactional(readOnly = false)
    public void save(List<E> entity) {
        getGenericDao().save(entity);
    }

    /**
     * 删除实体
     */
    @Transactional(readOnly = false)
    public void delete(final String id) {
        getGenericDao().delete(id);
    }

    /**
     * 获取全部的实体
     */
    public List<E> getAll() {
        return (List<E>) getGenericDao().findAll();
    }

    /**
     * 分页查询的实现
     */
    public Page<E> search(Map<String, Object> searchParams, cn.gd.thinkjoy.modules.web.pojo.PageRequest pageRequest) {
        PageRequest springPageRequest = SearchFilter.buildPageRequest(pageRequest);
        Specification<E> spec = SearchFilter.builderSpecification(searchParams, this.genericClassE);
        Page<E> pages = getGenericDao().findAll(spec, springPageRequest);
        /*
         * 设置Page参数方便页面获取
		 */
        pageRequest.setTotalCount(pages.getTotalElements());
        return pages;
    }
    /**获取登录用户*/
    public ShiroUser loginUser(){
    	Object obj = SecurityUtils.getSubject().getPrincipal();
    	if(obj == null)
    		return null;
    	return (ShiroUser)obj;
    }
}