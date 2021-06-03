package com.example.demo.utils;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class ResultVO {

	private int code;
	private String message;
	private HashMap<String, Object> data;

	public static ResultVO success(HashMap<String, Object> data) {
		return ResultVO.builder().code(200).data(data).build();
	}

	public static ResultVO error(int code, String message) {
		return ResultVO.builder().code(code).message(message).build();
	}

}
