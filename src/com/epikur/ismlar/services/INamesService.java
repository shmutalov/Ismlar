package com.epikur.ismlar.services;

import java.util.List;

import com.epikur.ismlar.models.NameModel;

/**
 * ��������� ������� ��� ������ � �� ���
 * @author sh.mutalov
 *
 */
public interface INamesService {
	
	/**
	 * ����������� � ���� ������ ���
	 * @param dataSourceName ��� ��������� ������
	 * @param userName ������������
	 * @param password ������
	 * @return ��� �������� ����������� ��������� {@code true}
	 */
    boolean Connect(String dataSourceName, String userName, String password);
    
    /**
     * ����������� �� ��
     */
    void Disconnect();

    /**
     * ������� ��
     * @param name ��� ��
     * @return ��� �������� �������� �� ������ {@code true}
     */
    boolean CreateDatabase(String name);
    
    /**
     * ������� ������ ������ �� ���, ���� ������� �����������
     * @return ��� �������� �������� ������ ������ {@code true}
     */
    boolean CreateDataModelIfNotExists();

    /**
     * �������� ���� ������ {@link NameModel}
     * @param limit_offset ���������� ��������� ��� �������� ��� ��������� ����� ������
     * @param limit_count ���������� ��������� ������ ��� ���������, ���� 0 �� ����� ������� ������ ������
     * @return ��������� ������ {@link #List List&lt;NameModel&gt;} ��� �������� ����������, 
     * ��� {@code null} � ������ ������
     */
    List<NameModel> GetAllNames(int limit_offset, int limit_count);
    
    /**
     * �������� {@link NameModel} �� ����� ({@link NameModel.name})
     * @param name ���
     * @return ��������� ������ {@link NameModel} ��� �������� ����������, 
     * ��� {@code null} � ������ ������
     */
    NameModel GetNameModelByName(String name);
    
    /**
     * �������� ��������������� ������ NameModel,
     * ��� ���� ������ ���������� �������� SQL {@code LIKE}.
     * @param letter ��������� ����� �����
     * @param name ���
     * @param gender ���
     * @param meaning �������� �����
     * @param origin �������������
     * @param limit_offset ���������� ��������� ��� �������� ��� ��������� ����� ������
     * @param limit_count ���������� ��������� ������ ��� ���������, ���� 0 �� ����� ������� ������ ������
     * @return ��������� ������ {@link #List List&lt;NameModel&gt;} ��� �������� ����������, 
     * ��� {@code null} � ������ ������
     */
    List<NameModel> GetFilteredNamesList(String letter
    		, String name
    		, String gender
    		, String meaning
    		, String origin
    		, int limit_offset
    		, int limit_count);
    
    /**
     * ������� ������ NameModel � ��
     * @param ism {@link NameModel}
     * @return ��� �������� ����������� ��������� {@code true}
     */
    boolean CreateIsm(NameModel ism);
    
    /**
     * ������� ������ {@link NameModel} �� �� ���
     * @param ism {@link NameModel}
     * @return ��������� ��������� ���������� ������ {@link #DeleteIsm(String) DeleteIsm(ism.getName())}
     * @see #DeleteIsm(String) DeleteIsm(String name)
     */
    boolean DeleteIsm(NameModel ism);
    
    /**
     * ������� ������ {@link NameModel} �� ����� �� �� ���
     * @param name ���
     * @return ��� �������� �������� ��������� {@code true}
     */
    boolean DeleteIsm(String name);
    
    /**
     * ������� ��� ������ {@link NameModel} � �� ���
     * @return ��� �������� �������� ��������� {@code true}
     */
    boolean DeleteAllIsm();
}
