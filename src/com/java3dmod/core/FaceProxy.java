package com.java3dmod.core;

import java.util.ArrayList;

/** Class FaceProxy is a triangle mesh geometry. */
public class FaceProxy {
	private ArrayList<VertexProxy> _vertices;

	/** Creates a new instance of the class FaceProxy. */
	public FaceProxy() {
		_vertices = new ArrayList<VertexProxy>();
	}

	/**
	 * Adds vertex to the triangle.
	 * 
	 * @param v
	 *            vertex, which should be added to the triangle.
	 */
	public void addVertex(VertexProxy v) {
		_vertices.add(v);
	}

	/** The list of vertices of which is a triangle. */
	public ArrayList<VertexProxy> getVertices() {
		return _vertices;
	}
}
