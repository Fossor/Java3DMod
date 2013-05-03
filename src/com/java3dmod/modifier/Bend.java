package com.java3dmod.modifier;

import java.util.ArrayList;

import com.java3dmod.IModifier;
import com.java3dmod.core.MeshProxy;
import com.java3dmod.core.Modifier;
import com.java3dmod.core.VertexProxy;
import com.java3dmod.util.ModConstant;

import android.graphics.Matrix;


	public class Bend extends Modifier implements IModifier{
		private double _pi2a = Math.PI * 2;
		private double _pi2b = Math.PI / 2;
		
		private float _force;
		private float _offset;
		private float _angle;
		private float _diagAngle;
		private int _constraint = ModConstant.NONE;
		private boolean _switchAxes = false;
		
		private int _max;
		private int _min;
		private int _mid;
		private float _width;
		private float _height;
		private float _origin;
		private Matrix _m1;
		private Matrix _m2;
		private float[] _temp;
		
		
		public Bend(float f, float o, float a) {
			_force = f;
			_offset = o;
			
			_m1 = new Matrix();
			_m2 = new Matrix();
			_temp = new float[2];
			
			setAngle(a);
		}
		
		/** @inheritDoc */
		public void setModifiable(MeshProxy mod) {
			this.mod=mod;
			setSwitchAxes(_switchAxes);
		}
		
		/** Switches bend axis*/
		public boolean getSwitchAxes() { return _switchAxes; }
		public void setSwitchAxes(boolean value) { 
			_max = value ? this.mod.getMidAxis() : this.mod.getMaxAxis();
			_min = this.mod.getMinAxis();
			_mid = value ? this.mod.getMaxAxis() : this.mod.getMidAxis();
			_width = this.mod.getSize(_max);	
			_height = this.mod.getSize(_mid);
			_origin = this.mod.getMin(_max);
			_diagAngle = (float) Math.atan(_width / _height);
			_switchAxes = value;
			
			
		}
		
		public void setForce(float f) { _force = f; }		
		public float getForce() { return _force; }
		
		public float getOffset() { return _offset; }
		public void setOffset(float offset) { _offset = offset; }
		
		public void setConstraint(int c) { _constraint = c; }
		public int getConstraint() { return _constraint; }
		
		public float getDiagAngle() { return _diagAngle; }
		
		public float getAngle() { return _angle; }
		
		public void setAngle(float a) { 
			_angle = a; 
			_m1.reset();
			_m2.reset();
			_m1.setRotate(_angle);
			_m2.setRotate(-_angle);
		}
		
		/** @inheritDoc */
		public void apply() {	
			if(getForce() == 0) return;

			ArrayList<VertexProxy> vs = mod.getVertices();
			int vc = vs.size();
			
			float distance = _origin + _width * _offset;
			float radius = (float) (_width / Math.PI / _force);
			float bendAngle = (float) (_pi2a * (_width / (radius * _pi2a)));
			float f = (float) (_pi2b - bendAngle * _offset);

			for (int i = 0; i < vc; i++) {
				VertexProxy v = vs.get(i);
				
				float vmax = _temp[0] = v.getValue(_max);
				float vmid = _temp[1] = v.getValue(_mid);
				float vmin = v.getValue(_min);
				
				float[] np=new float[2];
				_m1.mapPoints(np, _temp);
				vmax = np[0];
				vmid = np[1];

				float p = (vmax - _origin) / _width;

				if ((getConstraint() == ModConstant.LEFT && p <= _offset) || (getConstraint() == ModConstant.RIGHT && p >= _offset)) {	
				} else {
					float fa = f + bendAngle * p;
					float op = (float) (Math.sin(fa) * (radius + vmin));
					float ow = (float) (Math.cos(fa) * (radius + vmin));
					vmin = op - radius;
					vmax = distance - ow;
				}
				
				_temp[0] = vmax;
				_temp[1] = vmid;
				float[] np2=new float[2];
				_m2.mapPoints(np2, _temp);
				vmax = np2[0];
				vmid = np2[1];
				
				v.setValue(_max, vmax);
				v.setValue(_mid, vmid);
				v.setValue(_min, vmin);
				
			}
		}
	}
