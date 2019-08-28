package kr.co.hi_story.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.hi_story.dto.ParticipationDTO;
import kr.co.hi_story.dto.QuizDTO;
import kr.co.hi_story.dto.UtilDTO;


@Repository
public class UtilDAOImpl implements UtilDAO{
	
	@Inject
	SqlSessionTemplate mybatis;

	@Override
	public List<String> corpList(int bid) {
		List<String> result = mybatis.selectList("util.corpList",bid);
		return result;
	}

	@Override
	public List<Integer> userBadge(String uid) {
		List<Integer> result = mybatis.selectList("util.userBadge",uid);
		return result;
	}

	@Override
	public UtilDTO badge(int bid) {
		UtilDTO result = mybatis.selectOne("util.badge",bid);
		return result;
	}

	
}
