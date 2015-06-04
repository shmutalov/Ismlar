package com.epikur.ismlar;

import java.util.ArrayList;

import com.epikur.ismlar.models.NameModel;
import com.epikur.ismlar.services.AndroidSqliteNamesService;
import com.epikur.ismlar.services.INamesService;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView namesListView;
	private ListAdapter namesListViewAdapter;
	private EditText searchNames;
	private ArrayList<NameModel> namesList;
	private INamesService nameService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		namesListView = (ListView) findViewById(R.id.names_list);
		searchNames = (EditText) findViewById( R.id.search_names);
		
		nameService = new AndroidSqliteNamesService(this);
		
		if (nameService != null && nameService.Connect("names", null, null)) {
			namesListViewAdapter = new NamesListViewAdapter(this, nameService, 50);
			
			namesListView.setAdapter(namesListViewAdapter);
			namesListView.setOnScrollListener(new NamesListViewScrollListener((NamesListViewAdapter)namesListViewAdapter));
			namesListView.setOnItemClickListener(new NamesListViewItemClickListener(this, nameService));
			
			searchNames.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					return;
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					return;
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					String text = searchNames.getText().toString();
					
					((NamesListViewAdapter)namesListViewAdapter).applyFilter(null, null, text);
				}
			});
		} else {
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
