package com.linzd.pc.service.impl;

import com.linzd.pc.dao.BaseDao;
import com.linzd.pc.entity.TempPage;
import com.linzd.pc.entity.User;
import com.linzd.pc.service.UserService;
import com.linzd.pc.util.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户业务
 * @author lzd
 * Created by linzd on 2016/11/20.
 */
@Service
public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	@Resource(name = "baseDaoImpl")
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public User getUser(String name, String pwd) {
		User user=new User();
		user.setName(name);
		user.setPassword(pwd);
		List<User> list=this.baseDao.findAll(user, "login");
		return 	list.get(0);
	}

	@Override
	public int save(User user) {
		int i=this.baseDao.add(user, "save");
		return i;
	}

	@Override
	public int deleteById(int uid) {
		User user=new User();
		user.setId(uid);
		int result=this.baseDao.delete(user, "deleteById");
		return result;
	}

	@Override
	public int update(User user) {
		int result=this.baseDao.update(user, "update");
		return result;
	}

	@Override
	public User findById(int uid) {
		User user=new User();
		user.setId(uid);
		List<User> list=this.baseDao.findAll(user, "findById");
		return list.size()>0?list.get(0):null;
	}

	@Override
	public TempPage<User> findPage(int page,int size) {
		Map<String,Integer>map=new HashMap<String,Integer>();
		map.put("oage", page);
		map.put("size", size);
		List<TempPage>list=this.baseDao.findAll(new User(), map, "findPage");
		return list.size()>0?list.get(0):null;
	}

	@Override
	public ResultUtil changePassWord(User user, String oldPassWord, String newPassWord) {
		ResultUtil ru=new ResultUtil();
		if(user.getPassword().equals(oldPassWord)){
			user.setPassword(newPassWord);
			int result=this.baseDao.update(user, "update");
			if(result>0){
				ResultUtil.success("修改密码成功");
			}else{
				ResultUtil.error("修改密码失败");
			}
		}else{
			ResultUtil.error("原密码错误");
		}
		return ResultUtil.success();
	}




}
