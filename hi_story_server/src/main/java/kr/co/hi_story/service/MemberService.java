package kr.co.hi_story.service;


import javax.servlet.http.HttpServletResponse;

import kr.co.hi_story.dto.DataDTO;
import kr.co.hi_story.dto.MemberDTO;
import kr.co.hi_story.dto.MessageDTO;


public interface MemberService {
	public MessageDTO checkID(String uid) throws Exception;
	
	public MessageDTO signUp(MemberDTO dto);
	
	public DataDTO signIn(MemberDTO dto,HttpServletResponse response);

	public DataDTO findID(String uname,String email);
	
	public MessageDTO findPW(String uid,String email);

	public MessageDTO updateMember(MemberDTO dto);

	public MessageDTO deleteMem(MemberDTO dto);
	
	public MessageDTO logOut(String Authorization);
}
