package com.java3dmod.core;

import java.util.ArrayList;
/** Modifier class is the base class for all modifier classes. */
public class Modifier {
	/** Mesh, the geometry of which changes the current modifier. @private */
	protected MeshProxy mod;

	/** Creates a new instance of the class Modifier. */
	public Modifier(){}
	
	/**
	 * Defines the mesh, the geometry of which will change the current modifier.
	 * @param	mod	 mesh, the geometry of which will change the current modifier.
	 */
	public void setModifiable(MeshProxy mod) { this.mod = mod; }
	
	/**
	 * Returns the list of vertices of the mesh. 
	 * @return	vertex list of the mesh.
	 */
	public ArrayList<VertexProxy> getVertices() { return mod.getVertices(); }
}
