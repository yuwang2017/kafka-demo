package com.graycat.kafka.kafkaproducer.model;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private String name;
	private String grade;
	private int age;
	
	public Student() {
		
	}
	
	public Student(int id, String name, String grade, int age) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
