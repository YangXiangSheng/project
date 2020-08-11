package com.MaikeDun.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MaikeDun.bean.Achievement;
import com.MaikeDun.bean.CountAchievement;
import com.MaikeDun.bean.ShopSale;
import com.MaikeDun.dao.AchievementDao;
import com.MaikeDun.service.AchievementService;
@Service
public class AchievementServiceImpl implements AchievementService {
	@Autowired
	private AchievementDao achDao;
	@Override
	public List<Achievement> selectAchievementByDate(Date date) {
		// TODO Auto-generated method stub
		return achDao.selectAchievementByDate(date);
	}

	@Override
	public List<Achievement> selectAchievement() {
		// TODO Auto-generated method stub
		return achDao.selectAchievement();
	}
	public List<CountAchievement> countAchievement(CountAchievement cam){
		return achDao.countAchievement(cam);
	}
	public List<CountAchievement> SelectAchievementcount(CountAchievement cam){
		return achDao.SelectAchievementcount(cam);
	}
	public int addAchievement(Achievement ach) {
		return achDao.addAchievement(ach);
	}
	public int addAchievementByAuto(Achievement ach) {
		return achDao.addAchievementByAuto(ach);
	}
	public void updateAchievement(Achievement ach) {
		achDao.updateAchievement(ach);
	}
	public void updateAchievementByAuto(Achievement ach) {
		achDao.updateAchievementByAuto(ach);
	}
	public int deleteAchievement(Date date) {
		return achDao.deleteAchievement(date);
	}
	public List<ShopSale> selectShopSaleByDate(String date){
		return achDao.selectShopSaleByDate(date);
	}
}
