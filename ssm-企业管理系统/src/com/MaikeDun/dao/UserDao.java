
package com.MaikeDun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.MaikeDun.bean.Buy;
import com.MaikeDun.bean.Client;
import com.MaikeDun.bean.InviteCode;
import com.MaikeDun.bean.Order;
import com.MaikeDun.bean.Shop;
import com.MaikeDun.bean.ShopProduct;
import com.MaikeDun.bean.ShopSale;
import com.MaikeDun.bean.Staff;


public interface UserDao {
	//ע���¼
	public int add(@Param("id")String id,@Param("name")String name,@Param("password")String password,@Param("sex")String sex,@Param("email")String email,@Param("phone")String phone,@Param("idcard")String idcard,@Param("flag")String flag,@Param("photo")String photo);
	public Staff select(String name);
	//ע�����ѯ
	public InviteCode selectcode(String code);
	//�޸�staff
	public int updatestaffpwd(@Param("id")String id,@Param("password")String password);
	public int updatestaffmessage(@Param("id")String id,@Param("email")String email,@Param("phone")String phone,@Param("address")String address,@Param("QQ")String qq);
	public int updatestaffimg(@Param("id")String id,@Param("img")String img);
	//��Ʒ
	public List<Shop> selectAllShop();
	public List<Shop> selectShopByName(@Param("name")String name);
	public Shop selectShopById(@Param("s_id")String id);
	public int addshop(@Param("id")String id,@Param("name")String name,@Param("number")String number,@Param("price")String price);	
	public int deleteshop(@Param("s_id")String id);
	public int updateshop(@Param("s_id")String id,@Param("s_name")String name,@Param("number")String number,@Param("price")String price);
	public List<Shop> selectShopById1(String id);
	//������Ʒ
	public List<ShopProduct> selectAllShopPro();
	public List<ShopProduct> selectShopByNameProduct(@Param("name")String name);
	public int addshopproduct(@Param("id")String id,@Param("name")String name,@Param("number")String number,@Param("date")String date);
	public int updateshopproduct(@Param("id")String id,@Param("s_id")String s_id,@Param("s_name")String name,@Param("number")String number,@Param("date")String date);
	public int deleteshopproduct(@Param("id")String id);
	public int product_update_shop(@Param("s_id")String id,@Param("number")String number);//���������¼ʱ��ͬʱ��ӿ�棬update
	public int product_delete_shop(@Param("s_id")String id,@Param("number")String number); //ɾ��������¼ʱ��ͬʱɾ����棬update
	//���۲�Ʒ
	public List<ShopSale> selectAllShopSale();
	public List<ShopSale> selectShopByNameSale(@Param("name")String name);
	public int addshopsale(@Param("id")String id,@Param("name")String name,@Param("number")String number,@Param("date")String date);
	public int updateshopsale(@Param("id")String id,@Param("s_id")String s_id,@Param("s_name")String name,@Param("number")String number,@Param("date")String date);
	public int deleteshopsale(@Param("id")String id);	
	public int sale_update_shop(@Param("s_id")String id,@Param("number")String number);//������ۼ�¼ʱ��ͬʱɾ����棬update
	public int sale_delete_shop(@Param("s_id")String id,@Param("number")String number); //ɾ��������¼ʱ��ͬʱ���ӿ�棬update
	/*
	 * ����
	 */
	public List<Order> selectAllOrder();
	public List<Order> selectAllOrderByName(@Param("name")String name);
	public int addorder(@Param("s_id")String s_id,@Param("o_number")String o_number,@Param("o_time")String o_time,@Param("photo")String photo,@Param("c_id")String c_id,@Param("flag")int flag);
	public int deleteorder(@Param("o_id")int id);
	public int updateorder(@Param("o_id")int o_id,@Param("s_id")String s_id,@Param("o_number")String o_number,@Param("o_time")String o_time,@Param("photo")String photo);	
	public int updateorderflag(@Param("publish_time")String publish_time,@Param("o_id")int id);
	/**
	 * �ͻ�
	 */
	public List<Client> selectAllClient();
	public List<Client> selectAllClientByName(@Param("name")String name);
	public int addclient(@Param("c_id")String c_id,@Param("c_name")String c_name,@Param("phone")String phone,@Param("address")String address);
	public int deleteclient(@Param("c_id")String c_id);
	public int updateclient(@Param("c_id")String c_id,@Param("c_name")String c_name,@Param("phone")String phone,@Param("address")String address);	
	/**
	 * ����
	 */
	public List<Buy> selectAllBuy();
	public List<Buy> selectBuyByName(@Param("name")String name);
	public int addbuy(@Param("b_id")String id,@Param("b_name")String name,@Param("number")String number,@Param("date")String date,@Param("price")String price);
	public int updatebuy(@Param("id")int id,@Param("b_id")String s_id,@Param("b_name")String name,@Param("number")String number,@Param("date")String date,@Param("price")String price);
	public int deletebuy(@Param("id")int id);	
}

