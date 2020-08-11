package com.MaikeDun.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MaikeDun.dao.UserDao;
import com.MaikeDun.service.UserService;
import com.MaikeDun.bean.Buy;
import com.MaikeDun.bean.Client;
import com.MaikeDun.bean.InviteCode;
import com.MaikeDun.bean.Order;
import com.MaikeDun.bean.Shop;
import com.MaikeDun.bean.ShopProduct;
import com.MaikeDun.bean.ShopSale;
import com.MaikeDun.bean.Staff;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userMapper;

	@Override
	public int register(Staff staff) {
		// TODO Auto-generated method stub
		Staff staffs = userMapper.select(staff.getId());
		if(staffs != null) {//工号已经被注册
			return 100;
		}else {
			InviteCode inviteCode = userMapper.selectcode(staff.getFlag());
			staff.setFlag(inviteCode.getFlag());
			return userMapper.add(staff.getId(),staff.getName(),staff.getPassword(),staff.getSex(),staff.getEmail(),staff.getPhone(),staff.getIdcard(),staff.getFlag(),staff.getImg());
		}
	}

	@Override
	public int login(String username, String userpassword) {
		// TODO Auto-generated method stub
		Staff staff = userMapper.select(username);
		int flag = 0;
		if(staff != null) {
			if(staff.getIdcard().equals(username) || staff.getId().equals(username)) {
				flag += 1;
				if(staff.getPassword().equals(userpassword)) {
					flag += 1;
				}
			}
		}return flag;
	}

	@Override
	public int updatepwd(String id, String password) {
		// TODO Auto-generated method stub
		return  userMapper.updatestaffpwd(id,password);
	}

	@Override
	public int updatemes(String id, String email, String phone, String address, String QQ) {
		// TODO Auto-generated method stub
		return userMapper.updatestaffmessage(id, email, phone, address, QQ);
	}
	
	@Override
	public int saveStaffImg(String id,String img) {
		// TODO Auto-generated method stub
		return userMapper.updatestaffimg(id, img);
	}

	/*
	   * 产品
	   */
	@Override
	public int addshop(String id, String name, String number,String price) {
		// TODO Auto-generated method stub
		return userMapper.addshop(id,name,number,price);
	}
	
	@Override
	public int deleteshop(String id) {
		// TODO Auto-generated method stub
		return userMapper.deleteshop(id);
	}

	@Override
	public int updateshop(String id, String name, String number,String price) {
		// TODO Auto-generated method stub
		return userMapper.updateshop(id, name, number,price);
	}

	/*
	 * pagehelper插件分页
	 * @see com.MaiKeDun.service.UserService#list()
	 */
	  @Override
	    public List<Shop> list() {
	        return userMapper.selectAllShop();
	    }

	@Override
	public List<Shop> listByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectShopByName(name);
	}

	@Override
	public List<ShopProduct> list_product() {
		// TODO Auto-generated method stub
		return userMapper.selectAllShopPro();
	}

	@Override
	public int addshopproduct(String id, String name, String number, String date) {
		// TODO Auto-generated method stub
		return userMapper.addshopproduct(id,name,number,date);
	}

	@Override
	public int updateshopproduct(String id,String s_id,String name, String number, String date) {
		// TODO Auto-generated method stub
		return userMapper.updateshopproduct(id,s_id, name, number,date);
	}

	@Override
	public int deleteshopproduct(String id,String s_id,String date) {
		// TODO Auto-generated method stub
		return userMapper.deleteshopproduct(id);
	}

	@Override
	public List<ShopProduct> listByNameProduct(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectShopByNameProduct(name);
	}
	//销售
	@Override
	public List<ShopSale> list_sale() {
		// TODO Auto-generated method stub
		return userMapper.selectAllShopSale();
	}

	@Override
	public List<ShopSale> listByNameSale(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectShopByNameSale(name);
	}

	@Override
	public int addshopsale(String id, String name, String number, String date) {
		// TODO Auto-generated method stub
		return userMapper.addshopsale(id, name, number, date);
	}

	@Override
	public int deleteshopsale(String id,String s_id,String date) {
		// TODO Auto-generated method stub
		return userMapper.deleteshopsale(id);
	}

	@Override
	public int updateshopsale(String id, String s_id,String name, String number, String date) {
		// TODO Auto-generated method stub
		return userMapper.updateshopsale(id,s_id, name, number, date);
	}

	@Override
	public int product_update_shop(String id, String number) {
		// TODO Auto-generated method stub
		return userMapper.product_update_shop(id,number);
	}

	@Override
	public int product_delete_shop(String id, String number) {
		// TODO Auto-generated method stub
		return userMapper.product_delete_shop(id,number);
	}

	@Override
	public String selectShopById(String id) {
		// TODO Auto-generated method stub
		Shop shop = userMapper.selectShopById(id);
		return shop.getNumber();
	}
	public List<Shop> selectShopById1(String id){
		return userMapper.selectShopById1(id);
	}
	@Override
	public int sale_update_shop(String id, String number) {
		// TODO Auto-generated method stub
		return userMapper.sale_update_shop(id, number);
	}

	@Override
	public int sale_delete_shop(String id, String number) {
		// TODO Auto-generated method stub
		return userMapper.sale_delete_shop(id, number);
	}
	/*
	 * 订单Order
	 */

	@Override
	public List<Order> list_order() {
		// TODO Auto-generated method stub
		return userMapper.selectAllOrder();
	}

	@Override
	public int addorder(String s_id, String o_number, String o_time, String photo,String c_id,int flag) {
		// TODO Auto-generated method stub
		return userMapper.addorder(s_id,o_number,o_time,photo,c_id,flag);
	}

	@Override
	public int deleteorder(int id) {
		// TODO Auto-generated method stub
		return userMapper.deleteorder(id);
	}

	@Override
	public int updateorder(int o_id, String s_id, String o_number, String o_time, String photo) {
		// TODO Auto-generated method stub
		return userMapper.updateorder(o_id, s_id, o_number, o_time, photo);
	}

	@Override
	public List<Order> list_orderByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectAllOrderByName(name);
	}

	@Override
	public int updateorderflag(int o_id,String dateStr) {
		// TODO Auto-generated method stub
		return userMapper.updateorderflag(dateStr,o_id);
	}
	/**
	 * 客户
	 */

	@Override
	public List<Client> list_client() {
		// TODO Auto-generated method stub
		return userMapper.selectAllClient();
	}

	@Override
	public List<Client> list_clientByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectAllClientByName(name);
	}

	@Override
	public int addclient(String c_id, String c_name, String phone, String address) {
		// TODO Auto-generated method stub
		return userMapper.addclient(c_id,c_name,phone,address);
	}

	@Override
	public int deleteclient(String c_id) {
		// TODO Auto-generated method stub
		return userMapper.deleteclient(c_id);
	}

	@Override
	public int updateclient(String c_id, String c_name, String phone, String address) {
		// TODO Auto-generated method stub
		return userMapper.updateclient(c_id,c_name,phone,address);
	}
	/**
	 * 购买
	 */

	@Override
	public List<Buy> selectAllBuy() {
		// TODO Auto-generated method stub
		return userMapper.selectAllBuy();
	}

	@Override
	public List<Buy> selectBuyByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectBuyByName(name);
	}

	@Override
	public int addbuy(String id, String name, String number, String date, String price) {
		// TODO Auto-generated method stub
		return userMapper.addbuy(id, name, number, date, price);
	}

	@Override
	public int updatebuy(int id, String s_id, String name, String number, String date, String price) {
		// TODO Auto-generated method stub
		return userMapper.updatebuy(id, s_id, name, number, date, price);
	}

	@Override
	public int deletebuy(int id) {
		// TODO Auto-generated method stub
		return userMapper.deletebuy(id);
	}
	
	
	
	
	
	

	  
}