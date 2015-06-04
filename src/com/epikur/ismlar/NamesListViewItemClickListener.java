package com.epikur.ismlar;

import com.epikur.ismlar.models.NameModel;
import com.epikur.ismlar.services.INamesService;

import android.app.Dialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
	
public class NamesListViewItemClickListener implements AdapterView.OnItemClickListener {

	private Context context;
	private INamesService namesService;

	public NamesListViewItemClickListener(Context context, INamesService namesService) {
		this.context = context;
		this.namesService = namesService;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		final Dialog nameInfoDialog = new Dialog(context);
		
		nameInfoDialog.setContentView(R.layout.name_dialog);
		
		TextView nameText = (TextView) view.findViewById(R.id.name);
		ImageView genderImage = (ImageView) view.findViewById(R.id.gender_image);
		
		nameInfoDialog.setTitle(R.string.name_dialog_title);
		
		final NameModel nameInfo = namesService.GetNameModelByName(nameText.getText().toString());
		
		if (nameInfo != null) {
			((TextView)nameInfoDialog.findViewById(R.id.name)).setText(nameInfo.getName());
			((ImageView)nameInfoDialog.findViewById(R.id.gender_image)).setImageDrawable(genderImage.getDrawable());
			((TextView)nameInfoDialog.findViewById(R.id.origin)).setText(nameInfo.getOrigin());
			((TextView)nameInfoDialog.findViewById(R.id.meaning)).setText(nameInfo.getMeaning());
			
			((Button)nameInfoDialog.findViewById(R.id.add_to_favorite)).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					Toast.makeText(context, String.format("%s added to favorites", nameInfo.getName()), Toast.LENGTH_SHORT).show();
					view.setEnabled(false);
				}
			});
			
			((Button)nameInfoDialog.findViewById(R.id.close_dialog)).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					nameInfoDialog.dismiss();
				}
			});
			
			nameInfoDialog.show();
		}
	}
}
