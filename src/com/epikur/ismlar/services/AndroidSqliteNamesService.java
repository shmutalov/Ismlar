package com.epikur.ismlar.services;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.epikur.ismlar.EmbeddedSqliteDatabase;
import com.epikur.ismlar.NAME_GENDER;
import com.epikur.ismlar.models.NameModel;

public class AndroidSqliteNamesService implements INamesService {

	private Context context;
	private EmbeddedSqliteDatabase database;
	private boolean connected;
	
	public AndroidSqliteNamesService(Context context)
	{
		this.context = context;
	}
	
	/**
	 * ��������� ����������� � ��
	 * @return ��������� {@code true} ���� ����������� � �� ������������, 
	 * � ������ ������ {@code false}
	 */
	public boolean IsConnected() { 
		return connected;
    }
	
	@Override
	public boolean Connect(String dataSourceName, String userName,
			String password) {
		Disconnect();
		
		String sqliteDatabaseName = dataSourceName.endsWith(".sqlite") ? dataSourceName : dataSourceName + ".sqlite";
		
		try {
			database = new EmbeddedSqliteDatabase(context, sqliteDatabaseName);
			connected = true;
		} catch (Exception ex) {
			connected = false;
		}
		
		return connected;
	}

	@Override
	public void Disconnect() {
		if (connected && database != null)
		{
			database.close();
			database = null;
			connected = false;
		}
	}

	@Override
	public boolean CreateDatabase(String name) {
		throw new UnsupportedOperationException("This method is not implemented on current platform.");
	}

	@Override
	public boolean CreateDataModelIfNotExists() {
		throw new UnsupportedOperationException("This method is not implemented on current platform.");
	}

	@Override
	public List<NameModel> GetAllNames(int limit_offset, int limit_count) {
		if (IsConnected())
        {
            String selectAllNamesQuery =
                "SELECT" 			+ "\n"
                + "    letter" 		+ "\n"
                + "    , gender" 	+ "\n"
                + "    , name" 		+ "\n"
                + "    , meaning" 	+ "\n"
                + "    , origin" 	+ "\n"
                + "FROM ism"		+ "\n"
                + "ORDER BY name" 	+ "\n";

            if (limit_count != 0) {
            	if (limit_offset != 0) {
            		selectAllNamesQuery += "LIMIT " + limit_count + " OFFSET " + limit_offset;
            	}
            	else {
            		selectAllNamesQuery += "LIMIT " + limit_count;
            	}
            }
            
            Log.i("SQL Query", selectAllNamesQuery);
            
            SQLiteDatabase db = database.getReadableDatabase();
            Cursor allNamesCursor = null;
            
            try {
	            allNamesCursor = db.rawQuery(selectAllNamesQuery, null);
	            
	            if (allNamesCursor == null)
	            	return null;
	            
	            List<NameModel> namesList = new ArrayList<NameModel>();
	            
	            while (allNamesCursor.moveToNext())
	            {
	            	namesList.add(
            			new NameModel(
            				allNamesCursor.getString(allNamesCursor.getColumnIndex("letter"))
            				, allNamesCursor.getString(allNamesCursor.getColumnIndex("gender")).equals("F") ? NAME_GENDER.FEMALE : NAME_GENDER.MALE
            				, allNamesCursor.getString(allNamesCursor.getColumnIndex("name"))
            				, allNamesCursor.getString(allNamesCursor.getColumnIndex("origin"))
            				, allNamesCursor.getString(allNamesCursor.getColumnIndex("meaning"))
            			)
	            	);
	            }
	            
	            return namesList;
            } catch (Exception ex) {
            	return null;
            } finally {
            	if (allNamesCursor != null)
            		allNamesCursor.close();
            }
        }

        return null;
	}

