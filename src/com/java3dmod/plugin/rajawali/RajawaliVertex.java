package com.java3dmod.plugin.rajawali;

import com.java3dmod.core.VertexProxy;

public class RajawaliVertex extends VertexProxy {

	private Vertex vx;

/** Создает новый �?кземпл�?р кла�?�?а Away3dVertex. */
public RajawaliVertex(){}

/** @inheritDoc */
@Override
public void setVertex(Object vertex) {
	vx = (Vertex) vertex;
	ox = vx.getX();
	oy = vx.getY();
	oz = vx.getZ();
}

/** @inheritDoc */
@Override
public float getX() { return vx.getX(); }
/** @inheritDoc */
@Override
public float getY() { return vx.getY(); }
/** @inheritDoc */
@Override
public float getZ() { return vx.getZ(); }
/** @inheritDoc */
@Override
public void setX(float v) { vx.setX(v); }
/** @inheritDoc */
@Override
public void setY(float v) { vx.setY(v); }
/** @inheritDoc */
@Override
public void setZ(float v) { vx.setZ(v); }

}

