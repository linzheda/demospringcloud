package com.linzd.mobile.service.impl;

import com.linzd.mobile.dao.BaseDao;
import com.linzd.mobile.entity.TempPage;
import com.linzd.mobile.entity.User;
import com.linzd.mobile.service.UserService;
import com.linzd.mobile.util.ResultUtil;
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
		User user2=(User)this.baseDao.findOne(user, "login");
		return 	user2;
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

	@Override
	public Map<String, Object> test() {
		return this.baseDao.findMap(new User(),new HashMap(),"id","test");
	}

	@Override
	public List<Map<String, Object>> test2() {
		return this.baseDao.findAll(new User(),new HashMap(),"test");
	}


}
