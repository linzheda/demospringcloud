package com.linzd.pc.service;

import com.linzd.pc.entity.TempPage;
import com.linzd.pc.entity.User;
import com.linzd.pc.util.ResultUtil;
import org.springframework.stereotype.Repository;


/**
 * 用户接口
 * @author 林哲达
 * Created by linzd on 2016/11/20.
 */
@Repository
public interface UserService {
	
	/**
	 * 根据用户名密码查询用户，用于登录
	 * @param name
	 * @param pwd
	 * @return
	 */
	User getUser(String name, String pwd);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int save(User user);

	/**
	 * 删除
	 * @param uid
	 * @return
	 */
	int deleteById(int uid);

	/**
	 * 更新
	 * @param user
	 * @return
	 */
	int update(User user);
	
	/**
	 * 查找
	 * @param id
	 * @return
	 */
	User findById(int id);
	
	/**
	 * 分页查询
	 * @param page
	 * @param size
	 * @return
	 */
	TempPage<User> findPage(int page, int size);

	/**
	 *   修改密码
	 * @param user
	 * @param oldPassWord
	 * @param newPassWord
	 * @return
	 */
	ResultUtil changePassWord(User user, String oldPassWord, String newPassWord);



}
