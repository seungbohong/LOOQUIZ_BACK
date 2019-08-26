package kr.co.hi_story.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;
import kr.co.hi_story.service.MemberService;
import kr.co.hi_story.util.jwt.JwtInterceptor;
import kr.co.hi_story.util.jwt.JwtService;

import static kr.co.hi_story.dto.MessageDTO.FAIL_DEFAULT_RES;

@Controller
@RequestMapping
public class MemberController {

	@Inject
	private MemberService memberService;
	
	

	// 아이디 중복 확인
	@GetMapping("checkID")
	public ResponseEntity checkID(@RequestParam("uid") String uid) {
		try {
			return new ResponseEntity<>(memberService.checkID(uid), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 회원 가입
	@PostMapping("signUp")
	public ResponseEntity signUp(@RequestBody MemberDTO dto) {
		try {
			return new ResponseEntity<>(memberService.signUp(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 로그인
	@PostMapping("signIn")
	public ResponseEntity signIn(@RequestBody MemberDTO dto,HttpServletResponse response) {
		try {
			return new ResponseEntity<>(memberService.signIn(dto,response), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 로그아웃
	@GetMapping("logOut")
	public ResponseEntity logOut(@RequestHeader(value="Authorization") String Authorization,HttpServletResponse response) {
		try {
			System.out.println(1);
			return new ResponseEntity<>(memberService.logOut(Authorization), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 아이디 찾기
	@GetMapping("findID")
	public ResponseEntity findID(@RequestParam("uname") String uname,@RequestParam("email") String email) {
		try {
			return new ResponseEntity<>(memberService.findID(uname,email), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 비밀번호 찾기
	@GetMapping("findPW")
	public ResponseEntity findPW(@RequestParam("email") String email,@RequestParam("uid") String uid) {
		try {
			System.out.println(email);
			System.out.println(uid);
			return new ResponseEntity<>(memberService.findPW(uid,email), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 비밀번호 수정 
	@PutMapping("updateMember")
	public ResponseEntity updateMember(@RequestBody MemberDTO dto) {
		try {
			return new ResponseEntity<>(memberService.updateMember(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 회원 탈퇴
	@DeleteMapping("deleteMem")
	public ResponseEntity deleteMem(@RequestBody MemberDTO dto) {
		try {
			return new ResponseEntity<>(memberService.deleteMem(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}