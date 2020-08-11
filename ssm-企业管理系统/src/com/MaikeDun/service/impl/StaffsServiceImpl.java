package com.MaikeDun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MaikeDun.bean.Staffs;
import com.MaikeDun.bean.UpdateStaff;
import com.MaikeDun.dao.StaffsDao;
@Service
public class StaffsServiceImpl implements com.MaikeDun.service.StaffsService {

	@Autowired
	private StaffsDao staffDao;
	@Override
	public List<Staffs> selectStaff() {
		// TODO Auto-generated method stub
		return staffDao.selectStaff();
	}

	@Override
	public int addStaff(Staffs staff) {
		// TODO Auto-generated method stub
		return staffDao.addStaff(staff);
	}

	@Override
	public List<Staffs> selectStaffById(int id) {
		// TODO Auto-generated method stub
		return staffDao.selectStaffById(id);
	}

	@Override
	public void updateStaff(UpdateStaff us){
		// TODO Auto-generated method stub
		staffDao.updateStaff(us);;
	}
	public void updateStaffPhoto(Staffs staff) {
		staffDao.updateStaffPhoto(staff);
	}
	public int deleteStaff(int id) {
		return staffDao.deleteStaff(id);
	}
}
