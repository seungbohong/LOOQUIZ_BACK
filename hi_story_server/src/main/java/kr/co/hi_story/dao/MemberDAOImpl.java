package kr.co.hi_story.dao;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;


@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	SqlSessionTemplate mybatis;
	
	@Override
	public int checkID(String uid) throws Exception {
		if(mybatis.selectOne("member.checkID",uid)==null) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public int signUp(MemberDTO dto) {
		mybatis.insert("member.signUp", dto);
		return 1;
	}

	@Override
	public MemberDTO signIn(MemberDTO dto) {
		return mybatis.selectOne("member.signIn", dto);
	}

	@Override
	public MemberDTO findID(MemberDTO dto) {
		return mybatis.selectOne("member.findID",dto);
	}
	
	@Override
	public int findPW(MemberDTO dto) {
		mybatis.update("member.findPW",dto);
		return 1;
	}
	
	@Override
	public int updateMember(MemberDTO dto) {
		if(mybatis.selectOne("member.signIn", dto)==null) {
			return 2;
		}
		if(dto.getUpw() == dto.getPw()) {
			return 0;
		}else {
			mybatis.update("member.updateMember",dto);
			return 1;
		}
	}
	@Override
	public int deleteMem(MemberDTO dto) {
		mybatis.delete("member.deleteMem",dto);
		return 1;
	}
	


	
}
