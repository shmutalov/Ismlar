package com.epikur.ismlar.services;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;

public class AndroidFavoriteNamesService implements IFavoriteNamesService {
	public static final String PREFERENCES_NAME = "EPIKUR_ISMLAR";
	public static final String FAVOURITES = "FAVOURITES";
	
	private SharedPreferences settings;
	private Context context;
	private Gson gson;
	private ArrayList<String> namesList;
	
	public AndroidFavoriteNamesService(Context context) {
		this.context = context;
		this.namesList = new ArrayList<String>();
		
		settings = context.getSharedPreferences(PREFERENCES_NAME
				, Context.MODE_PRIVATE);
		
		gson = new Gson();
	}
	
	@Override
	public List<String> getAllNames() {
		return namesList;
	}

	@Override
	public boolean add(String name) {
		if (!namesList.contains(name))
			return namesList.add(name);
		else
			return false;
	}

	@Override
	public boolean remove(String name) {
		return namesList.remove(name);
	}

	@Override
	public boolean isFavorite(String name) {
		return namesList.contains(name);
	}

	@Override
	public boolean save() {
		String favouritesJSON = gson.toJson(namesList);
		
		settings.edit().putString(FAVOURITES, favouritesJSON);
		
		return settings.edit().commit();
	}

	@Override
	public boolean load() {
		String favouritesJSON = settings.getString(FAVOURITES, gson.toJson(new ArrayList<String>()));
		
		ArrayList<String> favouritesList = gson.fromJson(favouritesJSON, new TypeToken<ArrayList<String>>(){}.getType());
		
		if (favouritesJSON != null) {
			namesList.addAll(favouritesList);
			return true;
		}
		
		return false;
	}

}
