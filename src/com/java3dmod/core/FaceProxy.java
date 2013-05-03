package com.java3dmod.core;

import java.util.ArrayList;

public class FaceProxy {
	private ArrayList<VertexProxy> _vertices;

	public FaceProxy() { _vertices = new ArrayList<VertexProxy>(); }
	
	public void addVertex(VertexProxy v) { _vertices.add(v); }
	
	public ArrayList<VertexProxy> getVertices() { return _vertices; }
}
