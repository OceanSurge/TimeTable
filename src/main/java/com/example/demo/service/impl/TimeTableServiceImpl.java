package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.mapper.TimeTableMapper;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.Laboratory;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.Timetable;
import com.example.demo.service.LaboratoryService;
import com.example.demo.service.TimeTableService;
import com.example.demo.utils.TimeTableContainer;
import com.example.demo.utils.TimeTableQuery;
import com.example.demo.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	LaboratoryService laboratoryService;

	@Autowired
	TimeTableMapper timeTableMapper;

	@Autowired
	TeacherMapper teacherMapper;

	@Autowired
	CourseMapper courseMapper;

	@Override
	public HashMap<String, Object> allCourseByWeekAndLaboratory(TimeTableQuery timeTableQuery) {
		Laboratory idByName = laboratoryService.getIdByName(timeTableQuery.getLaboratory());
		QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
		timetableQueryWrapper
				.eq("week", Integer.parseInt(timeTableQuery.getWeek()))
				.eq("laboratory_id", idByName.getId());
		List<Timetable> timetables = timeTableMapper.selectList(timetableQueryWrapper);
//		timetables.forEach(System.out::println);
		HashMap<String, Object> timeTable = TimeTableContainer.getTimeTable();
		timetables.forEach(timetable -> {
			if (timetable.getWeekDay() == 1) {
				((AbstractList<Timetable>) timeTable.get(TimeTableContainer.WEEK[0])).add(timetable);
			} else if (timetable.getWeekDay() == 2) {
				((AbstractList<Timetable>) timeTable.get(TimeTableContainer.WEEK[1])).add(timetable);
			} else if (timetable.getWeekDay() == 3) {
				((AbstractList<Timetable>) timeTable.get(TimeTableContainer.WEEK[2])).add(timetable);
			} else if (timetable.getWeekDay() == 4) {
				((AbstractList<Timetable>) timeTable.get(TimeTableContainer.WEEK[3])).add(timetable);
			} else if (timetable.getWeekDay() == 5) {
				((AbstractList<Timetable>) timeTable.get(TimeTableContainer.WEEK[4])).add(timetable);
			} else if (timetable.getWeekDay() == 6) {
				((AbstractList<Timetable>) timeTable.get(TimeTableContainer.WEEK[5])).add(timetable);
			} else if (timetable.getWeekDay() == 7) {
				((AbstractList<Timetable>) timeTable.get(TimeTableContainer.WEEK[6])).add(timetable);
			}
		});
		return timeTable;
	}

	@Override
	public boolean changeState(Timetable timeTable) {
		if (timeTable.getState() == 0) {
			timeTable.setState(1);
		} else if (timeTable.getState() == 1 || timeTable.getState() == 2) {
			timeTable.setState(0);
		}
		int update = timeTableMapper.updateById(timeTable);
		if (update == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public HashMap<String, Object> selectCourseDetails(String courseName) {
		HashMap<String, Object> map = new HashMap<>();
//		QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
//		wrapper.eq("id", user.getId());
//		Teacher teacher = teacherMapper.selectOne(wrapper);
//		QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
//		courseQueryWrapper.eq("teacher_id", teacher.getId());
//		List<Course> courses = courseMapper.selectList(courseQueryWrapper);
//		for (Course course : courses) {
//			QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
//			timetableQueryWrapper.eq("course_name", course.getCourseName());
//			List<Timetable> timetableList = timeTableMapper.selectList(timetableQueryWrapper);
//			map.put(course.getCourseName(), timetableList);
//		}
		QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
		timetableQueryWrapper.eq("course_name", courseName);
		List<Timetable> timetableList = timeTableMapper.selectList(timetableQueryWrapper);
		map.put("courseName", timetableList);
		return map;
	}
}
