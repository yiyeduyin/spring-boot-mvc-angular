/**   
* @Title: BaseService.java 
* @Package com.hdp.customerservice.service 
* @Description: TODO
* @author new12304508_163_com   
* @date 2015年6月17日 下午1:56:36 
* @version V1.0   
*/
package com.cmiracle.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface BaseService<E, PK extends Serializable> {

	/**
	 * 根据id获取对象
	 * 
	 * @param id
	 * @return Entity
	 */
	public E get(PK id);

	/**
	 * 取得所有Entity
	 * 
	 * @return List<E>
	 */
	public List<E> getAllList();

	/**
	 * 取得对象总数量
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	public Long getTotalCount();

	/**
	 * 新增Entity
	 * 
	 * @param entity
	 */
	public E save(E entity);

	/**
	 * 保存修改Entity
	 * 
	 * @param entity
	 */
	public E update(E entity);

	/**
	 * 新增或者保存修改Entity
	 * 
	 * @param entity
	 */
	public E saveOrUpdate(E entity);

	/**
	 * 根据属性值判断对象是否存在
	 * 
	 * @param entity
	 * @return 存在-true, 不存在-false
	 */
	public boolean existById(PK id);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void delete(PK id);

	/**
	 * 根据多个id删除
	 * 
	 * @param ids
	 * @throws DataAccessException
	 */
	public void deleteByIds(List<PK> ids);

}
