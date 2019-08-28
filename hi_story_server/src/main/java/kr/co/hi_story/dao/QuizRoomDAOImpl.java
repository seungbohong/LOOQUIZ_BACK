package kr.co.hi_story.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.dto.QuizRoomDTO;


@Repository
public class QuizRoomDAOImpl implements QuizRoomDAO{
	
	@Inject
	SqlSessionTemplate mybatis;

	@Override
	public int makeRoom(QuizRoomDTO dto) {
		mybatis.insert("qr.makeRoom", dto);
		return 1;
	}

	@Override
	public String showCodeNum(QuizRoomDTO dto) {
		String codenum = mybatis.selectOne("qr.showCodeNum",dto);
		return codenum;
	}

	@Override
	public int checkCodeNum(String random) {
		String result = mybatis.selectOne("qr.checkCodeNum",random);
		if(result != null) {
			return 0; // 쓰던거라 안됨
		}else {
			return 1; // 사용해도됨
		}
	}

	
	
}
