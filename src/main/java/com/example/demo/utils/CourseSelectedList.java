package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSelectedList extends Object{
	private HashMap<Integer, Integer> weekDay;

	private HashMap<Integer, Integer> lesson;

	private int week;
}
