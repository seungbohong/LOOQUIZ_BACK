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
	public int checkCodeNum(String codenum) {
		System.out.println(codenum);
		String result = mybatis.selectOne("qr.checkCodeNum",codenum);
		System.out.println(result);
		if(result != null) {
			return 0; // 쓰던거라 안됨
		}else {
			return 1; // 사용해도됨
		}
	}

	@Override
	public int roomUserInit(QuizRoomDTO dto) {
		mybatis.selectOne("qr.roomUserInit",dto);
		return 1;
	}
	
	@Override
	public int deleteRoom(QuizRoomDTO dto) {
		mybatis.delete("qr.deleteRoom",dto);
		return 1;
	}
	
	@Override
	public int enterRoom(QuizRoomDTO dto) {
		mybatis.insert("qr.enterRoom", dto);
		return 1;
	}
	
	@Override
	public List<QuizRoomDTO> makeRoomSearch(QuizRoomDTO dto) {
		return mybatis.selectList("qr.makeRoomSearch", dto);
	}
	
	@Override
	public List<String> participateRoom(QuizRoomDTO dto) {
		return mybatis.selectList("qr.participateRoom", dto);
	}
	
	@Override
	public QuizRoomDTO participateRoomInfo(String codenum) {
		return mybatis.selectOne("qr.participateRoomInfo", codenum);
	}
	
	@Override
	public List<String> uidList(String codenum) {
		List<String> result = mybatis.selectList("qr.uidList",codenum);
		return result;
	}

}
