package com.epikur.ismlar.services;

import java.util.List;

import com.epikur.ismlar.models.NameModel;

/**
 * Интерфейс сервиса для работы с БД имён
 * @author sh.mutalov
 *
 */
public interface INamesService {
	
	/**
	 * Подключится к базе данных имён
	 * @param dataSourceName Имя источника данных
	 * @param userName Пользователь
	 * @param password Пароль
	 * @return При успешном подключении возвратит {@code true}
	 */
    boolean Connect(String dataSourceName, String userName, String password);
    
    /**
     * Отключиться от БД
     */
    void Disconnect();

    /**
     * Создать БД
     * @param name Имя БД
     * @return При успешном создании БД вернет {@code true}
     */
    boolean CreateDatabase(String name);
    
    /**
     * Создать модель данных БД имён, если таковая отсутствует
     * @return При успешном создании модели вернет {@code true}
     */
    boolean CreateDataModelIfNotExists();

    /**
     * Получить весь список {@link NameModel}
     * @return Возвратит список {@link #List List&lt;NameModel&gt;} при успешном выполнении, 
     * или {@code null} в другом случае
     */
    List<NameModel> GetAllNames();
    
    /**
     * Получить {@link NameModel} по имени ({@link NameModel.name})
     * @param name Имя
     * @return Возвратит объект {@link NameModel} при успешном выполнении, 
     * или {@code null} в другом случае
     */
    NameModel GetNameModelByName(String name);
    
    /**
     * Получить отфильтрованный список NameModel,
     * при этом фильтр использует оператор SQL {@code LIKE}.
     * @param letter Начальная буква имени
     * @param name Имя
     * @param gender Пол
     * @param meaning Значение имени
     * @param origin Происхождение
     * @return Возвратит список {@link #List List&lt;NameModel&gt;} при успешном выполнении, 
     * или {@code null} в другом случае
     */
    List<NameModel> GetFilteredIsmList(String letter, String name, String gender, String meaning, String origin);
    
    /**
     * Создать запись NameModel в БД
     * @param ism {@link NameModel}
     * @return При успешном подключении возвратит {@code true}
     */
    boolean CreateIsm(NameModel ism);
    
    /**
     * Удалить запись {@link NameModel} из БД имён
     * @param ism {@link NameModel}
     * @return Возвратит результат выполнения метода {@link #DeleteIsm(String) DeleteIsm(ism.getName())}
     * @see #DeleteIsm(String) DeleteIsm(String name)
     */
    boolean DeleteIsm(NameModel ism);
    
    /**
     * Удалить запись {@link NameModel} по имени из БД имён
     * @param name Имя
     * @return При успешном удалении возвратит {@code true}
     */
    boolean DeleteIsm(String name);
    
    /**
     * Удалить все записи {@link NameModel} в БД имён
     * @return При успешном удалении возвратит {@code true}
     */
    boolean DeleteAllIsm();
}
