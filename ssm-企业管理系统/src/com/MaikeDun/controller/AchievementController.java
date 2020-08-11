package com.MaikeDun.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MaikeDun.bean.Achievement;
import com.MaikeDun.bean.CountAchievement;
import com.MaikeDun.bean.ShopSale;
import com.MaikeDun.service.AchievementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class AchievementController {
@Autowired
private AchievementService achService;
@InitBinder
protected void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
}
@RequestMapping("/listAchievement")
public String showAllAchievement(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo) {
	Integer pageSize=10;//每页显示记录数
	 PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
	List<Achievement> achievement=achService.selectAchievement();
	PageInfo<Achievement> pageInfo=new PageInfo<>(achievement);
	session.setAttribute("pageInfo", pageInfo);
	return "listAchievement.jsp?pageNo="+pageNo;
}
@RequestMapping("/listOneDateAchievement")
public String showOneDateAchievement(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,@RequestParam(defaultValue="1",required=true) Integer pageNo,Model model) {
	if(date==null) {
		model.addAttribute("DateEmpty", "请选择日期");
		return "listAchievement.jsp?pageNo="+pageNo;
	}
	List<Achievement> achievement=achService.selectAchievementByDate(date);
	if(achievement.size()==0) {
		model.addAttribute("NotAchievement", "该天暂无收入");
		return "listAchievement.jsp?pageNo="+pageNo;
	}else {
	request.setAttribute("Achievement", achievement);
	return "listOneAchievement";
	}
}
@RequestMapping("/countAchievement")
public String countAchievement(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,@RequestParam(defaultValue="1",required=true) Integer pageNo,Model model) {
	if(date==null) {
		model.addAttribute("DateEmpty", "请选择当月日期");
		return "listAchievement.jsp?pageNo="+pageNo;
	}
	 Calendar calendar = Calendar.getInstance();
	 calendar.setTime(date);
	int month=calendar.get(Calendar.MONTH)+1;
	//System.out.print("月"+month);
	int year=calendar.get(Calendar.YEAR);
	//System.out.print("年"+year);
	CountAchievement cam=new CountAchievement();
	cam.setDate(date);
	cam.setMonth(month);
	cam.setYear(year);
	List<CountAchievement> count=achService.SelectAchievementcount(cam);
	List<CountAchievement> countAchievement =achService.countAchievement(cam);
	//System.out.print("size"+countAchievement.size());
	if(count.size()==0) {
		model.addAttribute("NotAchievement", "本月没有收入");
		return "listAchievement.jsp?pageNo="+pageNo;
	}
	request.setAttribute("CountAchievement", countAchievement);
	return "countAchievement";
}
@RequestMapping("addAchievementInit")
public String addAchievementInit() {
	return "addAchievement";
}
@RequestMapping("addAchievement")
public String addAchievement(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,
		@RequestParam(value="turnover",required=false)Double turnover,@RequestParam(value="information",required=false)
String information,Model model) {
	if(date==null) {
		model.addAttribute("DateEmpty", "请选择日期");
	}
	if(turnover==null) {
		model.addAttribute("turnoverEmptyError", "请填写收入，不能为空");
	}
	if(information.equals("")) {
		model.addAttribute("informationEmptyError", "请填写备注，不能为空");
	}
	if(date==null||turnover==null||information.equals("")) {
		return "addAchievement";
	}
	Achievement achievement=new Achievement(date,turnover,information);
	try {
	achService.addAchievement(achievement);
	}catch(Exception e) {
		model.addAttribute("dateOnly", "该日期已有收入信息！请重新输入");
		return "addAchievement";
	}
	//System.out.print("1"+date+"2"+turnover+"3"+information);
	return "redirect:/listAchievement";
}
@RequestMapping("/updateAchievementInit")
public String updateAchievementInit(HttpServletRequest request,@RequestParam(value="date",required=false) Date date) {
	List<Achievement> achievement =achService.selectAchievementByDate(date);
	request.setAttribute("Achievement", achievement);
return "updateAchievement";
}
@RequestMapping("/updateAchievement")
public String updateAchievement(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,
		@RequestParam(value="turnover",required=false)Double turnover,@RequestParam(value="information",required=false)
String information,Model model) {
	if(date==null) {
		model.addAttribute("DateEmpty", "请选择日期");
	}
	if(turnover==null) {
		model.addAttribute("turnoverEmptyError", "请填写收入，不能为空");
	}
	if(information.equals("")) {
		model.addAttribute("informationEmptyError", "请填写备注，不能为空");
	}
	if(date==null||turnover==null||information.equals("")) {
		return updateAchievementInit(request,date);
	}
	Achievement achievement=new Achievement(date,turnover,information);
	achService.updateAchievement(achievement);
	return "redirect:/listAchievement";
}
@RequestMapping("/deleteAchievement")
public String deleteAchievement(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,@RequestParam(defaultValue="1",required=true) Integer pageNo) {
	String date1=request.getParameter("date");
	List<ShopSale> shopSaleList=achService.selectShopSaleByDate(date1);
	if(shopSaleList.size()>0) {
		return "redirect:manage/error/achievement_delete_error.jsp"+"?pageNo="+pageNo;
	}
	else {
	achService.deleteAchievement(date);
	return "redirect:/listAchievement?pageNo="+pageNo;
	}
}
}
