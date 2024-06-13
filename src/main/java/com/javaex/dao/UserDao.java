package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 아이디 받아서 중복여부 체크
	public UserVo selectById(String id) {
		return sqlSession.selectOne("user.selectById",id);		
	}
	
	// 회원가입
	public int insertUser(UserVo vo) {
		return sqlSession.insert("user.insertUser",vo);
	}
	
	// 블로그 가입
	public int insertBlog(BlogVo vo) {
		return sqlSession.insert("user.insertBlog", vo);
	}
	
	// 로그인
	public UserVo loginByIdPw(UserVo vo) {
		return sqlSession.selectOne("user.loginByIdPw", vo);
	}
	
	// 아이디 찾기
	public UserVo getName(String id) {
		return sqlSession.selectOne("user.getName", id);
	}
}
