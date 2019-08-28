package kr.co.hi_story.dao;

import java.util.List;

import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.dto.UtilDTO;

public interface UtilDAO {
	
	public List<String> corpList(int bid);
	
	public List<Integer> userBadge(String uid);
	
	public UtilDTO badge(int bid);
}
