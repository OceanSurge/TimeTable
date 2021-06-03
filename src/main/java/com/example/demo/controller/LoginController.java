package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Teacher;
import com.example.demo.utils.ResultVO;
import com.example.demo.utils.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {
	@Autowired
	LoginService loginService;

	@RequestMapping("/login")
	public ResultVO Login(@RequestBody User user) {
		return loginService.Login(user);
	}

}
