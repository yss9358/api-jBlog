package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	// 아이디 받아서 중복여부 체크
	public UserVo exeCheck(String id) {
		return userDao.selectById(id);
	}
	
	// 회원가입 - 회원정보 추가 + 블로그 추가
	public int exeJoin(UserVo vo) {
		// 회원가입먼저
		int count = userDao.insertUser(vo);
		
		// 블로그 기본제목
		String title = vo.getId() + "의 블로그입니다."; 
		
		// 블로그 주소 
		String url = "http://localhost:8080/jblog/" + vo.getId();
		
		// 회원가입한아이디 + 블로그 기본제목 + 블로그 주소 묶기
		BlogVo blogVo = new BlogVo(vo.getId(), title, url);
		
		// 회원가입이 완료되면 회원가입한아이디 + 블로그 기본제목 + 블로그 주소 묶어서 추가
		if(count == 1) { // 회원가입 완료 + 블로그 가입완료 되면 리턴 1
			return userDao.insertBlog(blogVo); 
		} else { // 가입에 실패하면 리턴 -1
			return -1;
		}
	}
	
	// 로그인
	public UserVo exeLogin(UserVo vo) {
		UserVo authUser = userDao.loginByIdPw(vo);
		if(authUser != null) {
			return authUser;
		} else {
			UserVo idCheck = userDao.selectById(vo.getId());
			if(idCheck != null) {
				idCheck.setPassword("-1");
				return idCheck;
			} else {
				return null;
			}
		}
	}
	
	// 아이디 찾기
	
	public UserVo exeGetName(String id) {
		UserVo vo = userDao.getName(id);
		if(vo != null) {
			return vo;
		} else {
			return null;
		}
	}
}
