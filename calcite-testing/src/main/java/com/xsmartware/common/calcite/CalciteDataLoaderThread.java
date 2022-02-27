package com.xsmartware.common.calcite;

public class CalciteDataLoaderThread extends Thread{
	
	DataHolder holder ;
	
	public CalciteDataLoaderThread(DataHolder holder) {
		this.holder = holder;
	}
	
	@Override
	public void run() {
		if(holder == null || holder.loader == null)
			return;
		try {
			holder.db = holder.loader.loadData();
		}catch(Throwable ex) {
			ex.printStackTrace();
		}
	}

}
