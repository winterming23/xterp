package com.sxt.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.mapper.DeptMapper;
import com.sxt.sys.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  部门服务实现类
 * </p>
 *
 * @author
 * @since
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
	
	@Override
	public Dept getById(Serializable id) {
		return super.getById(id);
	}

	//更新
	@Override
	public boolean updateById(Dept entity) {
		return super.updateById(entity);
	}
	//删除
	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}
	
	@Override
	public boolean save(Dept entity) {
		return super.save(entity);
	}

}
