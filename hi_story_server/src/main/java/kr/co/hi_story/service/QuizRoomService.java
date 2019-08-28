package kr.co.hi_story.service;

public interface QuizRoomService {
	public searchMem(QuizRoomDTO dto);
	
	public enterRoom(QuizRoomDTO dto);
	
	public deleteRoom(QuizRoomDTO dto);
	
	public makeRoomSearch(QuizRoomDTO dto);
	
	public participateRoom(QuizRoomDTO dto);
	
	public regionQuizList(QuizRoomDTO dto);
	
	public makeRoom(QuizRoomDTO dto);
}
