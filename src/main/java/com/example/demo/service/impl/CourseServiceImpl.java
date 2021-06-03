package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.mapper.TimeTableMapper;
import com.example.demo.pojo.Course;
import com.example.demo.pojo.Laboratory;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.Timetable;
import com.example.demo.service.CourseService;
import com.example.demo.service.LaboratoryService;
import com.example.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	LaboratoryService laboratoryService;

	@Autowired
	TimeTableMapper timeTableMapper;

	@Override
	public List<Course> allCourse(User user) {
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("teacher_id", user.getId());
		List<Course> courses = courseMapper.selectList(wrapper);
		return courses;
	}

	@Override
	public Boolean addCourse(Course course) {
		course.setSummaryTime(course.getTimeLength() * course.getWeekLength() * course.getWeekNumber());
		int insert = courseMapper.insert(course);
		if (insert == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Course courseByName(String courseName) {
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("course_name", courseName);
		Course course = courseMapper.selectOne(wrapper);
		return course;
	}

	@Override
	public HashMap<String, Object> selectCourse(String courseName, String laboratoryName) {
		Course course = courseByName(courseName);
		Random random = new Random();
		Laboratory idByName = laboratoryService.getIdByName(laboratoryName);
		QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
		timetableQueryWrapper
				.eq("laboratory_id", idByName.getId());
		List<Timetable> timetables = timeTableMapper.selectList(timetableQueryWrapper);
//		timetables.forEach(System.out::println);
		ArrayList<Integer> lesson = new ArrayList<>();
		HashSet<Integer> weekDay = new HashSet<>();
		HashMap<String, Object> courseSelectedHashMap = new HashMap<>();
		int listNumber = 0;
		while (listNumber < 10 && courseSelectedHashMap.size() < 5) {
			while (lesson.size() < course.getWeekLength()) {
				lesson.add(random.nextInt(5 - course.getTimeLength() + 1) + 1);
			}
			while (weekDay.size() < course.getWeekLength()) {
				weekDay.add(random.nextInt(7 - course.getWeekLength() + 1) + 1);
			}
			Object[] arrayWeekDay = weekDay.toArray();
			Object[] arrayLesson = lesson.toArray();
			lesson.clear();
			weekDay.clear();
			int week = random.nextInt(18 - course.getWeekNumber() + 1) + 1;
			List<Timetable> collect = timetables.stream()
					.filter(timetable -> timetable.getWeek() >= week && timetable.getWeek() <= week + course.getWeekNumber() - 1)
					.filter(timetable -> timetable.getState() == 1)
					.collect(Collectors.toList());
			HashMap<Integer, Stream<Timetable>> map = new HashMap<>();


			int index = 0;
			for (int i = 0; i < arrayWeekDay.length; i++) {
				for (int i1 = 0; i1 < course.getTimeLength(); i1++) {
					final int a = i;
					final int b = i1;
					map.put(index, collect.stream()
							.filter(timetable -> timetable.getLesson() == (int) arrayLesson[a] + b && timetable.getWeekDay() == (int) arrayWeekDay[a]));
					index++;
				}
			}

			if (UploadImgUtils.doConcat(map).size() == course.getWeekNumber() * course.getWeekLength() * course.getTimeLength()) {
				HashMap<Integer, Integer> lessonContainer = new HashMap<>();
				HashMap<Integer, Integer> weekDayContainer = new HashMap<>();
				for (int i = 0; i < arrayWeekDay.length; i++) {
					weekDayContainer.put(i, (int) arrayWeekDay[i]);
					lessonContainer.put((int) arrayWeekDay[i], (int) arrayLesson[i]);
				}
				courseSelectedHashMap.put(Integer.toString(listNumber), CourseSelectedList.builder()
						.lesson(lessonContainer)
						.weekDay(weekDayContainer)
						.week(week).build());
			}
			listNumber++;
		}
		return courseSelectedHashMap;
	}

	@Override
	public void selectingCourse(CourseSelectedList courseSelectedList, String courseName, String laboratoryName) {
		Course course = courseByName(courseName);
		course.setState(1);
		Laboratory idByName = laboratoryService.getIdByName(laboratoryName);
		courseMapper.updateById(course);
		for (int i = courseSelectedList.getWeek(); i < courseSelectedList.getWeek() + course.getWeekNumber(); i++) {
			for (int i1 = 0; i1 < course.getWeekLength(); i1++) {
				for (int i2 = 0; i2 < course.getTimeLength(); i2++) {
					Timetable build = Timetable.builder()
							.week(i)
							.weekDay(courseSelectedList.getWeekDay().get(i1))
							.lesson(courseSelectedList.getLesson().get(courseSelectedList.getWeekDay().get(i1)) + i2)
							.state(2)
							.courseName(course.getCourseName())
							.laboratoryId(idByName.getId())
							.build();
					timeTableMapper.updateTimeTable(build);
				}
			}
		}
	}

	@Override
	public List<Course> allCourseByState(User user) {
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("teacher_id", user.getId())
				.eq("state", 1);
		List<Course> courses = courseMapper.selectList(wrapper);
		return courses;
	}

	@Override
	public void deleteCourse(String courseName, List<Timetable> timetables) {
		for (Timetable timetable : timetables) {
			timetable.setState(1);
			timetable.setCourseName(null);
			timeTableMapper.updateTimeTable(timetable);
		}
		Course course = courseByName(courseName);
		course.setState(0);
		courseMapper.updateById(course);
	}
}
