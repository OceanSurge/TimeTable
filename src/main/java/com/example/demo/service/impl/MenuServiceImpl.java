package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.pojo.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuMapper menuMapper;

	@Override
	public List<Menu> getMenuByRole(String role) {
		QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
		menuQueryWrapper
				.eq("role",role);
		List<Menu> menus = menuMapper.selectList(menuQueryWrapper);
		return menus;
	}
}
