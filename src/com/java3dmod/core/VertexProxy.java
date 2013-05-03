package com.java3dmod.core;

import com.java3dmod.util.ModConstant;

import rajawali.math.Number3D;


//import com.as3dmod.util.ModConstant;	
//
//import flash.geom.Vector3D;

/**
 * VertexProxy class is the base class for all classes that represent the vertex of the mesh 
 * itself some individual 3D-engine.
 * For each 3D-engine must be created subclass of this class with its implementation 
 * of some of the methods specific for this 3D-engine.
 */
public class VertexProxy {
	private float _ratioX;
	private float _ratioY;
	private float _ratioZ;
	
	protected float ox;
	protected float oy;
	protected float oz;

	public VertexProxy() {}
	
	/**
	 * Copies the value passed into the current object.
	 * @param	vertex	object containing the coordinates of the vertex.
	 */
	public void setVertex(Object vertex) {}
	
	/**
	 * Sets the vertex coordinates relation to the mesh size of each of the coordinate axes.
	 * @param	rx	relation of vertex coordinate on the X axis to the size of the mesh on axis X.
	 * @param	ry	relation of vertex coordinate on the Y axis to the size of the mesh on axis Y.
	 * @param	rz	relation of vertex coordinate on the Z axis to the size of the mesh on axis Z.
	 */
	public void setRatios(float rx, float ry, float rz) {
		_ratioX = rx;
		_ratioY = ry;
		_ratioZ = rz;
	}
	
	public void setOriginalPosition(float ox, float oy, float oz) {
		this.ox = ox;
		this.oy = oy;
		this.oz = oz;
	}
	/** Current coordinates of the vertex along the axis X. */
	public float  getX() { return 0; }
	public void setX(float v) { }
	/** Current coordinates of the vertex along the axis Y. */
	public float  getY() { return 0; }
	public void setY(float v) { }
	/** Current coordinates of the vertex along the axis Z. */
	public float  getZ() { return 0; }
	public void setZ(float v) { }
	
	/**
	 * Returns the current coordinate of the vertex along the axis.
	 * @param	axis	the name of the coordinate axis.
	 * @return			current coordinate of the vertex along the axis.
	 */
	public float getValue(int axis) {
		switch(axis) {
			case ModConstant.X: return getX();
			case ModConstant.Y: return getY();
			case ModConstant.Z: return getZ();
		}
		return 0;
	}
	
	/**
	 * Overwrites the current coordinate of the vertex along the axis. 
	 * @param	axis	the name of the coordinate axis.
	 * @param	v		new coordinate of the vertex along the axis.
	 */
	public void setValue(int axis, float v) {
		switch(axis) {
			case ModConstant.X: setX(v); break;
			case ModConstant.Y: setY(v); break;
			case ModConstant.Z: setZ(v); break;
		}
	}
	
	/** Ratio between vertex coordinates on the X axis and the size of the mesh on the X axis. The value ranges from 0 to 1. */
	public float  getRatioX() { return _ratioX; }
	
	/** Ratio between vertex coordinates on the Y axis and the size of the mesh on the Y axis. The value ranges from 0 to 1. */
	public float  getRatioY() { return _ratioY; }
	
	/** Ratio between vertex coordinates on the Z axis and the size of the mesh on the Z axis. The value ranges from 0 to 1. */
	public float  getRatioZ() { return _ratioZ; }
	
	/**
	 * Returns the ratio between vertex coordinates on the given axis and the size of the mesh on the same axis
	 * @param	axis	the name of the coordinate axis.
	 * @return			ratio between vertex coordinates on the given axis and the size of the mesh on the same axis. The value ranges from 0 to 1.
	 */
	public float  getRatio(int axis) {
		switch(axis) {
			case ModConstant.X: return _ratioX;
			case ModConstant.Y: return _ratioY;
			case ModConstant.Z: return _ratioZ;
		}
		return -1;
	}
	
	/** The initial coordinates of the vertex along the axis X. */
	public float  getOriginalX() { return ox; }
	
	/** The initial coordinates of the vertex along the axis Y. */
	public float  getOriginalY() { return oy; }
	
	/** The initial coordinates of the vertex along the axis Z. */
	public float  getOriginalZ() { return oz; }
	
	/**
	 * Returns the initial coordinates of the vertex along the axis.
	 * @param	axis	the name of the coordinate axis.
	 * @return			the initial coordinates of the vertex along the axis.
	 */
	public float  getOriginalValue(int axis) {
		switch(axis) {
			case ModConstant.X: return ox;
			case ModConstant.Y: return oy;
			case ModConstant.Z: return oz;
		}
		return 0;
	}
	
	/** Resets the current coordinates of the vertices on the source. */
	public void reset() {
		setX(ox);
		setY(oy);
		setZ(oz);
	}
	
	/** Overwrites the original coordinates of the current vertex coordinates. */
	public void collapse() {
		ox = getX();
		oy = getY();
		oz = getZ();
	}
	
	/** Vector with the current coordinates of the vertices. */
	public Number3D getVector() { return new Number3D(getX(),getY(),getZ()); }
	public void setVector(Number3D v) {
		setX(v.x);
		setX(v.y);
		setX(v.z);
	}
	
	/** Vector with coordinate values of the ratio between the mesh size of each of the coordinate axes. */
	public Number3D getRatioVector() { return new Number3D(getRatioX(), getRatioY(), getRatioZ()); }
}