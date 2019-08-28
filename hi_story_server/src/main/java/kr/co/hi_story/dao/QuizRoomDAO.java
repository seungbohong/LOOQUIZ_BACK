package kr.co.hi_story.dao;

import java.util.List;

import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.dto.QuizRoomDTO;

public interface QuizRoomDAO {
	public int makeRoom (QuizRoomDTO dto);
	
	public String showCodeNum(QuizRoomDTO dto);
	
	public int checkCodeNum(String random);
	

}
