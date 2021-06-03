package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Teacher;
import com.example.demo.utils.ResultVO;
import com.example.demo.utils.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	AdminMapper adminMapper;

	@Autowired
	TeacherMapper teacherMapper;


	@Override
	public Admin ALogin(User user) {
		QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
		adminQueryWrapper
				.eq("username", user.getUsername())
				.eq("password", user.getPassword())
				.eq("role", "管理员");
		Admin admin = adminMapper.selectOne(adminQueryWrapper);
		return admin;
	}

	@Override
	public Teacher TLogin(User user) {
		QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
		teacherQueryWrapper
				.eq("username", user.getUsername())
				.eq("password", user.getPassword())
				.eq("role", "教师")
				.eq("deleted", 0);
		Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
		return teacher;
	}

	@Override
	public ResultVO Login(User user) {
		HashMap<String, Object> map = new HashMap<>();
		QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
		adminQueryWrapper
				.eq("username", user.getUsername())
				.eq("password", user.getPassword())
				.eq("role", "管理员");
		QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
		teacherQueryWrapper
				.eq("username", user.getUsername())
				.eq("password", user.getPassword())
				.eq("role", "教师")
				.eq("deleted", 0);
		Admin admin = adminMapper.selectOne(adminQueryWrapper);
		Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
		if (admin != null && teacher != null) {
			return ResultVO.error(500, "重复");
		} else if (admin == null && teacher == null) {
			return ResultVO.error(500, "无此用户");
		} else if (admin != null && teacher == null) {
			map.put("user", admin);
			return ResultVO.success(map);
		} else if (admin == null && teacher != null) {
			map.put("user", teacher);
			return ResultVO.success(map);
		}
		return null;
	}
}
