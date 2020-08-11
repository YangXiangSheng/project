package com.MaikeDun.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.MaikeDun.bean.Buy;
import com.MaikeDun.bean.Client;
import com.MaikeDun.bean.Order;

import com.MaikeDun.bean.Shop;
import com.MaikeDun.bean.ShopProduct;
import com.MaikeDun.bean.ShopSale;
import com.MaikeDun.bean.Staff;


public interface UserService {
	/*
	 * 注册和登录
	 */
	int register(Staff user);
	public int login(String username,String userpassword);
	/*
	 * 用户信息
	 */
	public int updatepwd(String id,String password);
	public int updatemes(String id,String email,String phone,String address,String QQ);
	public int saveStaffImg(String id,String img);
	/*
	 * 产品
	 */
	//public List<Shop> list(Integer startLine,Integer pageSize);
	public List<Shop> list();
	public List<Shop> listByName(String name);
	public int addshop(String id,String name,String number,String price);
	public int deleteshop(String id);
	public int updateshop(String id,String name,String number,String price);
	public String selectShopById(String id);
	public List<Shop> selectShopById1(String id);
	//生产
	public List<ShopProduct> list_product();
	public List<ShopProduct> listByNameProduct(String name);
	public int addshopproduct(String id,String name,String numbe,String date);
	public int deleteshopproduct(String id,String s_id,String date);
	public int updateshopproduct(String id,String s_id,String name,String number,String date);
	public int product_update_shop(String id,String number);
	public int product_delete_shop(String id,String number);
	//销售
	public List<ShopSale> list_sale();
	public List<ShopSale> listByNameSale(String name);
	public int addshopsale(String id,String name,String numbe,String date);
	public int deleteshopsale(String id,String s_id,String date);
	public int updateshopsale(String id,String s_id,String name,String number,String date);	
	public int sale_update_shop(String id,String number);
	public int sale_delete_shop(String id,String number);	
	/*
	 * 订单
	 */
	public List<Order> list_order();
	public List<Order> list_orderByName(String name);
	public int addorder(String s_id,String o_number,String o_time,String photo,String c_id,int flag);
	public int deleteorder(int id);
	public int updateorder(int o_id,String s_id,String o_number,String o_time,String photo);	
	public int updateorderflag(int o_id,String date);
	/*
	 * 客户
	 */
	public List<Client> list_client();
	public List<Client> list_clientByName(String name);
	public int addclient(String c_id,String c_name,String phone,String address);
	public int deleteclient(String c_id);
	public int updateclient(String c_id,String c_name,String phone,String address);	
	/*
	 * 购买
	 */
	public List<Buy> selectAllBuy();
	public List<Buy> selectBuyByName(String name);
	public int addbuy(String id,String name,String number,String date,String price);
	public int updatebuy(int id,String s_id,String name,String number,String date,String price);
	public int deletebuy(int id);	
}
