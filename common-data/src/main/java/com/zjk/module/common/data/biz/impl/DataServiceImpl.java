package com.zjk.module.common.data.biz.impl;

import com.zjk.module.common.data.biz.IDataService;
import com.zjk.module.common.data.constant.DataConstant;
import com.zjk.module.common.data.domain.DataEntity;
import com.zjk.module.common.data.exception.DataException;
import com.zjk.module.common.data.repository.IDataRepository;
import com.zjk.module.common.base.biz.impl.CommonServiceImpl;
import com.zjk.module.common.base.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataServiceImpl<T, ID extends Serializable> extends CommonServiceImpl implements IDataService<T, ID> {

	@Autowired
	protected IDataRepository<T, ID> repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T findOne(ID id) {
//		return repository.findOne(id);
		// spring data jpa 2.x改动
		return repository.findById(id).get();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return repository.findAll(example, pageable);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<T> findAll(Specification<T> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	@Transactional
	public <S extends T> S save(S po) {
		if (po instanceof DataEntity) {
			((DataEntity) po).setCreated(new Date());
			((DataEntity) po).setUpdated(new Date());
			// 默认启用
			if (null == ((DataEntity) po).getState()) {
				((DataEntity) po).setState(DataConstant.ENABLED);
			}
		}
		return repository.save(po);
	}

	@Override
	@Transactional
	public void delete(T po) {
		if (null != po) {
			repository.delete(po);
		}
	}

	@Override
	@Transactional
	public <S extends T> List<S> save(Iterable<S> pos) {
		List<S> list = new ArrayList<>();
		if (null == pos) {
			return list;
		}
		for (S po : pos) {
			list.add(save(po));
		}
		return list;
	}

	@Override
	@Transactional
	public <S extends T> List<S> saveOrDelete(Iterable<S> pos) {
		List<S> list = new ArrayList<>();
		if (null == pos) {
			return list;
		}
		for (S po : pos) {
			if (po instanceof DataEntity) {
				if (((DataEntity) po).getDeleteFlag()) {
					delete(po);
					continue;
				}
			}
			list.add(save(po));
		}
		return list;
	}

	@Override
	@Transactional
	public <S extends T> S updateState(S po, String operate) {
		if (po instanceof DataEntity) {
			if (DataConstant.LOCK.equalsIgnoreCase(operate)) {
				((DataEntity) po).setState(DataConstant.LOCKED);
			} else if (DataConstant.UNLOCK.equalsIgnoreCase(operate)) {
				((DataEntity) po).setState(DataConstant.ENABLED);
			} else {
				throw new BusinessException(DataException.D0001, new Object[]{operate});
			}
		}
		return save(po);
	}

}