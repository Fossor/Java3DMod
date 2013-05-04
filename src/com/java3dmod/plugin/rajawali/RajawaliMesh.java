package com.java3dmod.plugin.rajawali;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import rajawali.BaseObject3D;
import rajawali.math.Number3D;

import com.java3dmod.core.FaceProxy;
import com.java3dmod.core.MeshProxy;

public class RajawaliMesh extends MeshProxy {

	private BaseObject3D awm;
	private FloatBuffer v;

	/** Constructor */
	public RajawaliMesh() {
	}

	/** @inheritDoc */
	@Override
	public void setMesh(Object mesh) {
		awm = (BaseObject3D) mesh;

		if (awm.getGeometry() == null||awm.getGeometry().getVertices()==null){
			throw new RuntimeException("No geometry found");
		}else if(awm.getGeometry().getVertices().capacity() == 0){
			throw new RuntimeException("No geometry found");
		}
		
		int i;
		FloatBuffer vs = awm.getGeometry().getVertices();
		int vc = vs.capacity();

		int vt = awm.getGeometry().getIndices().capacity();

		for (i = 0; i < vc; i += 3) {
			RajawaliVertex nv = new RajawaliVertex();
			nv.setVertex(new Vertex(vs.get(i), vs.get(i + 1), vs.get(i + 2)));
			vertices.add(nv);
		}

		for (i = 0; i < vt; i += 3) {
			FaceProxy nt = new FaceProxy();
			if ((awm.getGeometry().getIndices() instanceof IntBuffer)) {
				nt.addVertex(vertices.get(((IntBuffer) awm.getGeometry().getIndices()).get(i)));
				nt.addVertex(vertices.get(((IntBuffer) awm.getGeometry().getIndices()).get(i + 1)));
				nt.addVertex(vertices.get(((IntBuffer) awm.getGeometry().getIndices()).get(i + 2)));
			} else {
				nt.addVertex(vertices.get(((ShortBuffer) awm.getGeometry().getIndices()).get(i)));
				nt.addVertex(vertices.get(((ShortBuffer) awm.getGeometry().getIndices()).get(i + 1)));
				nt.addVertex(vertices.get(((ShortBuffer) awm.getGeometry().getIndices()).get(i + 2)));
			}
			faces.add(nt);
		}
		// }
	}

	/** @inheritDoc */
	@Override
	public void updateVertices() {
		v = awm.getGeometry().getVertices();

		// ArrayList<VertexProxy> vs = getVertices();
		int vc = vertices.size();

		for (int i = 0; i < vc; i++) {
			v.put(i * 3, vertices.get(i).getX());
			v.put(i * 3 + 1, vertices.get(i).getY());
			v.put(i * 3 + 2, vertices.get(i).getZ());
		}
		awm.getGeometry().changeBufferData(awm.getGeometry().getVertexBufferInfo(), v, 0, v.limit());
	}

	/** @inheritDoc */
	@Override
	public void updateMeshPosition(Number3D p) {
		awm.setX(awm.getX() + p.x);
		awm.setY(awm.getY() + p.y);
		awm.setZ(awm.getZ() + p.z);
	}
}
