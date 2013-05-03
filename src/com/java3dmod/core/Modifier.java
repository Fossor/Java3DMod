package com.java3dmod.core;

import java.util.ArrayList;

public class Modifier {
	protected MeshProxy mod;

	public Modifier(){}
	
	public void setModifiable(MeshProxy mod) { this.mod = mod; }
	
	public ArrayList<VertexProxy> getVertices() { return mod.getVertices(); }
}
