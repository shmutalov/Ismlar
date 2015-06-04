package com.epikur.ismlar.services;

import java.util.List;

/**
 * ������ ��� ������ � ���������� �������
 * @author sh.mutalov
 *
 */
public interface IFavoriteNamesService {
	/**
	 * �������� ���� ������ ��� � ���������
	 * @return ��������� ������ ��� {@link #List List&lt;String&gt;}
	 */
	public List<String> getAllNames();
	
	/**
	 * �������� ��� � ���������
	 * @param name ���
	 * @return �������� {@code true} ���� ��� ���������� � ������,
	 * � ������ ������ {@code false}
	 */
	public boolean add(String name);
	
	/**
	 * ������� ��� � ����������
	 * @param name
	 * @return �������� {@code true} ���� ��� ��������� �� ������, 
	 * � ������ ������ {@code false}
	 */
	public boolean remove(String name);
	
	/**
	 * ���������, �������� �� ��� ���������
	 * @param name
	 * @return �������� {@code true} ���� ��� ������������ � ������, 
	 * � ������ ������ {@code false}
	 */
	public boolean isFavorite(String name);
	
	/**
	 * ��������� ������ ����������
	 * @return �������� {@code true} ��� �������� ���������� ������, 
	 * � ������ ������ {@code false}
	 */
	public boolean save();
	
	/**
	 * ��������� ������ ����������
	 * @return �������� {@code true} ��� �������� �������� ������,
	 * � ������ ������ {@code false}
	 */
	public boolean load();
}
