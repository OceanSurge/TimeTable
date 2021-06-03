package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Laboratory {
	private int id;
	private String laboratoryName;
	private String img;
	private int peopleAmount;
	private int machineAmount;
	private String number;
}
