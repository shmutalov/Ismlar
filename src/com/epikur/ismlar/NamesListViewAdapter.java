package com.epikur.ismlar;

import java.util.ArrayList;
import java.util.List;

import com.epikur.ismlar.models.NameModel;
import com.epikur.ismlar.services.INamesService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NamesListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private INamesService namesService;
	private int preLoadCount;
	private List<NameModel> namesList;
	
	private String letterFilter;
	private String nameFilter;
	private NAME_GENDER genderFilter;
	
	public NamesListViewAdapter(Context context, INamesService namesService, int preLoadCount) {
		this.namesService = namesService;
		this.preLoadCount = preLoadCount;
		
		this.letterFilter = null;
		this.nameFilter = null;
		this.genderFilter = null;
		
		namesList = new ArrayList<NameModel>();
		
		loadData(0);
		
		//this.inflater = LayoutInflater.from(context);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void applyFilter(String letterFilter, NAME_GENDER genderFilter, String nameFilter) {
		this.letterFilter = letterFilter;
		this.genderFilter = genderFilter;
		this.nameFilter = nameFilter;
	}
	
	public int loadData(int position) {
		String genderFilterString = (genderFilter != null) ? (genderFilter == NAME_GENDER.MALE ? "M" : "F") : "%";
		String nameFilterString = (nameFilter != null) ? "%" + nameFilter + "%" : "%";
		String letterFilterString = (letterFilter != null) ? letterFilter : "%";
		
		//List<NameModel> names = namesService.GetFilteredNamesList(letterFilterString, nameFilterString, genderFilterString, "%", "%", position, preLoadCount);
		List<NameModel> names = namesService.GetAllNames(position, preLoadCount);
		
		int ret = 0;
		
		if (names == null || names.size() == 0) {
			namesList.clear();
			ret = 0;
		} else {
			ret = names.size();
			namesList.clear();
			namesList.addAll(names);
		}
			
		notifyDataSetChanged();
		
		return ret;
	}
	
	@Override
	public int getCount() {
		return namesList.size();
	}

	@Override
	public Object getItem(int position) {
		return namesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		
		view = inflater.inflate(R.layout.name_list_item, parent, false);
		
		TextView nameText = (TextView) view.findViewById(R.id.name);
		ImageView genderImage = (ImageView) view.findViewById(R.id.gender_image);
		
		nameText.setText(namesList.get(position).getName());
		
		if (namesList.get(position).getGender() == NAME_GENDER.MALE)
			genderImage.setImageResource(R.drawable.gender_male);
		else
			genderImage.setImageResource(R.drawable.gender_female);
		
		return view;
	}
}
