package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.TeacherService;
import com.example.demo.utils.QueryInfo;
import com.example.demo.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {

	@Autowired
	TeacherService teacherService;

	@Autowired
	TeacherMapper teacherMapper;

	@RequestMapping("/allTeacher")
	public ResultVO allUser(@RequestBody QueryInfo queryInfo) {
		HashMap<String, Object> map = new HashMap<>();
		Page<Teacher> teacherPage = teacherService.selectAllTeacher(queryInfo);
		map.put("teachers", teacherPage.getRecords());
		map.put("number", teacherPage.getTotal());
		return ResultVO.success(map);
	}

	@RequestMapping("/updateState")
	public ResultVO updateState(@RequestBody Teacher teacher) {
		HashMap<String, Object> map = new HashMap<>();
		Boolean aBoolean = teacherService.updateState(teacher);
		if (aBoolean) {
			return ResultVO.builder().message("成功").build();
		} else {
			return ResultVO.builder().message("失败").build();
		}
	}

	@RequestMapping("/deleteTeacher")
	public ResultVO deleteTeacher(@RequestParam("id") int id) {
		HashMap<String, Object> map = new HashMap<>();
		if (teacherService.deleteTeacher(id)) {
			return ResultVO.builder().message("成功").build();
		} else {
			return ResultVO.builder().message("失败").build();
		}
	}

	@RequestMapping("/getTeacherById")
	public ResultVO getTeacherById(@RequestParam("id") int id) {
		HashMap<String, Object> map = new HashMap<>();
		Teacher teacherById = teacherService.getTeacherById(id);
		map.put("teacherById", teacherById);
		return ResultVO.success(map);
	}

	@RequestMapping("/TeacherSubmit")
	public ResultVO TeacherSubmit(@RequestBody Teacher teacher) {
		HashMap<String, Object> map = new HashMap<>();
		Boolean aBoolean = teacherService.updateTeacher(teacher);
		if (aBoolean) {
			return ResultVO.builder().message("成功").build();
		} else {
			return ResultVO.builder().message("失败").build();
		}
	}

	@RequestMapping("/addTeacher")
	public ResultVO addTeacher(@RequestBody Teacher teacher) {
		HashMap<String, Object> map = new HashMap<>();
		Boolean aBoolean = teacherService.addTeacher(teacher);
		if (aBoolean) {
			return ResultVO.builder().message("成功").build();
		} else {
			return ResultVO.builder().message("失败").build();
		}
	}

}
