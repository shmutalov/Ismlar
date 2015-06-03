package com.epikur.ismlar.services;

import java.util.List;

import android.database.sqlite.*;

import com.epikur.ismlar.models.NameModel;

public class AndroidSqliteNamesService implements INamesService {

	private SQLiteDatabase database;
	
	@Override
	public boolean Connect(String dataSourceName, String userName,
			String password) {
		
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean CreateDatabase(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CreateDataModelIfNotExists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NameModel> GetAllNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NameModel GetNameModelByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NameModel> GetFilteredIsmList(String letter, String name,
			String gender, String meaning, String origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CreateIsm(NameModel ism) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteIsm(NameModel ism) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteIsm(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteAllIsm() {
		// TODO Auto-generated method stub
		return false;
	}

}
