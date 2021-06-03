package com.example.demo.utils;

import com.example.demo.pojo.Timetable;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UploadImgUtils {


	public static String approvalFile(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
//		String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
		//保存的文件名
//		LocalDateTime localDateTime = LocalDateTime.now();
		String dFileName = UUID.randomUUID() + "_" + originalFilename;
		String path = System.getProperty("user.dir") + "\\src\\main\\resources" + "/static/imgs/";
		//生成保存文件
		File uploadFile = new File(path + dFileName);
		System.out.println(uploadFile);
		System.out.println(uploadFile);
		//将上传文件保存到路径
		try {
			file.transferTo(uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "http://localhost:8080/static/imgs/" + dFileName;
	}

	public static void delLaboratoryImg(String path) {
		String[] split = path.split("/");
		File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources" + "/static/imgs/" + split[5]);
		file.delete();
	}

	public static List<Timetable> doConcat(HashMap<Integer, Stream<Timetable>> map) {
		if (map.size() == 1) {
			return map.get(0).collect(Collectors.toList());
		}
		if (map.size() == 2) {
			Stream<Timetable> concat = Stream.concat(map.get(0), map.get(1));
//			System.out.println(concat.collect(Collectors.toList()).size());
			return concat.collect(Collectors.toList());
		}
		if (map.size() > 2) {
			int size = map.size();
			map.put(size, Stream.concat(map.get(0), map.get(1)));
			for (int i = 2; i < size; i++) {
				map.put(size + i - 1, Stream.concat(map.get(size + i - 2), map.get(i)));
			}
//			System.out.println(map.get((size - 1) * 2).collect(Collectors.toList()).size());
			return map.get((size - 1) * 2).collect(Collectors.toList());
		}
		return null;
	}

}
