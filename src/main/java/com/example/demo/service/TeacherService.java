package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Teacher;
import com.example.demo.utils.QueryInfo;

import java.util.List;

public interface TeacherService {
	public Page<Teacher> selectAllTeacher(QueryInfo queryInfo);
	public Boolean updateState(Teacher teacher);
	public Boolean deleteTeacher(int id);
	public Teacher getTeacherById(int id);
	public Boolean updateTeacher(Teacher teacher);
	public Boolean addTeacher(Teacher teacher);
}
