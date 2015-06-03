package com.epikur.ismlar.models;

import com.epikur.ismlar.NAME_GENDER;

/**
 * ������ ���������� ���������� �� �����
 */
public class NameModel {
	/**
	 * ��������� ����� �����
	 */
	private String letter;
	/**
	 * ���
	 */
	private NAME_GENDER gender;
	/**
	 * ���
	 */
	private String name;
	/**
	 * �������������
	 */
	private String origin;
	/**
	 * ��������
	 */
	private String meaning;
	
	public NameModel(String letter, NAME_GENDER gender, String name,
			String origin, String meaning) {
		super();
		this.letter = letter;
		this.gender = gender;
		this.name = name;
		this.origin = origin;
		this.meaning = meaning;
	}
	
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public NAME_GENDER getGender() {
		return gender;
	}
	public void setGender(NAME_GENDER gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
}
