package com.java3dmod.plugin.rajawali;

//import com.java3dmod.core.MeshProxy;
//import com.java3dmod.core.VertexProxy;
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
	public String getMeshClass() { return "com.java3dmod.plugin.rajawali.RajawaliMesh"; }
	/** @inheritDoc */
	@Override
	public String getVertexClass() { return "com.java3dmod.plugin.rajawali.RajawaliVertex"; }
	
}
