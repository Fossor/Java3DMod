package com.java3dmod.core;

import com.java3dmod.util.ModConstant;

import rajawali.math.Number3D;


//import com.as3dmod.util.ModConstant;	
//
//import flash.geom.Vector3D;

public class VertexProxy {
	private float _ratioX;
	private float _ratioY;
	private float _ratioZ;
	
	protected float ox;
	protected float oy;
	protected float oz;

	public VertexProxy() {}
	
	public void setVertex(Object vertex) {}
	
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
	
	public float  getX() { return 0; }
	public void setX(float v) { }
	
	public float  getY() { return 0; }
	public void setY(float v) { }
	public float  getZ() { return 0; }
	public void setZ(float v) { }
	
	public float getValue(int axis) {
		switch(axis) {
			case ModConstant.X: return getX();
			case ModConstant.Y: return getY();
			case ModConstant.Z: return getZ();
		}
		return 0;
	}
	
	public void setValue(int axis, float v) {
		switch(axis) {
			case ModConstant.X: setX(v); break;
			case ModConstant.Y: setY(v); break;
			case ModConstant.Z: setZ(v); break;
		}
	}
	
	public float  getRatioX() { return _ratioX; }
	
	public float  getRatioY() { return _ratioY; }
	
	public float  getRatioZ() { return _ratioZ; }
	
	public float  getRatio(int axis) {
		switch(axis) {
			case ModConstant.X: return _ratioX;
			case ModConstant.Y: return _ratioY;
			case ModConstant.Z: return _ratioZ;
		}
		return -1;
	}
	
	public float  getOriginalX() { return ox; }
	
	public float  getOriginalY() { return oy; }
	
	public float  getOriginalZ() { return oz; }
	
	public float  getOriginalValue(int axis) {
		switch(axis) {
			case ModConstant.X: return ox;
			case ModConstant.Y: return oy;
			case ModConstant.Z: return oz;
		}
		return 0;
	}
	
	public void reset() {
		setX(ox);
		setY(oy);
		setZ(oz);
	}
	
	public void collapse() {
		ox = getX();
		oy = getY();
		oz = getZ();
	}
	
	public Number3D getVector() { return new Number3D(getX(),getY(),getZ()); }
	public void setVector(Number3D v) {
		setX(v.x);
		setX(v.y);
		setX(v.z);
	}
	
	public Number3D getRatioVector() { return new Number3D(getRatioX(), getRatioY(), getRatioZ()); }
}