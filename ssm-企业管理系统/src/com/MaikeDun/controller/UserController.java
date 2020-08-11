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
			//登录成功，将用户信息保存到session对象中
			Staff message = userMapper.select(staff.getName());
			session.setAttribute("staff", message);
			//重定向到主页面
			return "redirect:manage/index.jsp";
		}else if(loginType == 1) {
			model.addAttribute("msg", "密码错误，请重新登录 ！");
			return "login";
		}
		model.addAttribute("msg", "此用户不存在，如未注册请前往注册！");
		return "login";
	}
	@RequestMapping("/toregister")
	public String addStaff(Staff staff,Model model,HttpSession session,HttpServletRequest request) {
			String password = request.getParameter("password");//密码
			String flag = request.getParameter("flag");//注册码
			String id = request.getParameter("name");
			int int_id = Integer.valueOf(id); //工号
			List<Staffs> userList = staffsMapper.selectStaffById(int_id);
			if(userList.size()!=0) {//员工表有此用户
				InviteCode inviteCode = userMapper.selectcode(flag);
				if(inviteCode == null) {//没有这种注册码
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
			}else {//员工表没有此用户
				return "redirect:manage/error/register_error2.jsp";
			}


		}
	@RequestMapping("/usermain")
	public String main() {
		return "main";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//清除session
		session.invalidate();
		return "login";
	}
	//修改密码
	@RequestMapping("updatepwd")
	public String updatePwd(Staff staff,HttpSession session,HttpServletRequest request) {
		String password = staff.getPassword(); 
		String newpwd = request.getParameter("newpwd");
		Staff staffs = (Staff)session.getAttribute("staff");
		String id = staffs.getId();
		if(password.equals(newpwd)) {
			int up = userService.updatepwd(id,password);			
			if(up>0) {
				//清除session
				session.invalidate();
				return "updatepwd_success";
			}
		}
		return "error";
	}
	//修改用户头像
	@RequestMapping("saveimg")
	public String saveImg(MultipartFile file,Staff staff, HttpSession session,HttpServletRequest request)throws IOException{
        /**
                 * 上传图片
         */
        //图片上传成功后，将图片的地址写到数据库
        String filePath = request.getSession().getServletContext().getRealPath("photo");;//保存图片的路径,tomcat中有配置
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
        String newFileName = UUID.randomUUID()+originalFilename;
        //封装上传文件位置的全路径，就是硬盘路径+文件名
        File targetFile = new File(filePath,newFileName); 
        //把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
        file.transferTo(targetFile);
        //staff.setImg(newFileName);//文件名保存到实体类对应属性上 
        Staff staffs = (Staff)session.getAttribute("staff");
		String id = staffs.getId();
		staffs.setImg(newFileName);
		session.setAttribute("staff",staffs);
        /**
                * 保存
         */
        userService.saveStaffImg(id,newFileName);
        return "redirect:manage/account_message.jsp"; //重定向到查询	
	}
	//修改用户信息
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
	//添加产品
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
	//编辑产品
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
	//删除产品
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
	//查询产品
	@RequestMapping(value="searchshop")
	public String searchshop(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Shop> userList = userService.listByName(name);//获取所有用户信息
        PageInfo<Shop> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}				
    /**
        * 分页列出产品
     */
    @RequestMapping(value="/shop_message",method=RequestMethod.GET)
    public String pageList(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Shop> userList = userService.list();//获取所有用户信息
        PageInfo<Shop> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_message.jsp?pageNo="+pageNo;
    }
    /*
     * shop_production生产详细
     */
    @RequestMapping(value="/shop_production",method=RequestMethod.GET)
    public String pageList_production(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<ShopProduct> userList = userService.list_product();
        PageInfo<ShopProduct> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_production.jsp?pageNo="+pageNo;
    }
    //添加
	@RequestMapping("addshopproduct")
	public String addShopProduct(ShopProduct shop,HttpServletRequest request) {
		String s_id = request.getParameter("s_id");
		String s_name = request.getParameter("s_name");
		String number = request.getParameter("number");
		String date = request.getParameter("date");
		int add = 0;
		List<Shop> userList = userMapper.selectShopById1(s_id);//判断库存中有没有这商品
		if(userList.isEmpty()&userList.size() == 0) { //为空就add语句
			userService.addshop(s_id, s_name, number,null);	//加入库存，还没设定价格
			add = userService.addshopproduct(s_id,s_name,number,date);//加入生产记录
		}else { //如果不为空，就update shop
			userService.product_update_shop(s_id, number); //增加库存数量number
			add = userService.addshopproduct(s_id,userList.get(0).getS_name(),number,date);//防止输入商品名字错误，增加的是库存中的名字
		}
		if(add>0) {//加入成功
			return "redirect:manage/success/shopproduct_update_success.jsp?pageNo=1";
		}else {
			return "error";
		}
	}
	//编辑
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
		userService.product_delete_shop(s_id, old_number); //删除之前记录加入的库存
		userService.product_update_shop(s_id, number); //加入现在记录的库存
		if(up>0) {
			return "redirect:manage/success/shopproduct_update_success.jsp"+"?pageNo="+pageNo;
		}else {
			return "error";
		}
	}
	//删除
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
	//查询
	@RequestMapping("searchshopproduct")
	public String searchshopproduct(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request)
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<ShopProduct> userList = userService.listByNameProduct(name);//获取所有用户信息
        PageInfo<ShopProduct> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_production.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}
    /*
     * shop_production销售详细
     */
    @RequestMapping(value="/shop_sale",method=RequestMethod.GET)
    public String pageList_sale(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<ShopSale> userList = userService.list_sale();
        PageInfo<ShopSale> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/shop_sale.jsp?pageNo="+pageNo;
    }
  //添加
	@RequestMapping("addshopsale")
  	public String addShopSale(ShopSale shop,HttpServletRequest request,@RequestParam(value="date",required=false) Date date1) throws ParseException {
  		double turnover;
  		String s_id = request.getParameter("s_id");
  		String number = request.getParameter("number");
  		String date = request.getParameter("date");
  		List<Shop> userList = userService.selectShopById1(s_id);//判断库存中有没有这商品
  		//System.out.print("日期"+date1);
	   //System.out.print("数量"+num);
  		//System.out.print("金额"+turnover);
  		if(!userList.isEmpty()&userList.size()!= 0) {//库存有这个商品
	  	  		//新的修改
	  	  		
	  	  		/**
	  	  		 * 销售记录预准备
	  	  		 */if(Integer.parseInt(number)>Integer.parseInt(userList.get(0).getNumber())) {
			  	  		return "redirect:manage/error/shopsale_error.jsp?pageNo=1";
			  	  		}
			  	  		else {
			  	  		int add = userService.addshopsale(s_id,userList.get(0).getS_name(),number,date);//增加销售记录	
	  	  		List<Achievement> achievementList=achievementService.selectAchievementByDate(date1);	
	  	  		double num=Double.parseDouble(number);
	  	  		double turnover1=Double.parseDouble(userList.get(0).getPrice())*num;
	  	  		if(add>0) {
			  	  			/*
			  	  			 *增加销售记录的同时增加收入
			  	  		**
			  	  		*/
	  	  		userService.sale_update_shop(s_id, number);//删除库存
			            if(achievementList.size()==0) {//如果选定的那天没收入记录，就新纪录添加
			            	turnover=turnover1;
			            	Achievement achiecement=new Achievement(date1,turnover,null);
			  				achievementService.addAchievementByAuto(achiecement);
			  			}
			            else {//如果选定的那天有收入记录，就在旧记录下增加
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
  		}else {//库存没这个商品，跳转错误页面
  			return "redirect:manage/error/shopsale_error.jsp?pageNo=1";
  		}
  	}
  	//编辑
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
  		//转化数据到对应格式
  		Date new_date=java.sql.Date.valueOf(date);
  		Date old_date=java.sql.Date.valueOf(date1);
  		int int_old_number=Integer.parseInt(old_number);
  		int new_number=Integer.parseInt(number);
  		List<Shop> userList = userService.selectShopById1(s_id);//判断库存中有没有这商品
  		if(Integer.parseInt(number)>Integer.parseInt(userList.get(0).getNumber())+int_old_number) {
  	  		return "redirect:manage/error/shopsale_error.jsp?pageNo=1";
  	  		}
  	  		else {
  		//更新销售表的记录
  		int up = userService.updateshopsale(id,s_id, s_name, number,date);
  		//影响库存
  		
  		List<Achievement> achievementList=achievementService.selectAchievementByDate(new_date);
  		if(up>0) {//更新销售表成功
  			//查询库存信息
  			userService.sale_delete_shop(s_id, old_number); //添加之前被销售记录删除的库存
  	  		userMapper.sale_update_shop(s_id, number); //减去现在销售记录的库存
  	  		//根据修改后的日期查询记录
  			if(date1.equals(date)) {//没有修改日期的情况--new_date==old_date
	  			if(new_number==int_old_number) {//销售数量没有变化的情况
	  			}else {
	  				if(new_number>int_old_number) {//增加销售数量
	  					turnover=achievementList.get(0).getTurnover()+(new_number-int_old_number)*Double.parseDouble(userList.get(0).getPrice());
	  					Achievement achievement=new Achievement(new_date,turnover,null);
	  					achievementService.updateAchievementByAuto(achievement);
	  					//System.out.print("营业额"+turnover);
	  				}
	  				else {//减少销售数量
	  					turnover=achievementList.get(0).getTurnover()-(int_old_number-new_number)*Double.parseDouble(userList.get(0).getPrice());
	  					Achievement achievement=new Achievement(new_date,turnover,null);
	  					achievementService.updateAchievementByAuto(achievement);
	  				}
	  			}
  	  		}else {//日期修改了
  				List<Achievement> achievementListOld=achievementService.selectAchievementByDate(old_date);
  				turnover=achievementListOld.get(0).getTurnover()-int_old_number*Double.parseDouble(userList.get(0).getPrice());
  				if(turnover==0) {
  					achievementService.deleteAchievement(old_date);
  				}
  				else {
  				//System.out.print("营业额"+turnover+"xx"+(new_number-int_old_number));
  				Achievement achievement=new Achievement(old_date,turnover,null);
  				achievementService.updateAchievementByAuto(achievement);
  				}
  				if(achievementList.size()==0) {//该日期没有收入记录直接在收入表添加
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
  	//删除
 	@RequestMapping("deleteshopsale")
  	public String deleteshopsale(ShopSale shop,HttpServletRequest request) {
  		String pageNo = request.getParameter("page").trim();
  		String id = request.getParameter("id").trim();
  		String s_id = request.getParameter("s_id").trim();
  		String number = request.getParameter("number").trim();
  		String date = request.getParameter("date").trim();
  		Date date1=java.sql.Date.valueOf(date);//String转换成Date
  		int new_number=Integer.parseInt(number);//String转换int
  		int delete = userService.deleteshopsale(id,s_id,date);
  		
  		List<Shop> userList = userService.selectShopById1(s_id);//查询产品单价
  		userService.sale_delete_shop(s_id, number);//shop的库存增加
  		List<Achievement> achievementList=achievementService.selectAchievementByDate(date1);//查询收入	
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
  	//查询
  	@RequestMapping("searchshopsale")
  	public String searchshopsale(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
  			throws ServletException, IOException{
  		String name = request.getParameter("name").trim();
          Integer pageSize=7;//每页显示记录数
          //分页查询
          PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
          List<ShopSale> userList = userService.listByNameSale(name);//获取所有用户信息
          PageInfo<ShopSale> pageInfo=new PageInfo<>(userList);
          session.setAttribute("pageInfo", pageInfo);
          return "redirect:manage/shop_sale.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
  	}
    /*
     * order_message订单详细
     */
    @RequestMapping(value="/order_message",method=RequestMethod.GET)
    public String pageList_order(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=1;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Order> userList = userService.list_order();
        PageInfo<Order> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/order_message.jsp?pageNo="+pageNo;
    }
    //添加
  	@RequestMapping("addorder")
  	public String addOrder(Order order,HttpServletRequest request,MultipartFile file, HttpSession session) throws IOException{
  		String s_id = request.getParameter("s_id");
  		String o_number = request.getParameter("o_number");
  		String o_time = request.getParameter("o_time");
  		String c_id = request.getParameter("c_id");
  		 /**
  		  * 合同图片
		 */
		//图片上传成功后，将图片的地址写到数据库
  		 String filePath = request.getSession().getServletContext().getRealPath("photo");;//保存图片的路径,tomcat中有配置
		//获取原始图片的拓展名
		String originalFilename = file.getOriginalFilename();
		//新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
		String newFileName = UUID.randomUUID()+originalFilename;
		//封装上传文件位置的全路径，就是硬盘路径+文件名
		File targetFile = new File(filePath,newFileName); 
		//把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
		file.transferTo(targetFile);
		/**
		 * 保存
		 */
  		int add = userService.addorder(s_id,o_number,o_time,newFileName,c_id,0);
  		if(add>0) {
  			return  "redirect:/order_message";
  		}else {
  			return "../error";
  		}
  	}
  	//删除
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
  	//编辑
  	@RequestMapping("updateorder")
  	public String updateOrder(Order order,HttpServletRequest request,MultipartFile file, HttpSession session) throws IOException{
  		int o_id = Integer.valueOf(request.getParameter("o_id"));
  		String s_id = request.getParameter("s_id");
  		String o_number = request.getParameter("o_number");
  		String o_time = request.getParameter("o_time");
  		String pageNo = request.getParameter("pageNo");
  		 /**
  		  * 合同图片
		 */
		//图片上传成功后，将图片的地址写到数据库
  		 String filePath = request.getSession().getServletContext().getRealPath("photo");;//保存图片的路径,tomcat中有配置
		//获取原始图片的拓展名
		String originalFilename = file.getOriginalFilename();
		//新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复
		String photo = UUID.randomUUID()+originalFilename;
		//封装上传文件位置的全路径，就是硬盘路径+文件名
		File targetFile = new File(filePath,photo); 
		//把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
		file.transferTo(targetFile);
		/**
		 * 保存
		 */
  		int add = userService.updateorder(o_id,s_id,o_number,o_time,photo);
  		if(add>0) {
  			return  "redirect:/order_message?pageNo="+pageNo;
  		}else {
  			return "../error";
  		}
  	}
  	//查询
	@RequestMapping("searchorder")
	public String searchorder(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request)
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=1;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Order> userList = userService.list_orderByName(name);
        PageInfo<Order> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/order_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}
	//完成
	@RequestMapping("successorder")
	public String successorder(Order order,HttpServletRequest request, HttpSession session) {
		double turnover;
		int o_id = Integer.valueOf(request.getParameter("o_id").trim());
		String s_id = request.getParameter("s_id").trim();
		String pageNo = request.getParameter("pageNo");
		String number = request.getParameter("number");
		double num=Double.parseDouble(number);
		//获取当前时间
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(now.getTime());
		Date date=java.sql.Date.valueOf(dateStr);
		//System.out.print("日期"+date+date.getClass());
		Shop shop= userMapper.selectShopById(s_id);
		System.out.println(shop);
		//System.out.print("营业额"+turnover1);
		if(shop != null) { //有这商品
			double turnover1=Double.parseDouble(shop.getPrice())*num;
			List<Achievement> achievementList=achievementService.selectAchievementByDate(date);
			if(Integer.valueOf(shop.getNumber())>Integer.valueOf(number)) {
				int ad = userService.addshopsale(s_id, shop.getS_name(), number,dateStr);
				int su = userService.updateorderflag(o_id,dateStr);
				if(ad >0) {
					/*
	  	  			 *完成合同的同时增加收入
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
	 * 客户管理
	 */
	@RequestMapping(value="/client_message",method=RequestMethod.GET)
    public String pageList_client(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Client> userList = userService.list_client();
        PageInfo<Client> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/client_message.jsp?pageNo="+pageNo;
	}
	  //添加
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
  	//删除
  	@RequestMapping("deleteclient")
  	public String deleteclient(Client client,HttpServletRequest request) {
  		String c_id =  request.getParameter("id").trim();
  		String pageNo = request.getParameter("page").trim();
  		int flag =0; //判断所删除客户是否有没完成的订单
  		List<Order> userList = userMapper.selectAllOrderByName(c_id);
  		if(!userList.isEmpty()) {
  	  		for(int i = 0; i< userList.size();i++){
  	  			if(userList.get(i).getFlag() == 0) {
  	  				flag += 1;
  	  			}
  	  		}
  	  		if(flag == 0) { //flag=0所有订单完成
  	  	  		int delete = userService.deleteclient(c_id);
  	  	  		if(delete>0) {
  	  	  			return "redirect:/client_message?pageNo="+pageNo;
  	  	  		}else {
  	  	  			return "error";
  	  	  		}	
  	  		}else { //flag>0有订单没完成
  	  			return "redirect:manage/error/client_delete_error.jsp"+"?pageNo="+pageNo;
  	  		}
  		}else {//查询的订单列为空，没合同可直接删除
  			userService.deleteclient(c_id);
  			return "redirect:/client_message?pageNo="+pageNo;
  		}
  	}
  	//编辑
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
  	//查询
	@RequestMapping("searchclient")
	public String searchClient(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Client> userList = userService.list_clientByName(name);
        PageInfo<Client> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/client_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}
	/**
	 * 列出客户信息
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
	 * 饼状图
	 */
	@RequestMapping(value="/sex_pie",method=RequestMethod.POST)
	@ResponseBody
	public  String  getChart() {
        //查询数量
		List<Staffs> userlist = staffsMapper.selectStaff();
		int man = 0;
		int women = 0;
		for(int i=0;i<userlist.size();i++) {
			if(userlist.get(i).getSex().equals("男")) {
				man +=1;
			}else {
				women +=1;
			}
		}
        return man+","+women;
        
	}
	/**
	 * 双柱状图
	 */
	@RequestMapping(value="/zhu",method=RequestMethod.POST)
	@ResponseBody
	public  String  getMoney() {
        //前1天
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
		//支出
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
		//收入
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
		//净收入
		double a2 =a1-a;
		double b2 =b1-b;
		double c2 =c1-c;
		double d2 =d1-d;
		double e2 =e1-e;
		
        return a+","+b+","+c+","+d+","+e+","+a1+","+b1+","+c1+","+d1+","+e1+","+a2+","+b2+","+c2+","+d2+","+e2;
        
	}
	/**
	 * 购买
	 */
	@RequestMapping(value="buy_message",method=RequestMethod.GET)
    public String pageList_buy(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo){   	
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Buy> userList = userService.selectAllBuy();
        PageInfo<Buy> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/buy_message.jsp?pageNo="+pageNo;
	}
	//添加
	@RequestMapping("addbuy")
	public String addBuy(Buy buy,HttpServletRequest request,@RequestParam(value="date",required=false) Date date2) throws ParseException {
		String b_id = request.getParameter("b_id");
		String b_name = request.getParameter("b_name");
		String number = request.getParameter("number");
		String price = request.getParameter("price");
		String date = request.getParameter("date");
		int add = userService.addbuy(b_id, b_name, number,date,price);//添加记录到购买管理
		if(add>0) {//添加成功
	  	  		List<Expend> expendList=expendService.selectExpendByDate(date2);
	  	  		double num=Double.parseDouble(number);
	  	  		double expenditure=Double.parseDouble(price)*num; //数量x单价=总支出
	            if(expendList.size()==0) {//如果选定的那天没支出记录，就新纪录添加
	            	Expend expend=new Expend(date2,expenditure,null);
	            	expendService.addExpend(expend);
	  			}
	            else {//如果选定的那天有收入记录，就在旧记录下增加
		  			double exp=expenditure+expendList.get(0).getExpenditure();
		  			Expend expend=new Expend(date2,exp,null);
		  			expendService.updateExpend(expend);
	            }
				return "redirect:manage/success/buy_update_success.jsp?pageNo=1";
		}else {//添加失败
			return "error";
		}
	}
  	//编辑
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
  		//旧支出
  		double old_exp = Double.parseDouble(old_price)*Integer.parseInt(old_number);
  		//新支出
  		double new_exp = Double.parseDouble(price)*Integer.parseInt(number);
  		//修改购买记录
  		int up = userService.updatebuy(id, b_id, b_name, number, date, price);
  		//查询更新后的日期当天的支出情况
  		List<Expend> expendList=expendService.selectExpendByDate(java.sql.Date.valueOf(date));
  		if(up>0) {
  			//修改支出记录
  			if(date.equals(old_date)) {//日期没修改 	
				if(old_exp == new_exp) {//支出没变
					}else{//支出变了  							
						double exp = expendList.get(0).getExpenditure()- old_exp + new_exp; //减去旧支出加上新支出
						Expend expend=new Expend(java.sql.Date.valueOf(date),exp,null);
						expendService.updateExpend(expend);
				}
  			}else {//日期变了
  				List<Expend> expendListold=expendService.selectExpendByDate(java.sql.Date.valueOf(old_date));
  				double exp = expendListold.get(0).getExpenditure()-old_exp;
  				if(exp == 0) {//当天支出为0，直接删除记录
  					expendService.deleteExpend(java.sql.Date.valueOf(old_date));
  				}else {//当天支出不为0，修改记录
					Expend expend=new Expend(java.sql.Date.valueOf(old_date),exp,null);
					expendService.updateExpend(expend);
  				}
  				if(expendList.size()==0) {//修改的新日期当天没记录，加入记录
  					Expend expend=new Expend(java.sql.Date.valueOf(date),new_exp,null);
  					expendService.addExpend(expend);
  				}else {//有记录
					double exp1 = expendList.get(0).getExpenditure()- old_exp + new_exp; //减去旧支出加上新支出
					Expend expend=new Expend(java.sql.Date.valueOf(date),exp1,null);
					expendService.updateExpend(expend);
  				}
  			}
  			return "redirect:manage/success/buy_update_success.jsp"+"?pageNo="+pageNo;
  		}else {
  			return "error";
  		}
  	}
  	//删除
	@RequestMapping("deletebuy")
	public String deletebuy(HttpServletRequest request) {
		//获取字段
		String b_id = request.getParameter("b_id").trim();
		String number = request.getParameter("number").trim();
		String date = request.getParameter("date").trim();
		String pageNo = request.getParameter("page").trim();
		double price = Double.parseDouble(request.getParameter("price").trim());
		int id = Integer.valueOf(request.getParameter("id").trim());
  		Date date1=java.sql.Date.valueOf(date);//String转换成Date
  		int new_number=Integer.parseInt(number);//String转换int
		//删除购买记录上的信息
		int delete = userService.deletebuy(id);
		if(delete>0) {
			List<Expend> expendList=expendService.selectExpendByDate(date1);//查询对应日期的支出记录
			double exp = expendList.get(0).getExpenditure() - new_number*price;//最新支出
			if(exp == 0) {//支出为0，直接删除该支出记录
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
	//查询
	@RequestMapping(value="searchbuy")
	public String searchbuy(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo,HttpServletRequest request) 
			throws ServletException, IOException{
		String name = request.getParameter("name").trim();
        Integer pageSize=7;//每页显示记录数
        //分页查询
        PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
        List<Buy> userList = userService.selectBuyByName(name);//获取所有用户信息
        PageInfo<Buy> pageInfo=new PageInfo<>(userList);
        session.setAttribute("pageInfo", pageInfo);
        return "redirect:manage/buy_message.jsp?pageNo="+pageNo+"&name="+URLEncoder.encode(name,"UTF-8");
	}	
}

