package com.java3dmod.core;

import java.util.ArrayList;

public class Modifier {
	protected MeshProxy mod;

	/** Создает новый �?кземпл�?р кла�?�?а Modifier. */
	public Modifier(){}
	
	/**
	 * Определ�?ет меш, геометри�? которого будет измен�?ть�?�? текущим модификатором.
	 * @param	mod	 меш, геометри�? которого будет измен�?ть�?�? текущим модификатором.
	 */
	public void setModifiable(MeshProxy mod) { this.mod = mod; }
	
	/**
	 * Возвращает �?пи�?ок вершин меша. 
	 * @return	�?пи�?ок вершин меша.
	 */
	public ArrayList<VertexProxy> getVertices() { return mod.getVertices(); }
}
