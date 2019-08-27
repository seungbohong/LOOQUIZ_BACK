package kr.co.hi_story.dao;

import java.util.List;

import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;

public interface QuizDAO {
	public int createQ(QuizDTO dto);

	public int deleteQ(QuizDTO dto);
	
	public List<String> rnameList(String cityname);

	public ParticipationDTO successRate(ParticipationDTO dto);

	public int editingQuiz(QuizDTO dto);

	public QuizDTO quizQuestion(QuizDTO dto);

	public int takeQuizBefore(ParticipationDTO dto);

	public int takeQuizStart(ParticipationDTO dto);

	public int takeQuizX(ParticipationDTO dto);

	public int takeQuizO(ParticipationDTO dto);
	
	public List<String> cityList();
}
