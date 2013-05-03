package com.java3dmod.plugin;

import com.java3dmod.core.MeshProxy;

public class PluginFactory {
	public static Class<?> getMeshProxyClass(Library3d lib3d) {
		try {
			return Class.forName(lib3d.getMeshClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static MeshProxy getMeshProxy(Library3d lib3d) {
		Class<?> clazz = getMeshProxyClass(lib3d);
		try {
//			final Class<?> result = ctor.newInstance();
	        return (MeshProxy)(clazz.newInstance());
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("PluginFactory getMeshProxy IllegalArgumentException");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			System.out.println("PluginFactory getMeshProxy InstantiationException");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("PluginFactory getMeshProxy IllegalAccessException");
		} 
		
		return null;
		
	}
	
}
