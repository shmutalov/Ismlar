package com.epikur.ismlar;

import android.widget.AbsListView;

public class NamesListViewScrollListener implements AbsListView.OnScrollListener {
	private boolean loading;
	
	private int currentPosition;
	
	private int currentFirstVisibleItem;
	private int currentVisibleItemCount;
	private int currentTotalItemCount;
	
	private NamesListViewAdapter adapter;
	
	public NamesListViewScrollListener(NamesListViewAdapter adapter) {
		this.currentPosition = 0;
		this.adapter = adapter;
		
		loadData(currentPosition);
	}
	
	private void loadData(int position) {
		loading = true;
		
		int loadedCount = adapter.loadData(position);
		
		if (loadedCount != 0) {
			currentPosition = position;
		}
		
		loading = false;
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.currentFirstVisibleItem = firstVisibleItem;
		this.currentVisibleItemCount = visibleItemCount;
		
		this.currentTotalItemCount = totalItemCount;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE) {
			if ((currentFirstVisibleItem + currentVisibleItemCount) == currentTotalItemCount) {
				if (!loading) {
					int position = currentPosition + 5;
					
					if (adapter.loadData(position) != 0) {
						currentPosition = position;
					} else {
						adapter.loadData(currentPosition);
					}
				}
			} else if (currentFirstVisibleItem == 0) {
				if (!loading) {
					currentPosition -= 5;
					
					if (currentPosition < 0)
						currentPosition = 0;
					
					adapter.loadData(currentPosition);
				}
			}
		}
	}
}
