package com.example.demo.service;

import com.example.demo.pojo.Menu;

import java.util.List;

public interface MenuService {
	public List<Menu> getMenuByRole(String role);
}
