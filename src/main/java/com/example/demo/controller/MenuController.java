package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Menu;
import com.example.demo.service.MenuService;
import com.example.demo.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MenuController {
	@Autowired
	MenuService menuService;


	@RequestMapping("/main")
	public ResultVO pushToMain(@RequestParam("role") String role) {
		HashMap<String, Object> map = new HashMap<>();
		List<Menu> menuByRole = menuService.getMenuByRole(role);
		map.put("menus",menuByRole);
		return ResultVO.success(map);
	}


}
