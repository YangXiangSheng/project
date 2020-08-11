package com.MaikeDun.controller;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MaikeDun.bean.Staffs;
import com.MaikeDun.bean.UpdateStaff;
import com.MaikeDun.service.StaffsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class StaffsController {
@Autowired
private StaffsService staffService;

@RequestMapping("/listStaff")
public String showAllStaff(HttpSession session,@RequestParam(defaultValue="1",required=true) Integer pageNo) {
	Integer pageSize=4;//每页显示记录数
	 PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
	List<Staffs> staff=staffService.selectStaff();
	PageInfo<Staffs> pageInfo=new PageInfo<>(staff);
	session.setAttribute("pageInfo", pageInfo);
	return "listStaff.jsp?pageNo="+pageNo;
}
@RequestMapping("/listOneStaff")
public String showOneStaff(HttpSession session,@RequestParam(value="staffId",required=false) Integer staffId,
		@RequestParam(defaultValue="1",required=false) Integer pageNo,
		Model model) {
	Integer pageSize=1;//每页显示记录数
    //分页查询
    PageHelper.startPage(pageNo,pageSize);//从第一页开始，每页pageSize条记录
	if(staffId==null) {
		 model.addAttribute("staffIdEmptyError","员工编号不能为空");
		 return "listStaff.jsp?pageNo="+pageNo;
	 }
	List<Staffs> staff=staffService.selectStaffById(staffId);
	PageInfo<Staffs> pageInfo=new PageInfo<>(staff);
	if(staff.size()==0) {
		model.addAttribute("staffIdNotFound", "没有该员工");
		return showAllStaff(session,pageNo);
	}
	session.setAttribute("pageInfo", pageInfo);
	return "listStaff";
}

@RequestMapping("/upadteStaff")
public String update(HttpServletRequest request,@RequestParam(value="staffId",required=false) Integer staffId) {
	List<Staffs> staff=staffService.selectStaffById(staffId);
	request.setAttribute("Staff", staff);
	return "updateStaff";
}
@RequestMapping("/updateStaffFinal")
public String updateStaffFinal(RedirectAttributes attr,@RequestParam("staffOldId") Integer staffOldId,@RequestParam("staffNewId") Integer staffNewId,
		@RequestParam("staffName") String staffName, @RequestParam("sex") String sex,
		@RequestParam("phone") String phone, @RequestParam("email") String email,@RequestParam("idcard") String idcard, Model model,
		HttpServletRequest request) {
	//System.out.print("NEW:"+staffNewId+"OLD"+staffOldId);
	 String old_phone=request.getParameter("old_phone");
	 String old_email=request.getParameter("old_email");
	 String old_idcard=request.getParameter("old_idcard");
	if(staffNewId==null) {
		 model.addAttribute("staffIdEmptyError","员工编号不能为空");
	 }
	 if(staffName.equals("")) {
		 model.addAttribute("staffNameEmptyError","员工姓名不能为空");
	 }
	 String judgephone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
	 if(!Pattern.matches(judgephone, phone)) {
		 model.addAttribute("phoneEmptyError","员工电话格式错误");
	 }
	 String format = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	   if (!email.matches(format)) {
		   model.addAttribute("emailError","员工邮箱格式不对");  
	   }
	   String regex = "\\d{15}|\\d{17}[\\dxX]";
       //
       if(!idcard.matches(regex)) {
           model.addAttribute("idcardError", "身份证号码格式错误");
       }
	   String department=request.getParameter("department");
	   if(department==null) {
			 model.addAttribute("departmentEmptyError","员工部门不能为空");
		 } 
	   String position=request.getParameter("position");
	   if(position==null) {
			 model.addAttribute("positionEmptyError","员工职位不能为空");
		 } 
	   
			if(staffNewId==null||staffName.equals("")||!Pattern.matches(judgephone, phone)||!email.matches(format)||department==null||position==null||!idcard.matches(regex)) {
				return update(request,staffOldId);
			}
	   UpdateStaff us=new UpdateStaff(staffNewId,staffName,sex,phone,email,idcard,department,position,staffOldId);
	 try {
	   staffService.updateStaff(us);
   }catch(Exception e) {
	   List<Staffs> staffList=staffService.selectStaff();
	   for(Staffs staff:staffList) {
	  if(staff.getStaffId().equals(staffNewId)&&!staffOldId.equals(staffNewId)) {
	   model.addAttribute("staffIdOnlyError","员工编号已存在！请确认编号！");
	  }
	  if(staff.getPhone().equals(phone)&&!old_phone.equals(phone)) {
		  model.addAttribute("phoneOnly", "电话号码已存在，请重新输入！");
	  }
	  if(staff.getEmail().equals(email)&&!old_email.equals(email)) {
		  model.addAttribute("emailOnly", "邮箱已存在，请重新输入！");
	  }
	  if(staff.getIdcard().equals(idcard)&&!old_idcard.equals(idcard)) {
		  model.addAttribute("idcardOnly", "身份证已存在，请重新输入！");
	  }
	   }
	   return update(request,staffOldId);
   
   }
	  
return "redirect:/listStaff";
}
@RequestMapping("/upadteStaffImage")
public String updateImage(HttpServletRequest request,@RequestParam("staffId") Integer staffId) {
	List<Staffs> staff=staffService.selectStaffById(staffId);
	request.setAttribute("Staff", staff);
	return "updateStaffPhoto";
}
@RequestMapping("/updateStaffPhoto")
public String updateStaffPhoto(RedirectAttributes redirectAttributes, @RequestParam("staffId") Integer staffId,Model model,
		HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
		request.getSession().getServletContext());
String image = null;
if (multipartResolver.isMultipart(request)) {
	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	Iterator<String> iter = multiRequest.getFileNames();
	String myFileName = null;
	String myFiledName = null;
	while (iter.hasNext()) {
		List<MultipartFile> file = multiRequest.getFiles(iter.next());
		if (file != null) {
			for (MultipartFile files : file) {
				myFileName = files.getOriginalFilename();
				myFiledName = files.getName();
				if (myFileName.toString().trim() != "") {
					String path = request.getSession().getServletContext().getRealPath("photo/");
					String fileName = null;
					fileName =  myFileName;
					if (myFiledName.equals("photo")) {
						image = fileName;
					}
					String oldSuffix=FilenameUtils.getExtension(myFileName);//后缀名
					if(oldSuffix.equals("jpg")
		                    || oldSuffix.equals("png")
		                    ||oldSuffix.equals("pneg")
		                    || oldSuffix.equals("jpeg")
		                    || oldSuffix.equals("JPG")
		                    || oldSuffix.equals("PNG")
		                    || oldSuffix.equals("JPEG")
		                    || oldSuffix.equals("PENG")
		                    ) {
						File targetFile = new File(path, image);
						System.out.print("路径"+targetFile);
						 Staffs staff = new Staffs();
							staff.setStaffId(staffId);
							staff.setPhoto(image);
							staffService.updateStaffPhoto(staff);
						files.transferTo(targetFile);//保存文件
					}else {
						model.addAttribute("imageErro","格式不对");
						return updateImage(request,staffId);
					}
				}
			}
		}
	}
}
return "redirect:/listStaff";

}
@RequestMapping("/deleteStaff")
public String delete(HttpServletRequest request, @RequestParam("staffId") Integer staffId ,@RequestParam("photo") String photo) {
    String savePath = request.getSession().getServletContext().getRealPath("/") + "photo\\";
    File file = new File(savePath + photo);
    if (file.exists()) {
        file.delete();
    } 
    String pageNo = request.getParameter("pageNo");
	staffService.deleteStaff(staffId);
	return "redirect:/listStaff?pageNo="+pageNo;
}
@RequestMapping("/addStaffInit")
public String addStaffInit() {
	return "addStaff";
}
@RequestMapping("/addStaff")
	public String upload( @RequestParam("staffId") Integer staffId,
			@RequestParam("staffName") String staffName, @RequestParam("sex") String sex,
			@RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("idcard") String idcard, Model model,
			HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {
	
	List<Staffs> staffList=staffService.selectStaff();
	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
			request.getSession().getServletContext());
	String image = null;
	 if(staffId==null) {
		 model.addAttribute("staffIdEmptyError","员工编号不能为空");
	 }
	 if(staffName.equals("")) {
		 model.addAttribute("staffNameEmptyError","员工姓名不能为空");
	 }
	 String judgephone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
	 if(!Pattern.matches(judgephone, phone)) {
		 model.addAttribute("phoneEmptyError","员工电话格式错误");
	 }
	 String format = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	   if (!email.matches(format)) {
		   model.addAttribute("emailError","员工邮箱格式不对");  
	   }
	   String regex = "\\d{15}|\\d{17}[\\dxX]";
       //
       if(!idcard.matches(regex)) {
           model.addAttribute("idcardError", "身份证号码格式错误");
       }
	   String department=request.getParameter("department");
	   if(department==null) {
			 model.addAttribute("departmentEmptyError","员工部门不能为空");
		 } 
	   String position=request.getParameter("position");
	   if(position==null) {
			 model.addAttribute("positionEmptyError","员工职位不能为空");
		 } 
	   for(Staffs staff:staffList) {
			if(staff.getPhone().equals(phone)) {
				model.addAttribute("phoneOnly", "电话号码已存在，请重新输入！");
			}
			if(staff.getEmail().equals(email)) {
				model.addAttribute("emailOnly", "邮箱已存在，请重新输入！");
			}
			if(staff.getIdcard().equals(idcard)) {
				model.addAttribute("idcardOnly", "身份证已存在，请重新输入!");
			}
			if(staff.getPhone().equals(phone)||staff.getEmail().equals(email)||staff.getIdcard().equals(idcard)||staffId==null||staffName.equals("")||!Pattern.matches(judgephone, phone)||!email.matches(format)||department==null||position==null||!idcard.matches(regex)) {
				return "addStaff";
			}
		}
	if (multipartResolver.isMultipart(request)) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iter = multiRequest.getFileNames();
		String myFileName = null;
		String myFiledName = null;
		while (iter.hasNext()) {
			List<MultipartFile> file = multiRequest.getFiles(iter.next());
			if (file != null) {
				for (MultipartFile files : file) {
					myFileName = files.getOriginalFilename();
					myFiledName = files.getName();
					if (myFileName.toString().trim() != "") {
						String path = request.getSession().getServletContext().getRealPath("photo");
						String fileName = null;
						fileName =  myFileName;
						if (myFiledName.equals("photo")) {
							image = fileName;
						}
						String oldSuffix=FilenameUtils.getExtension(myFileName);//后缀名
						if(oldSuffix.equals("jpg")
			                    || oldSuffix.equals("png")
			                    ||oldSuffix.equals("pneg")
			                    || oldSuffix.equals("jpeg")
			                    || oldSuffix.equals("JPG")
			                    || oldSuffix.equals("PNG")
			                    || oldSuffix.equals("JPEG")
			                    || oldSuffix.equals("PENG")
			                    ) {
							File targetFile = new File(path, image);
							//System.out.print("路径"+targetFile);
							 Staffs staff = new Staffs(staffId, staffName, sex, phone, email,idcard,image,department,position);
								try {
								staffService.addStaff(staff);
							    }catch(Exception e) {
							    	model.addAttribute("staffIdOnlyError","请正确填写员工编号");
							    	return "addStaff";
							    }
							files.transferTo(targetFile);//保存文件
						}else {
							model.addAttribute("imageErro","格式不对");
							return "addStaff";
						}
					}
				}
			}
		}
	}
	return "redirect:/listStaff";
}
}
