package com.epikur.ismlar;

import com.epikur.ismlar.services.INamesService;

import android.widget.AbsListView;

public class NamesListViewScrollListener implements AbsListView.OnScrollListener {
	private int preLoadCount;
	private int totalLoadedCount;
	private int halfOfTotal;
	private boolean loading;
	
	private int diffCounter;
	private int oldDiffCounter;
	
	private int currentPosition;
	
	private int currentFirstVisibleItem;
	private int currentVisibleItemCount;
	private int oldFirstVisibleItem;
	private int oldVisibleItemCount;
	private int currentScrollState;
	
	private NamesListViewAdapter adapter;
	
	public NamesListViewScrollListener(NamesListViewAdapter adapter) {
		this.currentPosition = 0;
		this.totalLoadedCount = 0;
		this.diffCounter = 0;
		this.oldDiffCounter = 0;
		
		this.adapter = adapter;
		
		loadData(currentPosition);
	}
	
	private void loadData(int position) {
		loading = true;
		
		int loadedCount = adapter.loadData(position);
		
		if (loadedCount != 0) {
			currentPosition = position;
			totalLoadedCount = loadedCount;
			halfOfTotal = totalLoadedCount / 2;
		}
		
		loading = false;
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.oldFirstVisibleItem = currentFirstVisibleItem;
		this.oldVisibleItemCount = currentVisibleItemCount;
		
		this.currentFirstVisibleItem = firstVisibleItem;
		this.currentVisibleItemCount = visibleItemCount;
		
		int diff = this.currentFirstVisibleItem - this.oldFirstVisibleItem;
		
		oldDiffCounter = diffCounter;
		
		if (diff > 0) {
			diffCounter++;
		} else if (diff < 0) {
			diffCounter--;
		} else {
			diffCounter = 0;
		}
		
		if (diffCounter == 0) {
			if (oldDiffCounter > 0) {
				currentPosition = currentPosition + oldDiffCounter;
			} else if (oldDiffCounter < 0) {
				currentPosition = currentPosition + oldDiffCounter;
				
				if (currentPosition < 0)
					currentPosition = 0;
			} else {
				return;
			}
			
			oldDiffCounter = 0;
			
			if (!loading)
				adapter.loadData(currentPosition);
		} else {
			return;
		}
		
		/*if (diffCounter > 2) {
			if (firstVisibleItem > halfOfTotal) {
				currentPosition = currentPosition + halfOfTotal;
			}
		} else if (diffCounter < -2) {
			if (firstVisibleItem < halfOfTotal) {
				currentPosition = currentPosition - halfOfTotal;
				
				if (currentPosition < 0)
					currentPosition = 0;
			}
		} else {
			return;
		}
		
		
		if (!loading)
			adapter.loadData(currentPosition);*/
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.currentScrollState = scrollState;
	}
}
