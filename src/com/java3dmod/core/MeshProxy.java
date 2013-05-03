package com.java3dmod.core;

import java.util.ArrayList;

import com.java3dmod.IMeshInfo;
import com.java3dmod.util.ModConstant;

import rajawali.math.Number3D;

	/**
	 * Кла�?�? MeshProxy �?вл�?ет�?�? базовым кла�?�?ом дл�? в�?ех кла�?�?ов, пред�?тавл�?ющих из
	 * �?еб�? меш какого-то отдельного 3D-движка. Дл�? каждого 3D-движка, должен 
	 * быть �?оздан подкла�?�? �?того кла�?�?а �?о �?воей реализацией некоторых методов, характерных
	 * дл�? �?того 3D-движка.
	 */
	public class MeshProxy implements IMeshInfo {
		/** Cпи�?ок вершин меша. @private */
		protected ArrayList<VertexProxy> vertices;
		/** Cпи�?ок треугольников меша. @private */
		protected ArrayList<FaceProxy> faces;
		/** Мак�?имальна�? граница меша по о�?и X. @private */		
		protected float _maxX;
		/** Мак�?имальна�? граница меша по о�?и Y. @private */
		protected float _maxY;
		/** Мак�?имальна�? граница меша по о�?и Z. @private */
		protected float _maxZ;
		
		/** Минимальна�? граница меша по о�?и X. @private */
		protected float _minX;
		/** Минимальна�? граница меша по о�?и Y. @private */
		protected float _minY;
		/** Минимальна�? граница меша по о�?и Z. @private */
		protected float _minZ;
		
		/** О�?ь координат, вдоль которой размеры меша мак�?имальные. @private */
		protected int _maxAxis;
		/** О�?ь координат, вдоль которой размеры меша �?редние. @private */
		protected int _midAxis;
		/** О�?ь координат, вдоль которой размеры меша минимальные. @private */
		protected int _minAxis;
		
		/** Размеры меша по о�?и X. @private */
		protected float _width;
		/** Размеры меша по о�?и Y. @private */
		protected float _height;
		/** Размеры меша по о�?и Z. @private */
		protected float _depth;
		
		/** Создает новый �?кземпл�?р кла�?�?а MeshProxy. */
		public MeshProxy() { 
			vertices = new ArrayList<VertexProxy>(); 
			faces = new ArrayList<FaceProxy>();
		}
		
		/**
		 * У�?танавливает меш, геометри�? которого будет измен�?ть�?�? кла�?�?ами-модификаторами.
		 * @param	mesh	меш, геометри�? которого будет измен�?ть�?�? кла�?�?ами-модификаторами.
		 */
		public void setMesh(Object mesh) { }
		
		/**
		 * Обновл�?ет позицию меша.
		 * @param	p нова�? позици�? меша.
		 */
		public void updateMeshPosition(Number3D p) {}
		
		/**
		 * Возвращает �?пи�?ок вершин меша. 
		 * @return	�?пи�?ок вершин меша.
		 */
		public ArrayList<VertexProxy> getVertices() { return vertices; }
		
		/**
		 * Возвращает �?пи�?ок треугольников меша. 
		 * @return	�?пи�?ок треугольников меша. 
		 */
		public ArrayList<FaceProxy> getFaces() { return faces; }
		
		/** �?нализирует геометрию меша, вычи�?л�?�? некоторые значени�? необходимые кла�?�?ам-модификаторам дл�? работы. */
		public void analyzeGeometry() {
			int vc = getVertices().size();
			int i;
			VertexProxy v;
			
			for (i = 0; i < vc; i++) {
				v = vertices.get(i);
				
				//находим минимальные и мак�?имальные границы меша
				//по каждой из о�?и координат
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
			
			//вычи�?л�?ем размеры меша по каждой из о�?и координат
			_width = _maxX - _minX;
			_height = _maxY - _minY;
			_depth = _maxZ - _minZ;
			
			
			float maxe = Math.max(_width, Math.max(_height, _depth));
			float mine = Math.min(_width, Math.min(_height, _depth));
			
			//вычи�?л�?ем по каким о�?�?м координат размеры меша
			//мак�?имальные и минимальные.
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
		
		/** Сбра�?ывает текущее �?о�?то�?ние геометрии меша на и�?ходное, которое было до применени�? модификаторов. */
		public void resetGeometry() {
			int vc = getVertices().size();
			for (int i = 0; i < vc; i++) {
				VertexProxy v = vertices.get(i);
				v.reset();
			}
		}
		
		/** Указывает, что текущее �?о�?то�?ние геометрии меша теперь �?ледует �?читать как и�?ходное. */
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
		
		/**
		 * Возвращает минимальную границу меша по указанной о�?и.
		 * @param	axis	название о�?и координат. 
		 * @return			минимальна�? граница меша по указанной о�?и.
		 */
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
		
		/**
		 * Возвращает мак�?имальную границу меша по указанной о�?и.
		 * @param	axis	название о�?и координат. 
		 * @return			мак�?имальна�? граница меша по указанной о�?и.
		 */
		public float getMax(int axis) {
			switch(axis) {
				case ModConstant.X: return _maxX;
				case ModConstant.Y: return _maxY;
				case ModConstant.Z: return _maxZ;
			}
			return -1;
		}
		
		/** О�?ь координат, вдоль которой размеры меша мак�?имальные. */
		public int getMaxAxis() { return _maxAxis; }
		/** О�?ь координат, вдоль которой размеры меша �?редние. */
		public int getMidAxis() { return _midAxis; }
		/** О�?ь координат, вдоль которой размеры меша минимальные. */
		public int getMinAxis() { return _minAxis; }
		
		/**
		 * Возвращает размеры меша по указанной о�?и.
		 * @param	axis	название о�?и координат. 
		 * @return			размеры меша по указанной о�?и.
		 */
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
		
		/** Обновл�?ет вершины меша. */
		public void updateVertices() {}
	}
