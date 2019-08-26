package kr.co.hi_story.dao;

import javax.servlet.http.HttpServletResponse;

import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;

public interface MemberDAO {
	public int checkID(String uid) throws Exception;
	
	public int signUp(MemberDTO dto);
	
	public MemberDTO signIn(MemberDTO dto);

	public MemberDTO findID(MemberDTO dto);
	
	public int findPW(MemberDTO dto);

	public int updateMember(MemberDTO dto);

	public int deleteMem(MemberDTO dto);
}
