package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.*;
import com.example.demo.pojo.*;
import com.example.demo.service.CourseService;
import com.example.demo.service.LaboratoryService;
import com.example.demo.service.LoginService;
import com.example.demo.utils.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Rollback(value = false)
class DemoApplicationTests {

	@Autowired
	AdminMapper adminMapper;

	@Autowired
	LoginService loginService;

	@Autowired
	TeacherMapper teacherMapper;

	@Autowired
	TimeTableMapper timeTableMapper;

	@Autowired
	LaboratoryMapper laboratoryMapper;

	@Autowired
	LaboratoryService laboratoryService;

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	CourseService courseService;


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime localDateTime = LocalDateTime.now();


	@Test
	void addTimeTable() {
		int z = 1261;
		for (int i = 1; i <= 18; i++) {
			for (int j = 1; j <= 7; j++) {
				for (int k = 1; k <= 5; k++) {
					Timetable timetable = new Timetable(z, i, j, k, 1, 12, null);
					timeTableMapper.insert(timetable);
					z++;
				}
			}
		}
	}

	@Test
	void contextLoads() {
		QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<Admin>();
		adminQueryWrapper
				.eq("username", "admin")
				.eq("password", "000000");
		Admin admin = adminMapper.selectOne(adminQueryWrapper);
		System.out.println(admin);
	}

	@Test
	void pageTest() {
		QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
		teacherQueryWrapper.like("username", "");
		Page<Teacher> teacherPage = new Page<>(2, 3);
		Page<Teacher> teacherPage1 = teacherMapper.selectPage(teacherPage, teacherQueryWrapper);
		teacherPage1.getRecords().forEach(System.out::println);
		System.out.println(teacherPage1.getSize());
	}

	@Test
	void deleted() {
		Teacher teacher = new Teacher(1, "张三", "123123", 0, "教师");
		teacherMapper.updateById(teacher);
	}


	@Test
	void JsonFormatTest() {
		QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
		adminQueryWrapper
				.eq("username", "root")
				.eq("password", "000000")
				.eq("role", "管理员");

		Admin admin = adminMapper.selectOne(adminQueryWrapper);
		System.out.println(admin);
	}

	@Test
	void test1() {
		Page<Laboratory> objectPage = new Page<>();
		IPage<Laboratory> laboratoryIPage = laboratoryMapper.selectPage(objectPage, null);

		System.out.println(laboratoryIPage.getTotal());
	}

	@Test
	void test2() {
////		File file = new File("");
////		file.delete();
//		String path = "http://localhost:8080/static/imgs/91e71dde-8f69-40e3-83e8-2ee26f1ad3fb_OIP.jpeg";
//		String[] split = path.split("/");
//		System.out.println(split[5]);
//		File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources" + "/static/imgs/" + split[5]);
//		file.delete();


	}

	@Test
	void test3() {
		String courseName = "数据结构";
		String laboratoryName = "实验室1";
		Course course = courseService.courseByName(courseName);
		int id = course.getId();
//		QueryWrapper<Laboratory> wrapper = new QueryWrapper<>();
//		wrapper.eq("laboratory_name", laboratoryName);
//		Laboratory laboratory = laboratoryMapper.selectOne(wrapper);
//		System.out.println(laboratory);
//		System.out.println(course);
		Random random = new Random();
//		int i = random.nextInt(5)+1;
//		System.out.println(i);
		Laboratory idByName = laboratoryService.getIdByName(laboratoryName);
		QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
		timetableQueryWrapper
				.eq("laboratory_id", idByName.getId());
		List<Timetable> timetables = timeTableMapper.selectList(timetableQueryWrapper);
		int g = 0;
		ArrayList<Integer> weekDay = new ArrayList<>();
		weekDay.add(0);
		ArrayList<Integer> lesson = new ArrayList<>();
		lesson.add(0);
		ArrayList<Timetable> timetablesList = new ArrayList<>();
		int r = 0;
		while (g != 5) {
			boolean bool = true;
			for (int i = 1; i <= course.getTimeLength(); i++) {
				while ((r = random.nextInt(7) + 1) != weekDay.get(i - 1)) {
					weekDay.add(r);
				}
			}
			for (int i = 1; i <= course.getWeekLength(); i++) {
				while ((r = random.nextInt(5) + 1) != lesson.get(i - 1)) {
					lesson.add(r);
				}
			}
			r = random.nextInt(18 - course.getWeekNumber()) + 1;
			for (int i = 1; i < weekDay.size(); i++) {
				for (int i1 = 1; i1 < lesson.size(); i1++) {
					timetableQueryWrapper
							.eq("time_length", lesson.get(i1))
							.eq("week_length", weekDay.get(i));
					for (int i2 = 0; i2 < course.getWeekNumber(); i2++) {
						if (timeTableMapper.selectOne(timetableQueryWrapper).getState() == 0) {
							bool = false;
							return;
						}
					}
				}
			}
			if (!bool) {
				continue;
			}
			if (bool) {
				g++;
				System.out.println(g);
			}
		}
		System.out.println(1);
	}

