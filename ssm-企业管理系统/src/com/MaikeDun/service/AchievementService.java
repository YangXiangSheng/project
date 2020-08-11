package com.MaikeDun.service;

import java.sql.Date;
import java.util.List;

import com.MaikeDun.bean.Achievement;
import com.MaikeDun.bean.CountAchievement;
import com.MaikeDun.bean.ShopSale;

public interface AchievementService {
	public List<Achievement> selectAchievementByDate(Date date);
	public List<Achievement> selectAchievement();
	public List<CountAchievement> countAchievement(CountAchievement cam);
	public List<CountAchievement> SelectAchievementcount(CountAchievement cam);
	public int addAchievement(Achievement ach);
	public int addAchievementByAuto(Achievement ach);
	public void updateAchievement(Achievement ach);
	public void updateAchievementByAuto(Achievement ach);
	public int deleteAchievement(Date date);
	public List<ShopSale> selectShopSaleByDate(String date);
}
