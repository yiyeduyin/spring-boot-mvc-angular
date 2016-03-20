/**   
* @Title: AbstractBaseServiceImpl.java 
* @Package com.hdp.customerservice.service 
* @Description: TODO
* @author new12304508_163_com   
* @date 2015年6月17日 下午2:07:07 
* @version V1.0   
*/
package com.cmiracle.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cmiracle.repository.BaseRepository;

public abstract class AbstractBaseServiceImpl<E, PK extends Serializable> implements BaseService<E, PK> {

	@Autowired
	protected BaseRepository<E, PK> repository;

	@Override
	public E get(PK id) {
		return repository.findOne(id);
	}

	@Override
	public List<E> getAllList() {
		return repository.findAll();
	}

	@Override
	public Long getTotalCount() {
		return repository.count();
	}

	@Override
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	public E update(E entity) {
		return repository.save(entity);
	}

	@Override
	public E saveOrUpdate(E entity) {
		return repository.save(entity);
	}

	@Override
	public boolean existById(PK id) {
		return repository.exists(id);
	}

	@Override
	public void delete(PK id) {
		repository.delete(id);
	}

	@Override
	public void deleteByIds(List<PK> ids) {
		repository.delete(repository.findAll(ids));
	}

}
