package com.java3dmod.modifier;

import java.util.ArrayList;

import rajawali.math.Number3D;

import com.java3dmod.IModifier;
import com.java3dmod.core.Modifier;
import com.java3dmod.core.VertexProxy;

public class Bloat extends Modifier implements IModifier {

	private Number3D _center = new Number3D();

	/** Coordinates of a mesh center. */
	public Number3D getCenter() {
		return _center;
	}

	public void setCenter(Number3D v) {
		_center = v;
	}

	private float radius = 0;

	/** Bloat radius. */
	public float getRadius() {
		return radius;
	}

	public void setRadius(float v) {
		radius = Math.max(0, v);
	}

	private float impact = 0.01f;

	/** Modifiers impact */
	public float getImpact() {
		return impact;
	}

	public void setImpact(float v) {
		impact = Math.max(0, v);
	}

	private Number3D _u = new Number3D();

	/** New Bloat instance. */
	public Bloat() {
	}

	/** @inheritDoc */
	public void apply() {
		ArrayList<VertexProxy> vs = mod.getVertices();

		int len = vs.size();
		for (int i = 0; i < len; i++) {

			// distance from modifier center to the vertex
			VertexProxy v = vs.get(i);
			_u.x = v.getX() - _center.x;
			_u.y = v.getY() - _center.y;
			_u.z = v.getZ() - _center.z;

			// changing norm to norm + r * exp (-a * norm)
			float nn = (float) (_u.length() + radius * Math.exp(-_u.length() * impact));
			_u.normalize();
			_u.x *= nn;
			_u.y *= nn;
			_u.z *= nn;

			// moving vertex
			v.setX(_u.x + _center.x);
			v.setY(_u.y + _center.y);
			v.setZ(_u.z + _center.z);
		}
	}
}
