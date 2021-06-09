package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Timetable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TimeTableMapper extends BaseMapper<Timetable> {

	@Update("UPDATE timetable SET state = #{state} ,course_name = #{courseName} WHERE week=#{week} " +
			"AND week_day = #{weekDay} AND lesson = #{lesson} AND laboratory_id=#{laboratoryId}")
	public void updateTimeTable(Timetable timetable);



}
