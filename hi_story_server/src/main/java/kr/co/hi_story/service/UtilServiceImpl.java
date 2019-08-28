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
import kr.co.hi_story.dao.UtilDAO;
import kr.co.hi_story.dto.DataDTO;
import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;
import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.dto.UtilDTO;
import kr.co.hi_story.util.ResponseMessage;
import kr.co.hi_story.util.jwt.JwtService;

@Service("UtilService")
public class UtilServiceImpl implements UtilService {

	private UtilDTO udto;

	@Inject
	UtilDAO utilDAO;

	@Inject
	JwtService jwt;

	@Override
	public DataDTO corpList(int bid) {
		List<String> result = utilDAO.corpList(bid);

		if (result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}
		return DataDTO.resData(ResponseMessage.FAIL, null);
	}

	@Override
	public DataDTO badge() {
		List<Integer> userBadge = utilDAO.userBadge(jwt.getUserID());
		List<UtilDTO> result = new ArrayList<>();
		for (int object : userBadge) {
			result.add(utilDAO.badge(object));
		}
		if (result != null) {
			return DataDTO.resData(ResponseMessage.SUCCESS, result);
		}else {
			return DataDTO.resData(ResponseMessage.FAIL, null);
		}
		
	}
}
