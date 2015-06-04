package com.epikur.ismlar.services;

import java.util.List;

/**
 * Сервис для работы с избранными именами
 * @author sh.mutalov
 *
 */
public interface IFavoriteNamesService {
	/**
	 * Получить весь список имён в избранном
	 * @return Возвратит список имён {@link #List List&lt;String&gt;}
	 */
	public List<String> getAllNames();
	
	/**
	 * Добавить имя в избранное
	 * @param name Имя
	 * @return Возратит {@code true} если имя добавилось в список,
	 * в другом случае {@code false}
	 */
	public boolean add(String name);
	
	/**
	 * Удалить имя с избранного
	 * @param name
	 * @return Возратит {@code true} если имя удалилось из списка, 
	 * в другом случае {@code false}
	 */
	public boolean remove(String name);
	
	/**
	 * Проверить, является ли имя избранным
	 * @param name
	 * @return Возратит {@code true} если имя присутствует в списке, 
	 * в другом случае {@code false}
	 */
	public boolean isFavorite(String name);
	
	/**
	 * Сохранить список избранного
	 * @return Возратит {@code true} при успешном сохранении списка, 
	 * в другом случае {@code false}
	 */
	public boolean save();
	
	/**
	 * Загрузить список избранного
	 * @return Возратит {@code true} при успешной загрузки списка,
	 * в другом случае {@code false}
	 */
	public boolean load();
}
