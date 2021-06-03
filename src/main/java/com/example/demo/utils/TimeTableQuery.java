package com.example.demo.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TimeTableQuery {
	private int id;
	private String Laboratory;
	private String week;
	private int weekDay;
	private int lesson;
}
