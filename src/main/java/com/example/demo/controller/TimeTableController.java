package com.example.demo.controller;

import com.example.demo.pojo.Timetable;
import com.example.demo.service.TimeTableService;
import com.example.demo.utils.ResultVO;
import com.example.demo.utils.TimeTableQuery;
import com.example.demo.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TimeTableController {

	@Autowired
	TimeTableService timeTableService;

	@RequestMapping("/allCourseByWeekAndLaboratory")
	public ResultVO allCourseByWeekAndLaboratory(@RequestBody TimeTableQuery timeTableQuery) {
		HashMap<String, Object> map = timeTableService.allCourseByWeekAndLaboratory(timeTableQuery);
		return ResultVO.success(map);
	}

	@RequestMapping("/changeState")
	public ResultVO changeState(@RequestBody Timetable timeTable) {
		boolean changeState = timeTableService.changeState(timeTable);
		if (changeState) {
			return ResultVO.builder().code(200).message("success").build();
		} else {
			return ResultVO.builder().code(500).message("error").build();
		}
//		return null;
	}

	@RequestMapping("/selectCourseDetails")
	public ResultVO selectCourseDetails(@RequestParam("courseName") String courseName) {
		System.out.println(courseName);
		HashMap<String, Object> map = timeTableService.selectCourseDetails(courseName);
		return ResultVO.success(map);
//		return null;
	}


}
