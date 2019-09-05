package kr.co.hi_story.service;


import kr.co.hi_story.dto.DataDTO;
import kr.co.hi_story.dto.MessageDTO;
import kr.co.hi_story.dto.QuizRoomDTO;


public interface QuizRoomService {
	public MessageDTO  makeRoom(QuizRoomDTO dto);

	public MessageDTO deleteRoom(QuizRoomDTO dto);

	public MessageDTO enterRoom(QuizRoomDTO dto);

	public DataDTO makeRoomSearch();

	public DataDTO participateRoom();

	public DataDTO searchMem(String codenum);
	
	
}
