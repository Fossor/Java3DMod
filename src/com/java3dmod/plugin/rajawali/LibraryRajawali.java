package com.java3dmod.plugin.rajawali;

//import com.javamod.core.MeshProxy;
//import com.javamod.core.VertexProxy;
import com.java3dmod.plugin.Library3d;

public class LibraryRajawali extends Library3d {

	public LibraryRajawali() {
//		MeshProxy m = new RajawaliMesh();
//		VertexProxy v = new RajawaliVertex();
	}
	
	/** @inheritDoc */
	@Override
	public String getId() { return "Rajawali"; }
	/** @inheritDoc */
	@Override
	public String getMeshClass() { return "com.javamod.plugin.rajawali.RajawaliMesh"; }
	/** @inheritDoc */
	@Override
	public String getVertexClass() { return "com.javamod.plugin.rajawali.RajawaliVertex"; }
	
}
