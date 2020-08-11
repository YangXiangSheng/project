package com.MaikeDun.controller;


import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.MaikeDun.dao.AchievementDao;
import com.MaikeDun.dao.ExpendDao;
import com.MaikeDun.dao.StaffsDao;
import com.MaikeDun.dao.UserDao;
import com.MaikeDun.bean.Achievement;
import com.MaikeDun.bean.Buy;
import com.MaikeDun.bean.Client;
import com.MaikeDun.bean.Expend;
import com.MaikeDun.bean.InviteCode;
import com.MaikeDun.bean.Order;
import com.MaikeDun.bean.Shop;
import com.MaikeDun.bean.ShopProduct;
import com.MaikeDun.bean.ShopSale;
import com.MaikeDun.bean.Staff;
import com.MaikeDun.bean.Staffs;
import com.MaikeDun.service.AchievementService;
import com.MaikeDun.service.ExpendService;
import com.MaikeDun.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserDao userMapper;
	@Autowired
	AchievementService achievementService;
	@Autowired
	StaffsDao staffsMapper;
	@Autowired
	ExpendDao expendMapper;
	@Autowired
	AchievementDao achievementMapper;
	@Autowired
	ExpendService expendService;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@RequestMapping("initlogin")
	public String initLogin() {
		return "login";
	}
	@RequestMapping("initregister")
	public String initRegister() {
		return "register";
	}
	@RequestMapping("/tologin")
	public  String login(Staff staff,Model model,HttpSession session,HttpServletRequest request) {
		int loginType = userService.login(staff.getName(), staff.getPassword());
		if(loginType == 2) {
			//��¼�ɹ������û���Ϣ���浽session������
			Staff message = userMapper.select(staff.getName());
			session.setAttribute("staff", message);
			//�ض�����ҳ��
			return "redirect:manage/index.jsp";
		}else if(loginType == 1) {
			model.addAttribute("msg", "������������µ�¼ ��");
			return "login";
		}
		model.addAttribute("msg", "���û������ڣ���δע����ǰ��ע�ᣡ");
		return "login";
	}
	@RequestMapping("/toregister")
	public String addStaff(Staff staff,Model model,HttpSession session,HttpServletRequest request) {
			String password = request.getParameter("password");//����
			String flag = request.getParameter("flag");//ע����
			String id = request.getParameter("name");
			int int_id = Integer.valueOf(id); //����
			List<Staffs> userList = staffsMapper.selectStaffById(int_id);
			if(userList.size()!=0) {//Ա�����д��û�
				InviteCode inviteCode = userMapper.selectcode(flag);
				if(inviteCode == null) {//û������ע����
					return "redirect:manage/error/register_error3.jsp";
				}else {
					staff.setId(id);
					staff.setName(userList.get(0).getStaffName());
					staff.setPassword(password);
					staff.setSex(userList.get(0).getSex());
					staff.setEmail(userList.get(0).getEmail());
					staff.setPhone(userList.get(0).getPhone());
					staff.setIdcard(userList.get(0).getIdcard());
					staff.setFlag(flag);
					staff.setImg(userList.get(0).getPhoto());
					int add = userService.register(staff);
					if(add == 100) {
						return "redirect:manage/error/register_error.jsp";
					}else {
						if(add > 0) {
							return "login";
						}else{
							return "register";
						}	
					}
				}
			}else {//Ա����û�д��û�
				return "redirect:manage/error/register_error2.jsp";
			}


		}
	@RequestMapping("/usermain")
	public String main() {
		return "main";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//���session
		session.invalidate();
		return "login";
	}
	//�޸�����
	@RequestMapping("updatepwd")
	public String updatePwd(Staff staff,HttpSession session,HttpServletRequest request) {
		String password = staff.getPassword(); 
		String newpwd = request.getParameter("newpwd");
		Staff staffs = (Staff)session.getAttribute("staff");
		String id = staffs.getId();
		if(password.equals(newpwd)) {
			int up = userService.updatepwd(id,password);			
			if(up>0) {
				//���session
				session.invalidate();
				return "updatepwd_success";
			}
		}
		return "error";
	}
	//�޸��û�ͷ��
	@RequestMapping("saveimg")
	public String saveImg(MultipartFile file,Staff staff, HttpSession session,HttpServletRequest request)throws IOException{
        /**
                 * �ϴ�ͼƬ
         */
        //ͼƬ�ϴ��ɹ��󣬽�ͼƬ�ĵ�ַд�����ݿ�
        String filePath = request.getSession().getServletContext().getRealPath("photo");;//����ͼƬ��·��,tomcat��������
        //��ȡԭʼͼƬ����չ��
        String originalFilename = file.getOriginalFilename();
        //�µ��ļ����֣�ʹ��uuid���������+ԭʼͼƬ���֣����������ظ�
        String newFileName = UUID.randomUUID()+originalFilename;
        //��װ�ϴ��ļ�λ�õ�ȫ·��������Ӳ��·��+�ļ���
        File targetFile = new File(filePath,newFileName); 
        //�ѱ����ļ��ϴ����Ѿ���װ�õ��ļ�λ�õ�ȫ·�����������targetFile
        file.transferTo(targetFile);
        //staff.setImg(newFileName);//�ļ������浽ʵ�����Ӧ������ 
        Staff staffs = (Staff)session.getAttribute("staff");
		String id = staffs.getId();
		staffs.setImg(newFileName);
		session.setAttribute("staff",staffs);
        /**
                * ����
         */
        userService.saveStaffImg(id,newFileName);
        return "redirect:manage/account_message.jsp"; //�ض��򵽲�ѯ	
	}
	//�޸��û���Ϣ
	@RequestMapping("updateaccount")
	public String updateAccount(Staff staff,HttpSession session,HttpServletRequest request) {
		Staff staffs = (Staff)session.getAttribute("staff");
		String email = staffs.getEmail();
		String phone = staffs.getPhone();
		String qq = request.getParameter("qq");
		String address = request.getParameter("address");
		String id = staffs.getId();
		int up = userService.updatemes(id, email, phone, address, qq);
		if(up>0) {
			Staff message = userMapper.select(staffs.getIdcard());
			session.setAttribute("staff", message);
			return "redirect:manage/account_message.jsp";
		}else {
			return "error";
		}	
	}
	//��Ӳ�Ʒ
	@RequestMapping("addshop")
	public String addShop(Shop shop,HttpServletRequest request) {
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String number = request.getParameter("number");
		String price = request.getParameter("price");
		try {
			int add = userService.addshop(s_id,s_name,number,price);
			return "redirect:manage/success/shop_update_success.jsp?pageNo=1";
		}catch(Exception e) {
			return "error";
		}
	}
	//�༭��Ʒ
	@RequestMapping("updateshop")
	public String updateShop(Shop shop,HttpServletRequest request) {
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String number = request.getParameter("number");
		String price = request.getParameter("price").trim();
		String pageNo = request.getParameter("pageNo").trim();
		System.out.println(price);
		int up = userService.updateshop(s_id, s_name, number,price);
		if(up>0) {
			return "redirect:manage/success/shop_update_success.jsp"+"?pageNo="+pageNo;
		}else {
			return "error";
		}
	}
	//ɾ����Ʒ
	@RequestMapping("deleteshop")
	public String deleteshop(Shop shop,HttpServletRequest request) {
		String s_id = request.getParameter("id").trim();
		String pageNo = request.getParameter("page").trim();
		int delete = userService.deleteshop(s_id);
		if(delete>0) {
			return "redirect:/shop_message?pageNo="+pageNo;
		}else {
			return "error";
		}		
	}
	//��ѯ��Ʒ
	@RequestMapping(value="searchshop")
	public String searchshop(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Shop> userList = userService.listByName(name);//��ȡ�����û���Ϣ
        PageInfo<Shop> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}				
    /**
        * ��ҳ�г���Ʒ
     */
    @RequestMapping(value="/shop_message",method=RequestMethod.GET)
    public String pageList(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Shop> userList = userService.list();//��ȡ�����û���Ϣ
        PageInfo<Shop> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_message.jsp?pageNo="+pageNo;
    }
    /*
     * shop_production������ϸ
     */
    @RequestMapping(value="/shop_production",method=RequestMethod.GET)
    public String pageList_production(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<ShopProduct> userList = userService.list_product();
        PageInfo<ShopProduct> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_production.jsp?pageNo="+pageNo;
    }
    //���
	@RequestMapping("addshopproduct")
	public String addShopProduct(ShopProduct shop,HttpServletRequest request) {
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String number = request.getParameter("number");
		String date = request.getParameter("date");
		int add = 0;
		List<Shop> userList = userMapper.selectShopById1(s_id);//�жϿ������û������Ʒ
		if(userList.isEmpty()&userList.size() == 0) { //Ϊ�վ�add���
			userService.addshop(s_id, s_name, number,null);	//�����棬��û�趨�۸�
			add = userService.addshopproduct(s_id,s_name,number,date);//����������¼
		}else { //�����Ϊ�գ���update shop
			userService.product_update_shop(s_id, number); //���ӿ������number
			add = userService.addshopproduct(s_id,userList.get(0).getS_name(),number,date);//��ֹ������Ʒ���ִ������ӵ��ǿ���е�����
		}
		if(add>0) {//����ɹ�
			return "redirect:manage/success/shopproduct_update_success.jsp?pageNo=1";
		}else {
			return "error";
		}
	}
	//�༭
	@RequestMapping("updateshopproduct")
	public String updateShopProduct(ShopProduct shop,HttpServletRequest request) {
		String id = request.getParameter("id");
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String number = request.getParameter("number");
		String date = request.getParameter("date");
		String old_number = request.getParameter("old_number");
		String pageNo = request.getParameter("pageNo").trim();
		int up = userService.updateshopproduct(id,s_id, s_name, number,date);
		userService.product_delete_shop(s_id, old_number); //ɾ��֮ǰ��¼����Ŀ��
		userService.product_update_shop(s_id, number); //�������ڼ�¼�Ŀ��
		if(up>0) {
			return "redirect:manage/success/shopproduct_update_success.jsp"+"?pageNo="+pageNo;
		}else {
			return "error";
		}
	}
	//ɾ��
	@RequestMapping("deleteshopproduct")
	public String deleteshopproduct(ShopProduct shop,HttpServletRequest request) {
		String pageNo = request.getParameter("page").trim();
		String id = request.getParameter("id").trim();
		String s_id = request.getParameter("s_id").trim();
		String number = request.getParameter("number").trim();
		String date = request.getParameter("date").trim();
		int delete = userService.deleteshopproduct(id,s_id,date);
		int de = userService.product_delete_shop(s_id, number);
		if(delete>0 & de>0) {
			return "redirect:/shop_production?pageNo="+pageNo;
		}else {
			return "error";
		}		
	}
	//��ѯ
	@RequestMapping("searchshopproduct")
	public String searchshopproduct(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request)
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<ShopProduct> userList = userService.listByNameProduct(name);//��ȡ�����û���Ϣ
        PageInfo<ShopProduct> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_production.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}
    /*
     * shop_production������ϸ
     */
    @RequestMapping(value="/shop_sale",method=RequestMethod.GET)
    public String pageList_sale(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<ShopSale> userList = userService.list_sale();
        PageInfo<ShopSale> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_sale.jsp?pageNo="+pageNo;
    }
  //���
	@RequestMapping("addshopsale")
  	public String addShopSale(ShopSale shop,HttpServletRequest request,@RequestParam(value="date",required=false) Date date1) throws ParseException {
  		double turnover;
  		String s_id = request.getParameter("s_id");
  		String number = request.getParameter("number");
  		String date = request.getParameter("date");
  		List<Shop> userList = userService.selectShopById1(s_id);//�жϿ������û������Ʒ
  		//System.out.print("����"+date1);
	   //System.out.print("����"+num);
  		//System.out.print("���"+turnover);
  		if(!userList.isEmpty()&userList.size()!= 0) {//����������Ʒ
	  	  		//�µ��޸�
	  	  		
	  	  		/**
	  	  		 * ���ۼ�¼Ԥ׼��
	  	  		 */if(Integer.parseInt(number)>Integer.parseInt(userList.get(0).getNumber())) {
			  	  		return "redirect:manage/error/shopsale_error.jsp?pageNo=1";
			  	  		}
			  	  		else {
			  	  		int add = userService.addshopsale(s_id,userList.get(0).getS_name(),number,date);//�������ۼ�¼	
	  	  		List<Achievement> achievementList=achievementService.selectAchievementByDate(date1);	
	  	  		double num=Double.parseDouble(number);
	  	  		double turnover1=Double.parseDouble(userList.get(0).getPrice())*num;
	  	  		if(add>0) {
			  	  			/*
			  	  			 *�������ۼ�¼��ͬʱ��������
			  	  		**
			  	  		*/
	  	  		userService.sale_update_shop(s_id, number);//ɾ�����
			            if(achievementList.size()==0) {//���ѡ��������û�����¼�����¼�¼���
			            	turnover=turnover1;
			            	Achievement achiecement=new Achievement(date1,turnover,null);
			  				achievementService.addAchievementByAuto(achiecement);
			  			}
			            else {//���ѡ���������������¼�����ھɼ�¼������
				  			turnover=turnover1+achievementList.get(0).getTurnover();
				  			Achievement achiecement=new Achievement(date1,turnover,null);
				  			achievementService.updateAchievementByAuto(achiecement);
			            }
			  	  			return "redirect:manage/success/shopsale_update_success.jsp?pageNo=1";
	  	  			}
			  	  		else {
	  	  				return "error";
	  	  			}	
			  	  		}
  		}else {//���û�����Ʒ����ת����ҳ��
  			return "redirect:manage/error/shopsale_error.jsp?pageNo=1";
  		}
  	}
  	//�༭
	@RequestMapping("updateshopsale")
  	public String updateShopSale(ShopSale shop,HttpServletRequest request) {
  		double turnover;
  		String id = request.getParameter("id");  		
  		String s_id = request.getParameter("s_id");
  		String s_name = request.getParameter("s_name");
  		String number = request.getParameter("number");
  		String date = request.getParameter("date");
  		String old_number = request.getParameter("old_number");
  		String pageNo = request.getParameter("pageNo").trim();
  		String date1=request.getParameter("old_date");
  		//ת�����ݵ���Ӧ��ʽ
  		Date new_date=java.sql.Date.valueOf(date);
  		Date old_date=java.sql.Date.valueOf(date1);
  		int int_old_number=Integer.parseInt(old_number);
  		int new_number=Integer.parseInt(number);
  		List<Shop> userList = userService.selectShopById1(s_id);//�жϿ������û������Ʒ
  		if(Integer.parseInt(number)>Integer.parseInt(userList.get(0).getNumber())+int_old_number) {
  	  		return "redirect:manage/error/shopsale_error.jsp?pageNo=1";
  	  		}
  	  		else {
  		//�������۱�ļ�¼
  		int up = userService.updateshopsale(id,s_id, s_name, number,date);
  		//Ӱ����
  		
  		List<Achievement> achievementList=achievementService.selectAchievementByDate(new_date);
  		if(up>0) {//�������۱�ɹ�
  			//��ѯ�����Ϣ
  			userService.sale_delete_shop(s_id, old_number); //���֮ǰ�����ۼ�¼ɾ���Ŀ��
  	  		userMapper.sale_update_shop(s_id, number); //��ȥ�������ۼ�¼�Ŀ��
  	  		//�����޸ĺ�����ڲ�ѯ��¼
  			if(date1.equals(date)) {//û���޸����ڵ����--new_date==old_date
	  			if(new_number==int_old_number) {//��������û�б仯�����
	  			}else {
	  				if(new_number>int_old_number) {//������������
	  					turnover=achievementList.get(0).getTurnover()+(new_number-int_old_number)*Double.parseDouble(userList.get(0).getPrice());
	  					Achievement achievement=new Achievement(new_date,turnover,null);
	  					achievementService.updateAchievementByAuto(achievement);
	  					//System.out.print("Ӫҵ��"+turnover);
	  				}
	  				else {//������������
	  					turnover=achievementList.get(0).getTurnover()-(int_old_number-new_number)*Double.parseDouble(userList.get(0).getPrice());
	  					Achievement achievement=new Achievement(new_date,turnover,null);
	  					achievementService.updateAchievementByAuto(achievement);
	  				}
	  			}
  	  		}else {//�����޸���
  				List<Achievement> achievementListOld=achievementService.selectAchievementByDate(old_date);
  				turnover=achievementListOld.get(0).getTurnover()-int_old_number*Double.parseDouble(userList.get(0).getPrice());
  				if(turnover==0) {
  					achievementService.deleteAchievement(old_date);
  				}
  				else {
  				//System.out.print("Ӫҵ��"+turnover+"xx"+(new_number-int_old_number));
  				Achievement achievement=new Achievement(old_date,turnover,null);
  				achievementService.updateAchievementByAuto(achievement);
  				}
  				if(achievementList.size()==0) {//������û�������¼ֱ������������
  					double turnover1=new_number*Double.parseDouble(userList.get(0).getPrice());
  					Achievement achiecementNew=new Achievement(new_date,turnover1,null);
  					achievementService.addAchievementByAuto(achiecementNew);
  				}
  				else {
  					double turnover1=achievementList.get(0).getTurnover()+new_number*Double.parseDouble(userList.get(0).getPrice());
  					Achievement achiecementNew=new Achievement(new_date,turnover1,null);
  					achievementService.updateAchievementByAuto(achiecementNew);
  				}
  			}
  			return "redirect:manage/success/shopsale_update_success.jsp"+"?pageNo="+pageNo;
  		}else {
  			return "error";
  		}
  	  		}
  	}
  	//ɾ��
 	@RequestMapping("deleteshopsale")
  	public String deleteshopsale(ShopSale shop,HttpServletRequest request) {
  		String pageNo = request.getParameter("page").trim();
  		String id = request.getParameter("id").trim();
  		String s_id = request.getParameter("s_id").trim();
  		String number = request.getParameter("number").trim();
  		String date = request.getParameter("date").trim();
  		Date date1=java.sql.Date.valueOf(date);//Stringת����Date
  		int new_number=Integer.parseInt(number);//Stringת��int
  		int delete = userService.deleteshopsale(id,s_id,date);
  		
  		List<Shop> userList = userService.selectShopById1(s_id);//��ѯ��Ʒ����
  		userService.sale_delete_shop(s_id, number);//shop�Ŀ������
  		List<Achievement> achievementList=achievementService.selectAchievementByDate(date1);//��ѯ����	
  		if(delete>0) {
  			double turnover=achievementList.get(0).getTurnover()-new_number*Double.parseDouble(userList.get(0).getPrice());
  			if(turnover==0) {
  				achievementService.deleteAchievement(date1);
  			}
  			else {
  			Achievement achievement=new Achievement(date1,turnover,null);
  			achievementService.updateAchievementByAuto(achievement);
  			}
  			return "redirect:/shop_sale?pageNo="+pageNo;
  		}else {
  			return "error";
  		}		
  	}
  	//��ѯ
  	@RequestMapping("searchshopsale")
  	public String searchshopsale(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
  			throws ServletException, IOException{
  		String name = request.getParameter("name").trim();
          Integer pageSize=7;//ÿҳ��ʾ��¼��
          //��ҳ��ѯ
          PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
          List<ShopSale> userList = userService.listByNameSale(name);//��ȡ�����û���Ϣ
          PageInfo<ShopSale> pageInfo=new PageInfo<>(userList);
          session.setAttribute("pageInfo", pageInfo);
          return "redirect:manage/shop_sale.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
  	}
    /*
     * order_message������ϸ
     */
    @RequestMapping(value="/order_message",method=RequestMethod.GET)
    public String pageList_order(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=1;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Order> userList = userService.list_order();
        PageInfo<Order> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/order_message.jsp?pageNo="+pageNo;
    }
    //���
  	@RequestMapping("addorder")
  	public String addOrder(Order order,HttpServletRequest request,MultipartFile file, HttpSession session) throws IOException{
  		String s_id = request.getParameter("s_id");
  		String o_number = request.getParameter("o_number");
  		String o_time = request.getParameter("o_time");
  		String c_id = request.getParameter("c_id");
  		 /**
  		  * ��ͬͼƬ
		 */
		//ͼƬ�ϴ��ɹ��󣬽�ͼƬ�ĵ�ַд�����ݿ�
  		 String filePath = request.getSession().getServletContext().getRealPath("photo");;//����ͼƬ��·��,tomcat��������
		//��ȡԭʼͼƬ����չ��
		String originalFilename = file.getOriginalFilename();
		//�µ��ļ����֣�ʹ��uuid���������+ԭʼͼƬ���֣����������ظ�
		String newFileName = UUID.randomUUID()+originalFilename;
		//��װ�ϴ��ļ�λ�õ�ȫ·��������Ӳ��·��+�ļ���
		File targetFile = new File(filePath,newFileName); 
		//�ѱ����ļ��ϴ����Ѿ���װ�õ��ļ�λ�õ�ȫ·�����������targetFile
		file.transferTo(targetFile);
		/**
		 * ����
		 */
  		int add = userService.addorder(s_id,o_number,o_time,newFileName,c_id,0);
  		if(add>0) {
  			return  "redirect:/order_message";
  		}else {
  			return "../error";
  		}
  	}
  	//ɾ��
  	@RequestMapping("deleteorder")
  	public String deleteorder(Order order,HttpServletRequest request) {
  		int id =  Integer.valueOf(request.getParameter("id").trim());
  		String pageNo = request.getParameter("page").trim();
  		int delete = userService.deleteorder(id);
  		if(delete>0) {
  			return "redirect:/order_message?pageNo="+pageNo;
  		}else {
  			return "error";
  		}		
  	}
  	//�༭
  	@RequestMapping("updateorder")
  	public String updateOrder(Order order,HttpServletRequest request,MultipartFile file, HttpSession session) throws IOException{
  		int o_id = Integer.valueOf(request.getParameter("o_id"));
  		String s_id = request.getParameter("s_id");
  		String o_number = request.getParameter("o_number");
  		String o_time = request.getParameter("o_time");
  		String pageNo = request.getParameter("pageNo");
  		 /**
  		  * ��ͬͼƬ
		 */
		//ͼƬ�ϴ��ɹ��󣬽�ͼƬ�ĵ�ַд�����ݿ�
  		 String filePath = request.getSession().getServletContext().getRealPath("photo");;//����ͼƬ��·��,tomcat��������
		//��ȡԭʼͼƬ����չ��
		String originalFilename = file.getOriginalFilename();
		//�µ��ļ����֣�ʹ��uuid���������+ԭʼͼƬ���֣����������ظ�
		String photo = UUID.randomUUID()+originalFilename;
		//��װ�ϴ��ļ�λ�õ�ȫ·��������Ӳ��·��+�ļ���
		File targetFile = new File(filePath,photo); 
		//�ѱ����ļ��ϴ����Ѿ���װ�õ��ļ�λ�õ�ȫ·�����������targetFile
		file.transferTo(targetFile);
		/**
		 * ����
		 */
  		int add = userService.updateorder(o_id,s_id,o_number,o_time,photo);
  		if(add>0) {
  			return  "redirect:/order_message?pageNo="+pageNo;
  		}else {
  			return "../error";
  		}
  	}
  	//��ѯ
	@RequestMapping("searchorder")
	public String searchorder(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request)
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=1;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Order> userList = userService.list_orderByName(name);
        PageInfo<Order> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/order_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}
	//���
	@RequestMapping("successorder")
	public String successorder(Order order,HttpServletRequest request, HttpSession session) {
		double turnover;
		int o_id = Integer.valueOf(request.getParameter("o_id").trim());
		String s_id = request.getParameter("s_id").trim();
		String pageNo = request.getParameter("pageNo");
		String number = request.getParameter("number");
		double num=Double.parseDouble(number);
		//��ȡ��ǰʱ��
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(now.getTime());
		Date date=java.sql.Date.valueOf(dateStr);
		//System.out.print("����"+date+date.getClass());
		Shop shop= userMapper.selectShopById(s_id);
		System.out.println(shop);
		//System.out.print("Ӫҵ��"+turnover1);
		if(shop != null) { //������Ʒ
			double turnover1=Double.parseDouble(shop.getPrice())*num;
			List<Achievement> achievementList=achievementService.selectAchievementByDate(date);
			if(Integer.valueOf(shop.getNumber())>Integer.valueOf(number)) {
				int ad = userService.addshopsale(s_id, shop.getS_name(), number,dateStr);
				int su = userService.updateorderflag(o_id,dateStr);
				if(ad >0) {
					/*
	  	  			 *��ɺ�ͬ��ͬʱ��������
	  	  		**
	  	  		*/
					String new_number=String.valueOf(Integer.valueOf(shop.getNumber())-Integer.valueOf(number));
					int ad1=userService.updateshop(s_id, shop.getS_name(), new_number, shop.getPrice());
					if(achievementList.size()==0) {
				           turnover=turnover1;
				            	Achievement achiecement=new Achievement(date,turnover,null);
				  				achievementService.addAchievementByAuto(achiecement);
				  			}
				            else {
				  			turnover=turnover1+achievementList.get(0).getTurnover();
				  			Achievement achiecement=new Achievement(date,turnover,null);
				  			achievementService.updateAchievementByAuto(achiecement);
				            }
					if(su > 0) {
						return "redirect:/order_message?pageNo="+pageNo;
					}else {
						return "../error";
					}
				}else {
					return "redirect:manage/error/success_error.jsp"+"?pageNo="+pageNo;
				}
		     }else {
					return "redirect:manage/error/success_error.jsp"+"?pageNo="+pageNo;
				}
		}else{
			return "redirect:manage/error/success_error.jsp?pageNo="+pageNo;
			}
	}
	/**
	 * �ͻ�����
	 */
	@RequestMapping(value="/client_message",method=RequestMethod.GET)
    public String pageList_client(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Client> userList = userService.list_client();
        PageInfo<Client> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/client_message.jsp?pageNo="+pageNo;
	}
	  //���
  	@RequestMapping("addclient")
  	public String addClient(Client client,HttpServletRequest request) {
  		String c_id = request.getParameter("c_id");
  		String c_name = request.getParameter("c_name");
  		String phone = request.getParameter("phone");
  		String address = request.getParameter("address");
  		int add = userService.addclient(c_id,c_name,phone,address);
  		if(add>0) {
  			return "redirect:manage/success/client_update_success.jsp?pageNo=1";
  		}else {
  			return "error";
  		}
  	}
  	//ɾ��
  	@RequestMapping("deleteclient")
  	public String deleteclient(Client client,HttpServletRequest request) {
  		String c_id =  request.getParameter("id").trim();
  		String pageNo = request.getParameter("page").trim();
  		int flag =0; //�ж���ɾ���ͻ��Ƿ���û��ɵĶ���
  		List<Order> userList = userMapper.selectAllOrderByName(c_id);
  		if(!userList.isEmpty()) {
  	  		for(int i = 0; i< userList.size();i++){
  	  			if(userList.get(i).getFlag() == 0) {
  	  				flag += 1;
  	  			}
  	  		}
  	  		if(flag == 0) { //flag=0���ж������
  	  	  		int delete = userService.deleteclient(c_id);
  	  	  		if(delete>0) {
  	  	  			return "redirect:/client_message?pageNo="+pageNo;
  	  	  		}else {
  	  	  			return "error";
  	  	  		}	
  	  		}else { //flag>0�ж���û���
  	  			return "redirect:manage/error/client_delete_error.jsp"+"?pageNo="+pageNo;
  	  		}
  		}else {//��ѯ�Ķ�����Ϊ�գ�û��ͬ��ֱ��ɾ��
  			userService.deleteclient(c_id);
  			return "redirect:/client_message?pageNo="+pageNo;
  		}
  	}
  	//�༭
  	@RequestMapping("updateclient")
  	public String updateClient(Client client,HttpServletRequest request) {
  		String c_id = request.getParameter("c_id");  		
  		String c_name = request.getParameter("c_name");
  		String phone = request.getParameter("phone");
  		String address = request.getParameter("address");
  		String pageNo = request.getParameter("pageNo").trim();
  		int up = userService.updateclient(c_id,c_name,phone,address);
  		if(up>0) {
  			return "redirect:manage/success/client_update_success.jsp"+"?pageNo="+pageNo;
  		}else {
  			return "error";
  		}
  	}
  	//��ѯ
	@RequestMapping("searchclient")
	public String searchClient(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Client> userList = userService.list_clientByName(name);
        PageInfo<Client> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/client_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}
	/**
	 * �г��ͻ���Ϣ
	 */
	@RequestMapping(value="/c_message",method=RequestMethod.GET)
    public String c_message(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request)
    		throws ServletException, IOException{ 
		String c_id= request.getParameter("c_id").trim();
        List<Client> userList = userService.list_clientByName(c_id);
        if(userList != null& userList.size() != 0) {
	        String c_name = userList.get(0).getC_name();
	        String phone = userList.get(0).getPhone();
	        String address = userList.get(0).getAddress();
	        return "redirect:manage/c_message.jsp?c_id="+c_id+"&c_name="+URLEncoder.encode(c_name,"UTF-8")+"&phone="+phone+"&address="+URLEncoder.encode(address,"UTF-8");
        }else {
        	 return "redirect:manage/error/c_message_error.jsp";
        }
       
	}
	/**
	 * ��״ͼ
	 */
	@RequestMapping(value="/sex_pie",method=RequestMethod.POST)
	@ResponseBody
	public  String  getChart() {
        //��ѯ����
		List<Staffs> userlist = staffsMapper.selectStaff();
		int man = 0;
		int women = 0;
		for(int i=0;i<userlist.size();i++) {
			if(userlist.get(i).getSex().equals("��")) {
				man +=1;
			}else {
				women +=1;
			}
		}
        return man+","+women;
        
	}
	/**
	 * ˫��״ͼ
	 */
	@RequestMapping(value="/zhu",method=RequestMethod.POST)
	@ResponseBody
	public  String  getMoney() {
        //ǰ1��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -1);
		String day1 = sdf.format(now.getTime());
		now.add(Calendar.DATE, -1);
		String day2 = sdf.format(now.getTime());
		now.add(Calendar.DATE, -1);
		String day3 = sdf.format(now.getTime());
		now.add(Calendar.DATE, -1);
		String day4 = sdf.format(now.getTime());
		now.add(Calendar.DATE, -1);
		String day5 = sdf.format(now.getTime());
		//֧��
		double a =0;
		double b =0;
		double c =0;
		double d =0;
		double e =0;
		List<Expend> userlist=expendMapper.selectExpend();
		for(int i=0;i<userlist.size();i++) {
			if(userlist.get(i).getDate().toString().equals(day1)) {
				a += userlist.get(i).getExpenditure();
			}else if(userlist.get(i).getDate().toString().equals(day2)) {
				b += userlist.get(i).getExpenditure();
			}else if(userlist.get(i).getDate().toString().equals(day3)) {
				c += userlist.get(i).getExpenditure();
			}else if(userlist.get(i).getDate().toString().equals(day4)) {
				d += userlist.get(i).getExpenditure();
			}else if(userlist.get(i).getDate().toString().equals(day5)) {
				e += userlist.get(i).getExpenditure();
			}
		}
		//����
		double a1 =0;
		double b1 =0;
		double c1 =0;
		double d1 =0;
		double e1 =0;
		List<Achievement> userlist2=achievementMapper.selectAchievement();
		for(int i=0;i<userlist2.size();i++) {
			if(userlist2.get(i).getDate().toString().equals(day1)) {
				a1 += userlist2.get(i).getTurnover();
			}else if(userlist2.get(i).getDate().toString().equals(day2)) {
				b1 += userlist2.get(i).getTurnover();
			}else if(userlist2.get(i).getDate().toString().equals(day3)) {
				c1 += userlist2.get(i).getTurnover();
			}else if(userlist2.get(i).getDate().toString().equals(day4)) {
				d1 += userlist2.get(i).getTurnover();
			}else if(userlist2.get(i).getDate().toString().equals(day5)) {
				e1 += userlist2.get(i).getTurnover();
			}
		}
		//������
		double a2 =a1-a;
		double b2 =b1-b;
		double c2 =c1-c;
		double d2 =d1-d;
		double e2 =e1-e;
		
        return a+","+b+","+c+","+d+","+e+","+a1+","+b1+","+c1+","+d1+","+e1+","+a2+","+b2+","+c2+","+d2+","+e2;
        
	}
	/**
	 * ����
	 */
	@RequestMapping(value="buy_message",method=RequestMethod.GET)
    public String pageList_buy(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Buy> userList = userService.selectAllBuy();
        PageInfo<Buy> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/buy_message.jsp?pageNo="+pageNo;
	}
	//���
	@RequestMapping("addbuy")
	public String addBuy(Buy buy,HttpServletRequest request,@RequestParam(value="date",required=false) Date date2) throws ParseException {
		String b_id = request.getParameter("b_id");
		String b_name = request.getParameter("b_name");
		String number = request.getParameter("number");
		String price = request.getParameter("price");
		String date = request.getParameter("date");
		int add = userService.addbuy(b_id, b_name, number,date,price);//��Ӽ�¼���������
		if(add>0) {//��ӳɹ�
	  	  		List<Expend> expendList=expendService.selectExpendByDate(date2);
	  	  		double num=Double.parseDouble(number);
	  	  		double expenditure=Double.parseDouble(price)*num; //����x����=��֧��
	            if(expendList.size()==0) {//���ѡ��������û֧����¼�����¼�¼���
	            	Expend expend=new Expend(date2,expenditure,null);
	            	expendService.addExpend(expend);
	  			}
	            else {//���ѡ���������������¼�����ھɼ�¼������
		  			double exp=expenditure+expendList.get(0).getExpenditure();
		  			Expend expend=new Expend(date2,exp,null);
		  			expendService.updateExpend(expend);
	            }
				return "redirect:manage/success/buy_update_success.jsp?pageNo=1";
		}else {//���ʧ��
			return "error";
		}
	}
  	//�༭
  	@RequestMapping("updatebuy")
  	public String updateBuy(HttpServletRequest request) {
  		int id = Integer.valueOf(request.getParameter("id").trim());
  		String b_id = request.getParameter("b_id").trim(); 		
  		String b_name = request.getParameter("b_name").trim();
  		String number = request.getParameter("number").trim();
  		String price = request.getParameter("price").trim();
  		String date = request.getParameter("date").trim();
  		String old_date = request.getParameter("old_date").trim();
  		String old_number = request.getParameter("old_number").trim();
  		String old_price = request.getParameter("old_price").trim();	
  		String pageNo = request.getParameter("pageNo").trim();
  		//��֧��
  		double old_exp = Double.parseDouble(old_price)*Integer.parseInt(old_number);
  		//��֧��
  		double new_exp = Double.parseDouble(price)*Integer.parseInt(number);
  		//�޸Ĺ����¼
  		int up = userService.updatebuy(id, b_id, b_name, number, date, price);
  		//��ѯ���º�����ڵ����֧�����
  		List<Expend> expendList=expendService.selectExpendByDate(java.sql.Date.valueOf(date));
  		if(up>0) {
  			//�޸�֧����¼
  			if(date.equals(old_date)) {//����û�޸� 	
				if(old_exp == new_exp) {//֧��û��
					}else{//֧������  							
						double exp = expendList.get(0).getExpenditure()- old_exp + new_exp; //��ȥ��֧��������֧��
						Expend expend=new Expend(java.sql.Date.valueOf(date),exp,null);
						expendService.updateExpend(expend);
				}
  			}else {//���ڱ���
  				List<Expend> expendListold=expendService.selectExpendByDate(java.sql.Date.valueOf(old_date));
  				double exp = expendListold.get(0).getExpenditure()-old_exp;
  				if(exp == 0) {//����֧��Ϊ0��ֱ��ɾ����¼
  					expendService.deleteExpend(java.sql.Date.valueOf(old_date));
  				}else {//����֧����Ϊ0���޸ļ�¼
					Expend expend=new Expend(java.sql.Date.valueOf(old_date),exp,null);
					expendService.updateExpend(expend);
  				}
  				if(expendList.size()==0) {//�޸ĵ������ڵ���û��¼�������¼
  					Expend expend=new Expend(java.sql.Date.valueOf(date),new_exp,null);
  					expendService.addExpend(expend);
  				}else {//�м�¼
					double exp1 = expendList.get(0).getExpenditure()- old_exp + new_exp; //��ȥ��֧��������֧��
					Expend expend=new Expend(java.sql.Date.valueOf(date),exp1,null);
					expendService.updateExpend(expend);
  				}
  			}
  			return "redirect:manage/success/buy_update_success.jsp"+"?pageNo="+pageNo;
  		}else {
  			return "error";
  		}
  	}
  	//ɾ��
	@RequestMapping("deletebuy")
	public String deletebuy(HttpServletRequest request) {
		//��ȡ�ֶ�
		String b_id = request.getParameter("b_id").trim();
		String number = request.getParameter("number").trim();
		String date = request.getParameter("date").trim();
		String pageNo = request.getParameter("page").trim();
		double price = Double.parseDouble(request.getParameter("price").trim());
		int id = Integer.valueOf(request.getParameter("id").trim());
  		Date date1=java.sql.Date.valueOf(date);//Stringת����Date
  		int new_number=Integer.parseInt(number);//Stringת��int
		//ɾ�������¼�ϵ���Ϣ
		int delete = userService.deletebuy(id);
		if(delete>0) {
			List<Expend> expendList=expendService.selectExpendByDate(date1);//��ѯ��Ӧ���ڵ�֧����¼
			double exp = expendList.get(0).getExpenditure() - new_number*price;//����֧��
			if(exp == 0) {//֧��Ϊ0��ֱ��ɾ����֧����¼
				expendService.deleteExpend(date1);
			}else {
	  			Expend expend=new Expend(date1,exp,null);
	  			expendService.updateExpend(expend);
			}
			return "redirect:/buy_message?pageNo="+pageNo;
		}else {
			return "error";
		}		
	}
	//��ѯ
	@RequestMapping(value="searchbuy")
	public String searchbuy(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//ÿҳ��ʾ��¼��
        //��ҳ��ѯ
        PageHelper.startPage(pageNo,pageSize);//�ӵ�һҳ��ʼ��ÿҳpageSize����¼
        List<Buy> userList = userService.selectBuyByName(name);//��ȡ�����û���Ϣ
        PageInfo<Buy> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/buy_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}	
}

