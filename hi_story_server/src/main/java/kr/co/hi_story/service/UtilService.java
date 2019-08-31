package kr.co.hi_story.service;


import kr.co.hi_story.dto.DataDTO;
import kr.co.hi_story.dto.MessageDTO;
import kr.co.hi_story.dto.UtilDTO;

public interface UtilService {

	public DataDTO corpList(int bid);

	public DataDTO badge();

	public MessageDTO getUserBadge(UtilDTO dto);

	public DataDTO storeList();
}
