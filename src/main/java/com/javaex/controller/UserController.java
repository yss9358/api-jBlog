package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	// 아이디 받아서 중복여부 체크
	@PostMapping("/jblog/users")
	public JsonResult idCheck(@RequestBody UserVo vo) {
		UserVo checkVo =userService.exeCheck(vo.getId());
		if(checkVo == null) {
			return JsonResult.success("사용할 수 있는 아이디 입니다.");
		} else {
			return JsonResult.fail("다른 아이디로 가입해주세요.");
		}
	}
	
	// 회원가입
	@PostMapping("/jblog/users/join")
	public JsonResult join(@RequestBody UserVo vo) {
		int count = userService.exeJoin(vo);
		if(count != -1) {
			return JsonResult.success(count);
		} else {
			return JsonResult.fail("회원가입에 실패했습니다.");
		}
	}
	
	// 로그인
	@PostMapping("/jblog/users/login")
	public JsonResult login(@RequestBody UserVo vo, HttpServletResponse response) {
		UserVo authUser = userService.exeLogin(vo);
		if(authUser != null ) {
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getUserNo());
			return JsonResult.success(authUser);
		} else {
			return JsonResult.fail("로그인 실패");
		}
	}
	
	// 블로그 아이디 표시
	@GetMapping("/jblog/blogs")
	public JsonResult name(@RequestParam String id) {
		UserVo vo = userService.exeGetName(id);
		if(vo != null) {
			return JsonResult.success(vo);
		} else {
			return JsonResult.fail("해당 블로그는 존재하지 않습니다.");
		}
	}
	
}
