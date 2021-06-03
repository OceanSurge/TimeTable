package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	private int id;
	//	private int weekStartTime;
//	private int weekEndTime;
//	private int lessonStartTime;
//	private int lessonEndTime;
	private int weekLength;
	private int timeLength;
	private int weekNumber;
	private int teacherId;
	private int state;
	private String courseName;
	private int summaryTime;

//	@TableField(exist = false)
//	private List<Integer> lessonStartTime;
//	@TableField(exist = false)
//	private List<Integer> weekStartTime;
}
