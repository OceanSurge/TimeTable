package com.example.demo.controller;

import com.example.demo.mapper.CourseMapper;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.Timetable;
import com.example.demo.service.CourseService;
import com.example.demo.utils.CourseSelectedList;
import com.example.demo.utils.ResultVO;
import com.example.demo.utils.TimeTableQuery;
import com.example.demo.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping("/allCourse")
	public ResultVO allCourse(@RequestBody User user) {
		HashMap<String, Object> map = new HashMap<>();
		List<Course> courses = courseService.allCourse(user);
		map.put("courses", courses);
		return ResultVO.success(map);
	}

	@RequestMapping("/addCourse")
	public ResultVO addCourse(@RequestBody Course course) {
		Boolean aBoolean = courseService.addCourse(course);
		if (aBoolean) {
			return ResultVO.builder().code(200).message("success").build();
		} else {
			return ResultVO.builder().code(500).message("error").build();
		}
//		return null;
	}

	@RequestMapping("/courseByName")
	public ResultVO courseByName(@RequestParam("course") String courseName) {
		HashMap<String, Object> map = new HashMap<>();
		Course course = courseService.courseByName(courseName);
		map.put("course", course);
		return ResultVO.success(map);
	}

	@RequestMapping("/selectCourse")
	public ResultVO selectCourse(@RequestParam("courseName") String courseName, @RequestParam("laboratoryName") String laboratoryName) {
		HashMap<String, Object> stringCourseSelectedListHashMap = courseService.selectCourse(courseName, laboratoryName);
		if (stringCourseSelectedListHashMap == null) {
			return ResultVO.error(500, "error");
		} else {
			return ResultVO.success(stringCourseSelectedListHashMap);
		}
	}

	@RequestMapping("/selectingCourse")
	public ResultVO selectingCourse(@RequestBody CourseSelectedList courseSelectedList, @RequestParam("course") String courseName, @RequestParam("laboratory") String laboratoryName) {
		System.out.println(courseSelectedList);
		courseService.selectingCourse(courseSelectedList, courseName, laboratoryName);
		return null;
	}

	@RequestMapping("/selectCourseByName")
	private ResultVO selectCourseByName(@RequestParam("courseName") String courseName) {
		Course course = courseService.courseByName(courseName);
		HashMap<String, Object> map = new HashMap<>();
		map.put("course", course);
		return ResultVO.success(map);
	}

	@RequestMapping("/allCourseByState")
	public ResultVO allCourseByState(@RequestBody User user) {
		HashMap<String, Object> map = new HashMap<>();
		List<Course> courses = courseService.allCourseByState(user);
		map.put("courses", courses);
		return ResultVO.success(map);
	}

	@RequestMapping("/deleteCourse")
	private void deleteCourse(@RequestParam("courseName") String courseName, @RequestBody List<Timetable> timetables) {
		courseService.deleteCourse(courseName, timetables);
	}

}
