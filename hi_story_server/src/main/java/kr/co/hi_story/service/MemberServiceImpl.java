package kr.co.hi_story.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.hi_story.dao.MemberDAO;
import kr.co.hi_story.dto.DataDTO;
import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;
import kr.co.hi_story.util.ResponseMessage;
import kr.co.hi_story.util.jwt.JwtService;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	private MemberDTO mdto;
	
	@Autowired
	private JavaMailSender mailSender;

	@Inject
	MemberDAO memberDAO;

	@Inject
	JwtService jwt;

	@Override
	public MessageDTO checkID(String uid) throws Exception {
		if (memberDAO.checkID(uid) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}

	@Override
	public MessageDTO signUp(MemberDTO dto) {
		if (memberDAO.signUp(dto) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}

	@Override
	public DataDTO signIn(MemberDTO dto, HttpServletResponse response) {
		mdto = memberDAO.signIn(dto);
		Map<String, Object> map = new HashMap();
		map.put("uid", mdto.getUid());
		map.put("uname", mdto.getUname());
		map.put("email", mdto.getEmail());
		if (mdto != null) {
			String token = jwt.create("userID", dto, "User");
			response.setHeader("Authorization", token);
//			mdto.setUid(token);
			System.out.println(token);
			return DataDTO.resData(ResponseMessage.SUCCESS, map);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}

	@Override
	public MessageDTO logOut(String auth) {
		String token = auth;

		if (jwt.logout(token) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		} else {
			return MessageDTO.resMessage(ResponseMessage.FAIL);
		}
	}

	@Override
	public DataDTO findID(String uname, String email) {
		MemberDTO param = new MemberDTO();
		param.setUname(uname);
		param.setEmail(email);
		mdto = memberDAO.findID(param);
		if (mdto != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, mdto);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}

	@Override
	public MessageDTO findPW(String uid, String email) {
		System.out.println(2);
		String setfrom = "kimhyungtaik@gmail.com";
		String tomail = email; // 받는 사람 이메일
		String content = jwt.create("pw", uid, "findPW").substring(0, 10); // 내용
		System.out.println(content);
		
		MemberDTO temp = new MemberDTO();
		
		temp.setEmail(email);
		System.out.println(3);
		temp.setUid(uid);
		temp.setUpw(content);
		System.out.println("**********************"+temp.getUid());
		int result = memberDAO.findPW(temp);
		System.out.println("**************"+result);
		if (result == 1) {
			System.out.println(4);
			SimpleMailMessage message = new SimpleMailMessage();
//				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			message.setSubject("비밀번호변경");
			message.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			message.setTo(tomail); // 받는사람 이메일
			message.setText(content); // 메일 내용

			mailSender.send(message);
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		} else {
			System.out.println(5);
			return MessageDTO.resMessage(ResponseMessage.FAIL);
		}
	}

	

	@Override
	public MessageDTO updateMember(MemberDTO dto) {
		mdto.setUid(jwt.getUserID());
		mdto.setPw(dto.getPw());
		mdto.setUpw(dto.getUpw());
		int result = memberDAO.updateMember(mdto);
		if (result == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		} else if (result == 0) {
			return MessageDTO.resMessage(ResponseMessage.FAIL);
		} else {
			return MessageDTO.resMessage(2);
		}
	}

	@Override
	public MessageDTO deleteMem(MemberDTO dto) {
		mdto.setUid(jwt.getUserID());
		System.out.println(jwt.isUsable(mdto.getUid()));
		mdto.setPw(dto.getPw());
		if (mdto.getUid() != null) {
			if (memberDAO.deleteMem(mdto) == 1) {
				return MessageDTO.resMessage(ResponseMessage.SUCCESS);
			}
			return MessageDTO.resMessage(ResponseMessage.FAIL);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}

}
