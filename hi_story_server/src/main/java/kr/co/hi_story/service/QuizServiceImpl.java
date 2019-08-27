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
import kr.co.hi_story.dto.DataDTO;
import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;
import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.util.ResponseMessage;
import kr.co.hi_story.util.jwt.JwtService;

@Service("QuizService")
public class QuizServiceImpl implements QuizService {

	private QuizDTO qdto;
	private ParticipationDTO pdto;

	@Inject
	QuizDAO quizDAO;

	@Inject
	JwtService jwt;


	@Override
	public MessageDTO createQ(QuizDTO dto) {
		qdto = new QuizDTO();
		qdto.setUid(jwt.getUserID());
		qdto.setRname(dto.getRname());
		qdto.setDname(dto.getDname());
		qdto.setQname(dto.getQname());
		qdto.setCityname(dto.getCityname());
		qdto.setQcontent1(dto.getQcontent1());
		qdto.setQcontent2(dto.getQcontent2());
		qdto.setQcontent3(dto.getQcontent3());
		qdto.setQcontent4(dto.getQcontent4());
		qdto.setQcontent5(dto.getQcontent5());
		qdto.setHcontent(dto.getHcontent());
		qdto.setSolution(dto.getSolution());
		qdto.setAnswer(dto.getAnswer());
		if (quizDAO.createQ(qdto) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}

	@Override
	public MessageDTO deleteQ(QuizDTO dto) {
		qdto = new QuizDTO();
		qdto.setUid(jwt.getUserID());
		qdto.setQid(dto.getQid());
		
		if (quizDAO.deleteQ(qdto) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}

	@Override
	public DataDTO successRate(QuizDTO dto) {
		pdto = new ParticipationDTO();
		ParticipationDTO temp = new ParticipationDTO();
		List<String> rname = quizDAO.rnameList(dto.getCityname());
		pdto.setUid(jwt.getUserID());
		List<ParticipationDTO> result = new ArrayList<>(); 
		for (String object : rname) {
			pdto.setRname(object);
			temp = quizDAO.successRate(pdto);
			if(temp != null) {
				result.add(quizDAO.successRate(pdto));
			}
			
		}
		if (result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}

	@Override
	public MessageDTO editingQuiz(QuizDTO dto) {
		qdto = new QuizDTO();
		qdto.setUid(jwt.getUserID());
		qdto.setRname(dto.getRname());
		qdto.setDname(dto.getDname());
		qdto.setQname(dto.getQname());
		qdto.setCityname(dto.getCityname());
		qdto.setQcontent1(dto.getQcontent1());
		qdto.setQcontent2(dto.getQcontent2());
		qdto.setQcontent3(dto.getQcontent3());
		qdto.setQcontent4(dto.getQcontent4());
		qdto.setQcontent5(dto.getQcontent5());
		qdto.setHcontent(dto.getHcontent());
		qdto.setSolution(dto.getSolution());
		qdto.setAnswer(dto.getAnswer());
		qdto.setQid(dto.getQid());
		
		if (quizDAO.editingQuiz(qdto) == 1) {
			return MessageDTO.resMessage(ResponseMessage.SUCCESS);
		}
		return MessageDTO.resMessage(ResponseMessage.FAIL);
	}

	@Override
	public DataDTO quizQuestion(QuizDTO dto) {
		qdto = new QuizDTO();
		qdto.setUid(jwt.getUserID());
		qdto.setQid(dto.getQid());
		
		QuizDTO result = new QuizDTO();
		result = quizDAO.quizQuestion(qdto);
		
		if (result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}

	@Override
	public MessageDTO takeQuiz(ParticipationDTO dto) {
		pdto = new ParticipationDTO();
		pdto.setUid(jwt.getUserID());
		pdto.setRname(dto.getRname());
		if(quizDAO.takeQuizBefore(pdto)==1) {
			quizDAO.takeQuizStart(pdto);
		}
		
		//true = Á¤´ä
		if(dto.getResult() == 1) {
			quizDAO.takeQuizO(pdto);
		}else {
			quizDAO.takeQuizX(pdto);
		}
		return MessageDTO.resMessage(ResponseMessage.SUCCESS);
	}
	
	@Override
	public DataDTO rnameList(String cityname) {
		
		List<String> result = quizDAO.rnameList(cityname);
		
		if (result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}

	@Override
	public DataDTO cityList() {
		
		List<String> result = quizDAO.cityList();
		
		if (result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}
}