	@Override
	public NameModel GetNameModelByName(String name) {
		if (IsConnected())
        {
            String selectNameQuery =
                "SELECT" 			+ "\n"
                + "    letter" 		+ "\n"
                + "    , gender" 	+ "\n"
                + "    , name" 		+ "\n"
                + "    , meaning" 	+ "\n"
                + "    , origin" 	+ "\n"
                + "FROM ism"		+ "\n"
                + "WHERE 1=1"			+ "\n"
                + "		AND name = ?"	+ "\n";

            SQLiteDatabase db = database.getReadableDatabase();
            Cursor nameCursor = null;
            
            try {
            	nameCursor = db.rawQuery(selectNameQuery
            			, new String [] {name}
            	);
	            
	            if (nameCursor == null)
	            	return null;
	            
	            NameModel nameModel = null;
	            
	            while (nameCursor.moveToNext())
	            {
	            	nameModel =
            			new NameModel(
            					nameCursor.getString(nameCursor.getColumnIndex("letter"))
            				, nameCursor.getString(nameCursor.getColumnIndex("gender")).equals("F") ? NAME_GENDER.FEMALE : NAME_GENDER.MALE
            				, nameCursor.getString(nameCursor.getColumnIndex("name"))
            				, nameCursor.getString(nameCursor.getColumnIndex("origin"))
            				, nameCursor.getString(nameCursor.getColumnIndex("meaning"))
            			);
	            }
	            
	            return nameModel;
            } catch (Exception ex) {
            	return null;
            } finally {
            	if (nameCursor != null)
            		nameCursor.close();
            }
        }

        return null;
	}

	@Override
	public List<NameModel> GetFilteredNamesList(String letter, String name,
			String gender, String meaning, String origin,
			int limit_offset, int limit_count) {
		if (IsConnected())
        {
            String selectFilteredNamesQuery =
                "SELECT" 			+ "\n"
                + "    letter" 		+ "\n"
                + "    , gender" 	+ "\n"
                + "    , name" 		+ "\n"
                + "    , meaning" 	+ "\n"
                + "    , origin" 	+ "\n"
                + "FROM ism" 		+ "\n"
                + "WHERE 1=1" 		+ "\n"
	            + "    AND ( UPPER(letter) LIKE ? )" 		+ "\n"
	            + "    AND ( UPPER(gender) LIKE ? )" 		+ "\n"
	            + "    AND ( UPPER(name) LIKE ? )" 			+ "\n"
	            + "    AND ( UPPER(meaning) LIKE ? )" 		+ "\n"
	            + "    AND ( UPPER(origin) LIKE ? )"		+ "\n"
	            + "ORDER BY name"	+ "\n";

            if (limit_count != 0) {
            	if (limit_offset != 0) {
            		selectFilteredNamesQuery += "LIMIT " + limit_count + " OFFSET " + limit_offset;
            	}
            	else {
            		selectFilteredNamesQuery += "LIMIT " + limit_count;
            	}
            }
            
            Log.i("SQL Query", selectFilteredNamesQuery);
            
            SQLiteDatabase db = database.getReadableDatabase();
            Cursor filteredNamesCursor = null;
            
            try {
            	filteredNamesCursor = db.rawQuery(selectFilteredNamesQuery
	            		, new String[] {
		            		letter
		            		, gender
		            		, name
		            		, meaning
		            		, origin
	            		}
	            );
	            
	            if (filteredNamesCursor == null)
	            	return null;
	            
	            List<NameModel> namesList = new ArrayList<NameModel>();
	            
	            while (filteredNamesCursor.moveToNext())
	            {
	            	namesList.add(
            			new NameModel(
	        				filteredNamesCursor.getString(filteredNamesCursor.getColumnIndex("letter"))
	        				, filteredNamesCursor.getString(filteredNamesCursor.getColumnIndex("gender")).equals("F") ? NAME_GENDER.FEMALE : NAME_GENDER.MALE
	        				, filteredNamesCursor.getString(filteredNamesCursor.getColumnIndex("name"))
	        				, filteredNamesCursor.getString(filteredNamesCursor.getColumnIndex("origin"))
	        				, filteredNamesCursor.getString(filteredNamesCursor.getColumnIndex("meaning"))
	        			)
	            	);
	            }
	            
	            return namesList;
            } catch (Exception ex) {
            	return null;
            } finally {
            	if (filteredNamesCursor != null)
            		filteredNamesCursor.close();
            }
        }

        return null;
	}

	@Override
	public boolean CreateIsm(NameModel ism) {
		throw new UnsupportedOperationException("This method is not implemented on current platform.");
	}

	@Override
	public boolean DeleteIsm(NameModel ism) {
		throw new UnsupportedOperationException("This method is not implemented on current platform.");
	}

	@Override
	public boolean DeleteIsm(String name) {
		throw new UnsupportedOperationException("This method is not implemented on current platform.");
	}

	@Override
	public boolean DeleteAllIsm() {
		throw new UnsupportedOperationException("This method is not implemented on current platform.");
	}

}
