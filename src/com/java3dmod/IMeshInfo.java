package com.java3dmod;
/**
 * <p><h2>IMeshInfo</h2></p>
 * 
 * <p>Basic mesh information (size, position).</p>
 * 
 * 	@version 1.0
 * 	@author Bartek Drozdz
 */
public interface IMeshInfo {
	float getMinX();
	float getMinY();
	float getMinZ();
	float getMaxX();
	float getMaxY();
	float getMaxZ();
	
	float getWidth();
	float getHeight();
	float getDepth();
}
