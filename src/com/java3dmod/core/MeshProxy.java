package com.java3dmod.core;

import java.util.ArrayList;

import com.java3dmod.IMeshInfo;
import com.java3dmod.util.ModConstant;

import rajawali.math.Number3D;

	public class MeshProxy implements IMeshInfo {
		protected ArrayList<VertexProxy> vertices;
		protected ArrayList<FaceProxy> faces;
		protected float _maxX;
		protected float _maxY;
		protected float _maxZ;
		
		protected float _minX;
		protected float _minY;
		protected float _minZ;
		
		protected int _maxAxis;
		protected int _midAxis;
		protected int _minAxis;
		
		protected float _width;
		protected float _height;
		protected float _depth;
		
		public MeshProxy() { 
			vertices = new ArrayList<VertexProxy>(); 
			faces = new ArrayList<FaceProxy>();
		}
		
		public void setMesh(Object mesh) { }
		
		public void updateMeshPosition(Number3D p) {}
		
		public ArrayList<VertexProxy> getVertices() { return vertices; }
		
		public ArrayList<FaceProxy> getFaces() { return faces; }
		
		public void analyzeGeometry() {
			int vc = getVertices().size();
			int i;
			VertexProxy v;
			
			for (i = 0; i < vc; i++) {
				v = vertices.get(i);
				
				if (i == 0) {
					_minX = _maxX = v.getX();
					_minY = _maxY = v.getY();
					_minZ = _maxZ = v.getZ();
				} else  {
					_minX = Math.min(_minX, v.getX());
					_minY = Math.min(_minY, v.getY());
					_minZ = Math.min(_minZ, v.getZ());
					
					_maxX = Math.max(_maxX, v.getX()); 
					_maxY = Math.max(_maxY, v.getY()); 
					_maxZ = Math.max(_maxZ, v.getZ()); 
				}
				
				v.setOriginalPosition(v.getX(), v.getY(), v.getZ());
			}
			
			_width = _maxX - _minX;
			_height = _maxY - _minY;
			_depth = _maxZ - _minZ;
			
			
			float maxe = Math.max(_width, Math.max(_height, _depth));
			float mine = Math.min(_width, Math.min(_height, _depth));
			
			if (maxe == _width && mine == _height) {
				_minAxis = ModConstant.Y;
				_midAxis = ModConstant.Z;
				_maxAxis = ModConstant.X;
			} else if (maxe == _width && mine == _depth) {
				_minAxis = ModConstant.Z;
				_midAxis = ModConstant.Y;
				_maxAxis = ModConstant.X;
			} else if (maxe == _height && mine == _width) {
				_minAxis = ModConstant.X;
				_midAxis = ModConstant.Z;
				_maxAxis = ModConstant.Y;
			} else if (maxe == _height && mine == _depth) {
				_minAxis = ModConstant.Z;
				_midAxis = ModConstant.X;
				_maxAxis = ModConstant.Y;
			} else if (maxe == _depth && mine == _width) {
				_minAxis = ModConstant.X;
				_midAxis = ModConstant.Y;
				_maxAxis = ModConstant.Z;
			} else if (maxe == _depth && mine == _height) {
				_minAxis = ModConstant.Y;
				_midAxis = ModConstant.X;
				_maxAxis = ModConstant.Z;
			}
			
			for (i = 0; i < vc; i++) {
				v = vertices.get(i);
				v.setRatios((v.getX() - _minX) / _width, (v.getY() - _minY) / _height, (v.getZ() - _minZ) / _depth);
			}
		}
		
		public void resetGeometry() {
			int vc = getVertices().size();
			for (int i = 0; i < vc; i++) {
				VertexProxy v = vertices.get(i);
				v.reset();
			}
		}
		
		public void collapseGeometry() {
			int vc = getVertices().size();
			for (int i = 0; i < vc; i++) {
				VertexProxy v = vertices.get(i);
				v.collapse();
			}
			analyzeGeometry();
		}
		
		/** @inheritDoc */
		public float getMinX() { return _minX; }
		/** @inheritDoc */
		public float getMinY() { return _minY; }
		/** @inheritDoc */
		public float getMinZ() { return _minZ; }
		
		public float getMin(int axis) {
			switch(axis) {
				case ModConstant.X: return _minX;
				case ModConstant.Y: return _minY;
				case ModConstant.Z: return _minZ;
			}
			return -1;
		}
		
		/** @inheritDoc */
		public float getMaxX() { return _maxX; }
		/** @inheritDoc */
		public float getMaxY() { return _maxY; }
		/** @inheritDoc */
		public float getMaxZ() { return _maxZ; }
		
		public float getMax(int axis) {
			switch(axis) {
				case ModConstant.X: return _maxX;
				case ModConstant.Y: return _maxY;
				case ModConstant.Z: return _maxZ;
			}
			return -1;
		}
		
		public int getMaxAxis() { return _maxAxis; }
		public int getMidAxis() { return _midAxis; }
		public int getMinAxis() { return _minAxis; }
		
		public float getSize(int axis) {
			switch(axis) {
				case ModConstant.X: return _width;
				case ModConstant.Y: return _height;
				case ModConstant.Z: return _depth;
			}
			return -1;
		}
		
		/** @inheritDoc */
		public float getWidth() { return _width; }
		/** @inheritDoc */
		public float getHeight() { return _height; }
		/** @inheritDoc */
		public float getDepth() { return _depth; }
		
		public void updateVertices() {}
	}
