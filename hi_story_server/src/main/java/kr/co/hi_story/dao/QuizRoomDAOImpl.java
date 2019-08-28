package kr.co.hi_story.dao;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class QuizRoomDAOImpl implements QuizRoomDAO {
	
	@Inject
	SqlSessionTemplate mybatis;

}
