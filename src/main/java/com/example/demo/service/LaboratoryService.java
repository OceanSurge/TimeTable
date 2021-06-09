package com.example.demo.service;

import com.example.demo.pojo.Laboratory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;


public interface LaboratoryService {
	public HashMap<String, Object> getAllLaboratory();

	public Boolean updateLaboratory(Laboratory laboratory);

	public Boolean addLaboratory(Laboratory laboratory);

	public Laboratory getIdByName(String name);

	public Boolean removeLaboratory(int id);
}
