package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {
	private int id;
	private int week;
	private int weekDay;
	private int lesson;
	private int state;
	private int laboratoryId;
	private String  courseName;
}
