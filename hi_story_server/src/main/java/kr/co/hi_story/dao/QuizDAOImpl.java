package kr.co.hi_story.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;


@Repository
public class QuizDAOImpl implements QuizDAO{
	
	@Inject
	SqlSessionTemplate mybatis;

	@Override
	public int createQ(QuizDTO dto) {
		mybatis.insert("quiz.createQ", dto);
		return 1;
	}

	@Override
	public int deleteQ(QuizDTO dto) {
		mybatis.delete("quiz.deleteQ",dto);
		return 1;
	}

	@Override
	public List<String> rnameList(String cityname) {
		List<String> result = mybatis.selectList("quiz.rnameList",cityname);
		return result;
	}

	@Override
	public ParticipationDTO successRate(ParticipationDTO dto) {
		ParticipationDTO data = mybatis.selectOne("quiz.successRate",dto);
		return data;
	}
	
	@Override
	public int editingQuiz(QuizDTO dto) {
		mybatis.update("quiz.editingQuiz",dto);
		return 1;
	}

	@Override
	public QuizDTO quizQuestion(QuizDTO dto) {
		QuizDTO data = mybatis.selectOne("quiz.quizQuestion",dto);
		return data;
	}

	public int takeQuizBefore(ParticipationDTO dto) {
		ParticipationDTO p = mybatis.selectOne("quiz.takeQuizBefore",dto);
		if(p != null) {
			return 0; //퀴즈 에 참여한적이 있음
		}else {
			return 1; //퀴즈에 참여한적이 없음 -> takeQuizStart해서 맞춘 갯수 0으로 셋팅
		}
	}
	
	@Override
	public int takeQuizStart(ParticipationDTO dto) {
		mybatis.insert("quiz.takeQuizStart",dto);
		return 1;
	}
	
	@Override
	public int takeQuizX(ParticipationDTO dto) {
		mybatis.update("quiz.takeQuizX",dto);
		return 1;
	}
	@Override
	public int takeQuizO(ParticipationDTO dto) {
		mybatis.update("quiz.takeQuizO",dto);
		return 1;
	}

	@Override
	public List<String> cityList() {
		List<String> result = mybatis.selectList("quiz.cityList");
		return result;
	}
	
//	cityList
//	List<String> result = mybatis.selectList("quiz.rnameList",dto);


	
}
