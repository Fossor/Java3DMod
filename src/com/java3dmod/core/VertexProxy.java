package com.java3dmod.core;

import com.java3dmod.util.ModConstant;

import rajawali.math.Number3D;


//import com.as3dmod.util.ModConstant;	
//
//import flash.geom.Vector3D;

/**
 * Кла�?�? VertexProxy �?вл�?ет�?�? базовым кла�?�?ом дл�? в�?ех кла�?�?ов, пред�?тавл�?ющих из
 * �?еб�? вершину меша какого-то отдельного 3D-движка. Дл�? каждого 3D-движка, должен 
 * быть �?оздан подкла�?�? �?того кла�?�?а �?о �?воей реализацией некоторых методов, характерных
 * дл�? �?того 3D-движка.
 */
public class VertexProxy {
	/** Cоотношение координаты вершины по о�?и X к размеру меша по о�?и X. */
	private float _ratioX;
	/** Cоотношение координаты вершины по о�?и Y к размеру меша по о�?и Y. */
	private float _ratioY;
	/** Cоотношение координаты вершины по о�?и Z к размеру меша по о�?и Z. */
	private float _ratioZ;
	
	/** И�?ходна�? координата вершины по о�?и X. @private */
	protected float ox;
	/** И�?ходна�? координата вершины по о�?и Y. @private */
	protected float oy;
	/** И�?ходна�? координата вершины по о�?и Z. @private */
	protected float oz;

	/** Создает новый �?кземпл�?р кла�?�?а VertexProxy.*/
	public VertexProxy() {}
	
	/**
	 * Копирует значени�? из переданного объекта в текущий.
	 * @param	vertex	объект, �?одержащий координаты вершины.
	 */
	public void setVertex(Object vertex) {}
	
	/**
	 * У�?танавливает значени�? �?оотношени�? координат вершины к размерам меша по каждой из о�?и координат.
	 * @param	rx	�?оотношение координаты вершины по о�?и X к размеру меша по о�?и X.
	 * @param	ry	�?оотношение координаты вершины по о�?и Y к размеру меша по о�?и Y.
	 * @param	rz	�?оотношение координаты вершины по о�?и Z к размеру меша по о�?и Z.
	 */
	public void setRatios(float rx, float ry, float rz) {
		_ratioX = rx;
		_ratioY = ry;
		_ratioZ = rz;
	}
	
	/**
	 * У�?танавливает и�?ходные координаты вершины.
	 * @param	ox	и�?ходна�? координата вершины по о�?и X.
	 * @param	oy	и�?ходна�? координата вершины по о�?и Y.
	 * @param	oz	и�?ходна�? координата вершины по о�?и Z.
	 */
	public void setOriginalPosition(float ox, float oy, float oz) {
		this.ox = ox;
		this.oy = oy;
		this.oz = oz;
	}
	
	/** Текуща�? координата вершины по о�?и X. */
	public float  getX() { return 0; }
	public void setX(float v) { }
	
	/** Текуща�? координата вершины по о�?и Y. */
	public float  getY() { return 0; }
	public void setY(float v) { }
	
	/** Текуща�? координата вершины по о�?и Z. */
	public float  getZ() { return 0; }
	public void setZ(float v) { }
	
	/**
	 * Возвращает текущую координату вершины по указанной о�?и.
	 * @param	axis	название о�?и координат.
	 * @return			текуща�? координата вершины по указанной о�?и.
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
	 * Перезапи�?ывает текущую координату вершины по указанной о�?и. 
	 * @param	axis	название о�?и координат.
	 * @param	v		нова�? текуща�? координата вершины по указанной о�?и.
	 */
	public void setValue(int axis, float v) {
		switch(axis) {
			case ModConstant.X: setX(v); break;
			case ModConstant.Y: setY(v); break;
			case ModConstant.Z: setZ(v); break;
		}
	}
	
	/** Соотношение координаты вершины по о�?и X к размеру меша по о�?и X. Значение лежит в диапазоне от 0 до 1. */
	public float  getRatioX() { return _ratioX; }
	
	/** Соотношение координаты вершины по о�?и Y к размеру меша по о�?и Y. Значение лежит в диапазоне от 0 до 1. */
	public float  getRatioY() { return _ratioY; }
	
	/** Соотношение координаты вершины по о�?и Z к размеру меша по о�?и Z. Значение лежит в диапазоне от 0 до 1. */
	public float  getRatioZ() { return _ratioZ; }
	
	/**
	 * Возвращает �?оотношение координаты вершины к размеру меша по указанной о�?и.
	 * @param	axis	название о�?и координат.
	 * @return			�?оотношение координаты вершины к размеру меша по указанной о�?и. Значение лежит в диапазоне от 0 до 1.
	 */
	public float  getRatio(int axis) {
		switch(axis) {
			case ModConstant.X: return _ratioX;
			case ModConstant.Y: return _ratioY;
			case ModConstant.Z: return _ratioZ;
		}
		return -1;
	}
	
	/** И�?ходна�? координата вершины по о�?и X. */
	public float  getOriginalX() { return ox; }
	
	/** И�?ходна�? координата вершины по о�?и Y. */
	public float  getOriginalY() { return oy; }
	
	/** И�?ходна�? координата вершины по о�?и Z. */
	public float  getOriginalZ() { return oz; }
	
	/**
	 * Возвращает и�?ходную координату вершины по указанной о�?и.
	 * @param	axis	название о�?и координат.
	 * @return			и�?ходна�? координата вершины по указанной о�?и.
	 */
	public float  getOriginalValue(int axis) {
		switch(axis) {
			case ModConstant.X: return ox;
			case ModConstant.Y: return oy;
			case ModConstant.Z: return oz;
		}
		return 0;
	}
	
	/** Сбра�?ывает текущие координаты вершины на и�?ходные. */
	public void reset() {
		setX(ox);
		setY(oy);
		setZ(oz);
	}
	
	/** Перезапи�?ывает и�?ходные координаты вершины текущими координатами. */
	public void collapse() {
		ox = getX();
		oy = getY();
		oz = getZ();
	}
	
	/** Вектор �? текущими координатами вершины. */
	public Number3D getVector() { return new Number3D(getX(),getY(),getZ()); }
	public void setVector(Number3D v) {
		setX(v.x);
		setX(v.y);
		setX(v.z);
	}
	
	/** Вектор �? значени�?ми �?оотношени�? координат вершины к размерам меша по каждой из о�?и координат. */
	public Number3D getRatioVector() { return new Number3D(getRatioX(), getRatioY(), getRatioZ()); }
}