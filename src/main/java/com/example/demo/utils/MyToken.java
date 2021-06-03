package com.example.demo.utils;

import lombok.Data;

@Data
public class MyToken {
	public enum Role {
		User, Admin
	}
	private Integer id;
	private Role role;
}
