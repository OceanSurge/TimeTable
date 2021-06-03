package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.TeacherMapper;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.TeacherService;
import com.example.demo.utils.QueryInfo;
import com.example.demo.utils.Remainder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherMapper teacherMapper;

	@Override
	public Page<Teacher> selectAllTeacher(QueryInfo queryInfo) {
		QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
		teacherQueryWrapper.like("username", queryInfo.getQuery());
		Page<Teacher> Page = new Page<>(queryInfo.getPageNum(), queryInfo.getPageSize());
		Page<Teacher> teacherPage = teacherMapper.selectPage(Page, teacherQueryWrapper);
		return teacherPage;
	}

	@Override
	public Boolean deleteTeacher(int id) {
		if (teacherMapper.deleteById(id) == 1){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean updateState(Teacher teacher) {
		int update = teacherMapper.updateById(teacher);
		if (update == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Teacher getTeacherById(int id) {
		Teacher teacher = teacherMapper.selectById(id);
		return teacher;
	}

	@Override
	public Boolean updateTeacher(Teacher teacher) {
		int i = teacherMapper.updateById(teacher);
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean addTeacher(Teacher teacher) {
		int insert = teacherMapper.insert(teacher);
		if (insert == 1) {
			return true;
		} else {
			return false;
		}
	}
}
