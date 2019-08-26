package kr.co.hi_story.dto;

import kr.co.hi_story.util.ResponseMessage;
import kr.co.hi_story.util.StatusCode;
import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

@Data

@Builder

@AllArgsConstructor

public class DataDTO<T> {


	private T data;
	
	private int message;


	public DataDTO(final int message,final T t) {
		this.message = message;
		this.data = t;
	}

	
	public static <T> DataDTO<T> resData(final int message, final T t) {
		return DataDTO.<T>builder()
				.data(t)
				.message(message)
				.build();

	}

}