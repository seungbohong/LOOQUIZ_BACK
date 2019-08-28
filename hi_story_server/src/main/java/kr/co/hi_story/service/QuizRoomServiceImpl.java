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
	JwtService jwt;

	@Override
	public DataDTO makeRoom(QuizRoomDTO dto) {
		qrdto = new QuizRoomDTO();
		
		int random=0;
		while(true) {
			random = (int) (Math.random()*1000+1);
			String codenum = dto.getQrname() + "#"+String.valueOf(random);
			if(quizroomDAO.checkCodeNum(codenum)==1) {
				qrdto.setCodenum(codenum);
				break;
			}
		}
		qrdto.setUid(jwt.getUserID());
		qrdto.setQrname(dto.getQrname());
		qrdto.setEndtime(dto.getEndtime());
		
		if(quizroomDAO.makeRoom(qrdto) ==1 ) {
			String result = quizroomDAO.showCodeNum(qrdto);
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}
	
}
