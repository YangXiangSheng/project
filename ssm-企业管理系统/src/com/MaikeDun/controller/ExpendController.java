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

import com.MaikeDun.bean.CountExpend;
import com.MaikeDun.bean.Expend;
import com.MaikeDun.service.ExpendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class ExpendController {
	@Autowired
	private ExpendService expendService;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@RequestMapping("/listExpend")
	public String showAllExpend(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo) {
		Integer pageSize=10;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
		List<Expend> expend=expendService.selectExpend();
		PageInfo<Expend> pageInfo=new PageInfo<>(expend);
        session.setAttribute("pageInfo", pageInfo);
		return "listExpend.jsp?pageNo="+pageNo;
	}
	@RequestMapping("/listOneDateExpend")
	public String showOneDateExpend(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,@RequestParam(defaultValue="1",required=true) Integer pageNo,Model model) {
		
		if(date==null) {
			model.addAttribute("DateEmpty", "��ѡ������");
			return "listExpend.jsp?pageNo="+pageNo;
		}
		List<Expend> expend=expendService.selectExpendByDate(date);
		if(expend.size()==0) {
			model.addAttribute("NotExpend", "��������֧��");
			return "listExpend.jsp?pageNo="+pageNo;
		}
		request.setAttribute("Expend", expend);
		return "listOneExpend";
	}
	@RequestMapping("/countExpend")
	public String countExpend(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,@RequestParam(defaultValue="1",required=true) Integer pageNo,Model model) {
		if(date==null) {
			model.addAttribute("DateEmpty", "��ѡ��������");
			return "listExpend.jsp?pageNo="+pageNo;
		}
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		int month=calendar.get(Calendar.MONTH)+1;
		//System.out.print("��"+month);
		int year=calendar.get(Calendar.YEAR);
		//System.out.print("��"+year);
		CountExpend ce =new CountExpend();
		ce.setDate(date);
		ce.setMonth(month);
		ce.setYear(year);
		List<CountExpend> count=expendService.SelectExpendcount(ce);
		List<CountExpend> CountExpend =expendService.countExpend(ce);
		//System.out.print("size"+count.size());
		if(count.size()==0) {
			model.addAttribute("NotExpend", "����û��֧��");
			return "listExpend.jsp?pageNo="+pageNo;
		}
		request.setAttribute("CountExpend", CountExpend);
		return "countExpend";
	}
	@RequestMapping("addExpendInit")
	public String addExpendInit() {
		return "addExpend";
	}
	@RequestMapping("addExpend")
	public String addExpend(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,
			@RequestParam(value="expenditure",required=false)Double expenditure,@RequestParam(value="information",required=false)
	String information,Model model) {
		if(date==null) {
			model.addAttribute("DateEmpty", "��ѡ������");
		}
		if(expenditure==null) {
			model.addAttribute("expenditureEmptyError", "����д֧��������Ϊ��");
		}
		if(information.equals("")) {
			model.addAttribute("informationEmptyError", "����д��ע������Ϊ��");
		}
		if(date==null||expenditure==null||information.equals("")) {
			return "addExpend";
		}
		Expend expend=new Expend(date,expenditure,information);
		try {
		expendService.addExpend(expend);
		}catch(Exception e) {
			model.addAttribute("dateOnly", "����������֧����Ϣ������������");
			return "addExpend";
		}
		//System.out.print("1"+date+"2"+turnover+"3"+information);
		return "redirect:/listExpend";
	}
	@RequestMapping("/updateExpendInit")
	public String updateExpendInit(HttpServletRequest request,@RequestParam(value="date",required=false) Date date) {
		List<Expend> expend =expendService.selectExpendByDate(date);
		request.setAttribute("Expend", expend);
	return "updateExpend";
	}
	@RequestMapping("/updateExpend")
	public String updateExpend(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,
			@RequestParam(value="expenditure",required=false)Double expenditure,@RequestParam(value="information",required=false)
	String information,Model model) {
		if(date==null) {
			model.addAttribute("DateEmpty", "��ѡ������");
		}
		if(expenditure==null) {
			model.addAttribute("expenditureEmptyError", "����д֧��������Ϊ��");
		}
		if(information.equals("")) {
			model.addAttribute("informationEmptyError", "����д��ע������Ϊ��");
		}
		if(date==null||expenditure==null||information.equals("")) {
			return updateExpendInit(request,date);
		}
		Expend expend=new Expend(date,expenditure,information);
		expendService.updateExpend(expend);
		return "redirect:/listExpend";
	}
	@RequestMapping("/deleteExpend")
	public String deleteAchievement(HttpServletRequest request,@RequestParam(value="date",required=false) Date date,@RequestParam(defaultValue="1",required=true) Integer pageNo) {
		expendService.deleteExpend(date);
		return "redirect:/listExpend?pageNo="+pageNo;
	}
	}
