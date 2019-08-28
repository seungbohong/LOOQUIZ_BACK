package kr.co.hi_story.dto;

import kr.co.hi_story.util.ResponseMessage;
import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

@Data

@Builder

@AllArgsConstructor

public class MessageDTO<T> {
	
	private int message;

	public static <T> MessageDTO<T> resMessage(final int message) {

		return MessageDTO.<T>builder()
				.message(message)
				.build();
	}
	

	public static final MessageDTO FAIL_DEFAULT_RES = new MessageDTO(ResponseMessage.SERVER_ERROR);

}