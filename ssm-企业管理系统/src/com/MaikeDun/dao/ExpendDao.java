package com.MaikeDun.dao;

import java.sql.Date;
import java.util.List;

import com.MaikeDun.bean.*;
public interface ExpendDao {
	public List<Expend> selectExpendByDate(Date date);
	public List<Expend> selectExpendByDatestr(String date);
	public List<Expend> selectExpend();
	public List<CountExpend> countExpend(CountExpend ce);
	public List<CountExpend> SelectExpendcount(CountExpend ce);
	public int addExpend(Expend ce);
	public void updateExpend(Expend ce);
	public int deleteExpend(Date date);
}
