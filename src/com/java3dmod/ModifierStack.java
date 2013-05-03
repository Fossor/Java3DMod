package com.java3dmod;

import java.util.ArrayList;

import com.java3dmod.core.MeshProxy;
import com.java3dmod.plugin.Library3d;
import com.java3dmod.plugin.PluginFactory;

public class ModifierStack {

//	private Library3d lib3d;
	private MeshProxy baseMesh;
	private ArrayList<IModifier> stack;
//	private Class<?> MeshProxyClass;

public ModifierStack(Library3d lib3d, Object mesh) {
//	this.lib3d = lib3d;
//	MeshProxyClass = PluginFactory.getMeshProxyClass(lib3d);
	baseMesh = PluginFactory.getMeshProxy(lib3d);
	baseMesh.setMesh(mesh);
	baseMesh.analyzeGeometry();
	stack = new ArrayList<IModifier>();
}

public void addModifier(IModifier mod) {
	mod.setModifiable(baseMesh);
	stack.add(mod);
}

public void removeModifier(IModifier mod) {
	stack.remove(mod);//.splice(stack.indexOf(mod), 1);
}

public void apply() {
	baseMesh.resetGeometry();
	int vl = stack.size();
	for (int i = 0; i < vl; i++) stack.get(i).apply();
	baseMesh.updateVertices();
}

public void collapse() {
	apply();
	baseMesh.collapseGeometry();
	stack = new ArrayList<IModifier>();
}

public void clear() { stack = new ArrayList<IModifier>(); }

public IMeshInfo getMeshInfo() { return baseMesh; }
}
