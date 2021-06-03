package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
	private int id;
	private String username;
	private String password;
	private int deleted;
	private String role;
}