	@Test
	void test4() {
		String courseName = "数字逻辑";
		String laboratoryName = "实验室2";
		Course course = courseService.courseByName(courseName);
		System.out.println(course);
		Random random = new Random();
		Laboratory idByName = laboratoryService.getIdByName(laboratoryName);
		System.out.println(idByName);
		QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
		timetableQueryWrapper
				.eq("laboratory_id", idByName.getId());
		List<Timetable> timetables = timeTableMapper.selectList(timetableQueryWrapper);
//		timetables.forEach(System.out::println);
		ArrayList<Integer> lesson = new ArrayList<>();
		HashSet<Integer> weekDay = new HashSet<>();
		HashMap<String, CourseSelectedList> courseSelectedHashMap = new HashMap<>();
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
			System.out.println(lesson);
			System.out.println(weekDay);
			lesson.clear();
			weekDay.clear();
			int week = random.nextInt(18 - course.getWeekNumber() + 1) + 1;
			System.out.println(week);
			List<Timetable> collect = timetables.stream()
					.filter(timetable -> timetable.getWeek() >= week && timetable.getWeek() <= week + course.getWeekNumber() - 1)
					.filter(timetable -> timetable.getState() == 1)
					.collect(Collectors.toList());
			System.out.println(collect.size());
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
			System.out.println(course.getWeekNumber() * course.getWeekLength() * course.getTimeLength());
			System.out.println(map.size());
			System.out.println(UploadImgUtils.doConcat(map).size());
//			if (UploadImgUtils.doConcat(map).size() == course.getWeekNumber() * course.getWeekLength() * course.getTimeLength()) {
//				System.out.println("asd");-
//				HashMap<Integer, Integer> lessonContainer = new HashMap<>();
//				HashMap<Integer, Integer> weekDayContainer = new HashMap<>();
//				for (int i = 0; i < arrayWeekDay.length; i++) {
//					weekDayContainer.put(i, (int) arrayWeekDay[i]);
//					lessonContainer.put((int) arrayWeekDay[i], (int) arrayLesson[i]);
//				}
//				courseSelectedHashMap.put(Integer.toString(listNumber), CourseSelectedList.builder()
//						.lesson(lessonContainer)
//						.weekDay(weekDayContainer)
//						.week(week).build());
//			}
			listNumber++;
		}
		System.out.println(courseSelectedHashMap);
	}

	@Test
	void test5() {
		UpdateWrapper<Timetable> wrapper = new UpdateWrapper<>();
		wrapper.set("state", 2)
				.set("course_name", "数据结构");
		Timetable build = Timetable.builder().week(1).weekDay(1).lesson(1).laboratoryId(1).state(2).courseName("数字逻辑").build();
//		int update = timeTableMapper.update(build,null);
//		int i = timeTableMapper.updateById(build);

//		System.out.println(i);
		timeTableMapper.updateTimeTable(build);
	}

	@Test
	void test6() {
		HashMap<String, Object> map = new HashMap<>();
		QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
		timetableQueryWrapper.eq("course_name", "数字逻辑");
		List<Timetable> timetableList = timeTableMapper.selectList(timetableQueryWrapper);
		System.out.println(timetableList.size());
		map.put("courseName", timetableList);
		System.out.println(map.size());
	}

}
