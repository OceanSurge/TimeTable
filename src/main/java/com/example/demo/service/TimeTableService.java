package com.example.demo.service;

import com.example.demo.pojo.Timetable;
import com.example.demo.utils.TimeTableQuery;
import com.example.demo.utils.User;

import java.util.HashMap;

public interface TimeTableService {
	public HashMap<String, Object> allCourseByWeekAndLaboratory(TimeTableQuery timeTableQuery);

	public boolean changeState(Timetable timeTable);

	public HashMap<String,Object> selectCourseDetails(String courseName);
}
