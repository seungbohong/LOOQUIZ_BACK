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
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.service.MemberService;

import static kr.co.hi_story.dto.MessageDTO.FAIL_DEFAULT_RES;

@Controller
@RequestMapping
public class MemberController {

	@Inject
	private MemberService memberService;
	
	

	// ���̵� �ߺ� Ȯ��
	@GetMapping("checkID")
	public ResponseEntity checkID(@RequestParam("uid") String uid) {
		try {
			return new ResponseEntity<>(memberService.checkID(uid), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("checkPW")
	public ResponseEntity checkPW(@RequestBody MemberDTO dto) {
		try {
			return new ResponseEntity<>(memberService.checkPW(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ȸ�� ����
	@PostMapping("signUp")
	public ResponseEntity signUp(@RequestBody MemberDTO dto) {
		try {
			return new ResponseEntity<>(memberService.signUp(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// �α���
	@PostMapping("signIn")
	public ResponseEntity signIn(@RequestBody MemberDTO dto,HttpServletResponse response) {
		try {
			return new ResponseEntity<>(memberService.signIn(dto,response), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// �α׾ƿ�
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

	// ���̵� ã��
	@GetMapping("findID")
	public ResponseEntity findID(@RequestParam("uname") String uname,@RequestParam("email") String email) {
		try {
			return new ResponseEntity<>(memberService.findID(uname,email), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ��й�ȣ ã��
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

	// ��й�ȣ ���� 
	@PutMapping("updateMember")
	public ResponseEntity updateMember(@RequestBody MemberDTO dto) {
		try {
			return new ResponseEntity<>(memberService.updateMember(dto), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ȸ�� Ż��
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