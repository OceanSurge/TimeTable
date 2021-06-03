package com.example.demo.service;

import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Teacher;
import com.example.demo.utils.ResultVO;
import com.example.demo.utils.User;

import java.util.HashMap;

public interface LoginService {
	public ResultVO Login(User user);
	public Admin ALogin(User user);
	public Teacher TLogin(User user);
}
