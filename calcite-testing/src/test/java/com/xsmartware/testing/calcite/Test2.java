package com.xsmartware.testing.calcite;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import org.junit.Test;
import com.xsmartware.common.calcite.CalCiteDataQueryThread;
import com.xsmartware.common.calcite.CalciteDataLoaderThread;
import com.xsmartware.common.calcite.DataHolder;
import com.xsmartware.common.calcite.DataLoader;


public class Test2 {
	
	
	@Test
	public void test2() {
		DataHolder holder = new DataHolder();
		DataLoader loader = null;
		try {
			loader = loadLoaderFromJar();
			holder.loader = loader;
			
			CalciteDataLoaderThread t1 = new CalciteDataLoaderThread(holder);
			t1.start();
			t1.join();
			CalCiteDataQueryThread t2 = new CalCiteDataQueryThread(holder, 10);
			t2.start();
			t2.join();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	
	public DataLoader loadLoaderFromJar(){
		String path = "lib" + File.separator + "data-loader-0.0.1.jar";
		DataLoader loader = null;
		try {
			ClassLoader cl = URLClassLoader.newInstance(
					new URL[] { new URL("jar:file:" + path + "!/") },
					getClass().getClassLoader());
			Class<?> clazz = Class.forName("com.xsmartware.testing.calcite.TestDataLoader", true, cl);
			Class<? extends DataLoader> newClass = clazz.asSubclass(DataLoader.class);

			Constructor<? extends DataLoader> constructor = newClass.getConstructor();
			loader = (DataLoader) constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loader;
	}
	
}
