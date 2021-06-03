package com.example.demo.utils;

import com.example.demo.pojo.Timetable;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTableContainer {

	public static final String[] WEEK = {"0", "1", "2", "3", "4", "5", "6"};

	public static HashMap<String, Object> getTimeTable() {
		HashMap<String, Object> map = new HashMap<>();
		map.put(WEEK[0], new ArrayList<Timetable>());
		map.put(WEEK[1], new ArrayList<Timetable>());
		map.put(WEEK[2], new ArrayList<Timetable>());
		map.put(WEEK[3], new ArrayList<Timetable>());
		map.put(WEEK[4], new ArrayList<Timetable>());
		map.put(WEEK[5], new ArrayList<Timetable>());
		map.put(WEEK[6], new ArrayList<Timetable>());
		return map;
	}


}
