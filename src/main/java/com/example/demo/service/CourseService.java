package com.example.demo.service;

import com.example.demo.pojo.Course;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.Timetable;
import com.example.demo.utils.CourseSelectedList;
import com.example.demo.utils.TimeTableContainer;
import com.example.demo.utils.TimeTableQuery;
import com.example.demo.utils.User;

import java.util.HashMap;
import java.util.List;

public interface CourseService {

	public List<Course> allCourse(User user);

	public List<Course> allCourseByState(User user);

	public Boolean addCourse(Course course);

	public Course courseByName(String courseName);

	public HashMap<String, Object> selectCourse(String courseName, String laboratoryName);

	public void selectingCourse(CourseSelectedList courseSelectedList, String courseName, String laboratoryName);

	public void deleteCourse(String courseName, List<Timetable> timetables);

}
