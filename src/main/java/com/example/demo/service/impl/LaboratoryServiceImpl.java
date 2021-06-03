package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.LaboratoryMapper;
import com.example.demo.mapper.TimeTableMapper;
import com.example.demo.pojo.Laboratory;
import com.example.demo.pojo.Timetable;
import com.example.demo.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

	@Autowired
	LaboratoryMapper laboratoryMapper;

	@Autowired
	TimeTableMapper timeTableMapper;

	@Override
	public HashMap<String, Object> getAllLaboratory() {
		HashMap<String, Object> map = new HashMap<>();
		Page<Laboratory> page = new Page<>();
		Page<Laboratory> laboratoryPage = laboratoryMapper.selectPage(page, null);
		map.put("allLaboratory", laboratoryPage.getRecords());
		map.put("size", laboratoryPage.getTotal());
		return map;
	}


	@Override
	public Boolean updateLaboratory(Laboratory laboratory) {
		int i = laboratoryMapper.updateById(laboratory);
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean addLaboratory(Laboratory laboratory) {

		int insert = laboratoryMapper.insert(laboratory);
		QueryWrapper<Laboratory> laboratoryQueryWrapper = new QueryWrapper<>();
		laboratoryQueryWrapper.eq("laboratory_name",laboratory.getLaboratoryName());
		Laboratory laboratory1 = laboratoryMapper.selectOne(laboratoryQueryWrapper);
		int size = laboratoryMapper.selectList(null).size();
		int z = 1 + (size - 1) * 630;
		for (int i = 1; i <= 18; i++) {
			for (int j = 1; j <= 7; j++) {
				for (int k = 1; k <= 5; k++) {
					Timetable timetable = new Timetable(z, i, j, k, 1, laboratory1.getId(), null);
					timeTableMapper.insert(timetable);
					z++;
				}
			}
		}

		if (insert == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Laboratory getIdByName(String name) {
		QueryWrapper<Laboratory> laboratoryQueryWrapper = new QueryWrapper<>();
		laboratoryQueryWrapper.eq("laboratory_name", name);
		Laboratory laboratory = laboratoryMapper.selectOne(laboratoryQueryWrapper);
		return laboratory;
	}
}
