package kr.co.hi_story.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import kr.co.hi_story.dao.QuizDAO;
import kr.co.hi_story.dao.QuizRoomDAO;
import kr.co.hi_story.dto.DataDTO;
import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;
import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.dto.QuizRoomDTO;
import kr.co.hi_story.util.ResponseMessage;
import kr.co.hi_story.util.jwt.JwtService;

@Service("QuizRoomService")
public class QuizRoomServiceImpl implements QuizRoomService {

	private QuizRoomDTO qrdto;

	@Inject
	QuizRoomDAO quizroomDAO;

	@Inject
	MemberDAO memberDAO;
	
	@Inject
	JwtService jwt;

	@Override
	public MessageDTO makeRoom(QuizRoomDTO dto) {
		qrdto = new QuizRoomDTO();
		String codenum = dto.getCodenum();
		while(true) {
			// String codenum = dto.getCodenum();
			if(quizroomDAO.checkCodeNum(codenum)==1) {
				qrdto.setCodenum(codenum);
				break;
			}
		}
		qrdto.setUid(jwt.getUserID());
		qrdto.setQrname(dto.getQrname());
		qrdto.setEndtime(dto.getEndtime());
		
		if(quizroomDAO.makeRoom(qrdto) ==1 && quizroomDAO.roomUserInit(qrdto)==1) {
			// String result = quizroomDAO.showCodeNum(qrdto);
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
			//return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
		//return DataDTO.resData(ResponseMessage.FAIL, null);
	}
	
	@Override
	public MessageDTO deleteRoom(QuizRoomDTO dto) {
		qrdto = new QuizRoomDTO();
		qrdto.setUid(jwt.getUserID());
		qrdto.setCodenum(dto.getCodenum());
		
		if (quizroomDAO.deleteRoom(qrdto) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}
	
	@Override
	public MessageDTO enterRoom(QuizRoomDTO dto) {
		qrdto = new QuizRoomDTO();
		qrdto.setUid(jwt.getUserID());
		qrdto.setCodenum(dto.getCodenum());
		
		if (quizroomDAO.enterRoom(qrdto) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}
	
	@Override
	public DataDTO makeRoomSearch() {
		qrdto = new QuizRoomDTO();
		qrdto.setUid(jwt.getUserID());
		List<QuizRoomDTO> result = quizroomDAO.makeRoomSearch(qrdto);
		if(result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}
	
	@Override
	public DataDTO participateRoom() {
		qrdto = new QuizRoomDTO();
		qrdto.setUid(jwt.getUserID());
		List<String> codenum = quizroomDAO.participateRoom(qrdto);
		List<QuizRoomDTO> result = new ArrayList<>();
		for(String object:codenum) {
			System.out.println(object);
			result.add(quizroomDAO.participateRoomInfo(object));
		}
		if(result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}
	
	@Override
	public DataDTO searchMem(String codenum) {
		List<String> uid = quizroomDAO.uidList(codenum);
		List<MemberDTO> result = new ArrayList<>(); 
		for (String object : uid) {
			result.add(memberDAO.memInfo(object));
		}
		if (result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}
}
