package com.MaikeDun.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MaikeDun.bean.CountExpend;
import com.MaikeDun.bean.Expend;
import com.MaikeDun.dao.ExpendDao;
import com.MaikeDun.service.ExpendService;
@Service
public class ExpendServiceImpl implements ExpendService {
	@Autowired
	private ExpendDao expendDao;
	@Override
	public List<Expend> selectExpendByDate(Date date) {
		// TODO Auto-generated method stub
		return expendDao.selectExpendByDate(date);
	}

	@Override
	public List<Expend> selectExpend() {
		// TODO Auto-generated method stub
		return expendDao.selectExpend();
	}

	@Override
	public List<CountExpend> countExpend(CountExpend ce) {
		// TODO Auto-generated method stub
		return expendDao.countExpend(ce);
	}

	@Override
	public List<CountExpend> SelectExpendcount(CountExpend ce) {
		// TODO Auto-generated method stub
		return expendDao.SelectExpendcount(ce);
	}

	@Override
	public int addExpend(Expend ce) {
		// TODO Auto-generated method stub
		return expendDao.addExpend(ce);
	}

	@Override
	public void updateExpend(Expend ce) {
		// TODO Auto-generated method stub
            expendDao.updateExpend(ce);
	}

	@Override
	public int deleteExpend(Date date) {
		// TODO Auto-generated method stub
		return expendDao.deleteExpend(date);
	}

}
