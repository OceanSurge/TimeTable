package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.Laboratory;
import com.example.demo.service.LaboratoryService;
import com.example.demo.utils.ResultVO;
import com.example.demo.utils.UploadImgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
public class LaboratoryController {


	@Autowired
	LaboratoryService laboratoryService;

	@RequestMapping("/allLaboratory")
	public ResultVO allLaboratory() {
		HashMap<String, Object> stringObjectHashMap = laboratoryService.getAllLaboratory();
		return ResultVO.success(stringObjectHashMap);
	}

	@RequestMapping("/LaboratoryManage")
	public ResultVO LaboratoryManage() {
		HashMap<String, Object> allLaboratory = laboratoryService.getAllLaboratory();
		return ResultVO.success(allLaboratory);
	}

	@RequestMapping("/addLaboratoryImg")
	public ResultVO addLaboratory(@RequestParam("img") MultipartFile uploadFile) {
		String s = UploadImgUtils.approvalFile(uploadFile);
		HashMap<String, Object> map = new HashMap<>();
		map.put("url", s);
		return ResultVO.success(map);
	}

	@RequestMapping("/delLaboratoryImg")
	public ResultVO delLaboratoryImg(@RequestParam("url") String url) {
		UploadImgUtils.delLaboratoryImg(url);
		return null;
	}

	@RequestMapping("/addLaboratory")
	public ResultVO addLaboratory(@RequestBody Laboratory laboratory) {
		Boolean aBoolean = laboratoryService.addLaboratory(laboratory);
		if (aBoolean) {
			return ResultVO.builder().code(200).message("success").build();
		} else {
			return ResultVO.builder().code(500).message("error").build();
		}
	}

	@PostMapping("/updateLaboratory")
	public ResultVO updateLaboratory(@RequestBody Laboratory laboratory) {
		Boolean aBoolean = laboratoryService.updateLaboratory(laboratory);
		if (aBoolean) {
			return ResultVO.builder().code(200).message("success").build();
		} else {
			return ResultVO.builder().code(500).message("error").build();
		}
	}
}
