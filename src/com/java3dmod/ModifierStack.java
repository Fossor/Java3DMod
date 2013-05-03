/**
 * Copyright (c) 2008 Bartek Drozdz (http://www.everydayflash.com)
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Same license applies to every file in this package and its subpackages.  
 */
package com.java3dmod;

import java.util.ArrayList;

import com.java3dmod.core.MeshProxy;
import com.java3dmod.plugin.Library3d;
import com.java3dmod.plugin.PluginFactory;

/**
 * <p>
 * <h2>Modifier Stack.</h2>
 * </p>
 * <p>
 * Modifier Stack - is a base of Java3DMod. It contains a reference to the mesh and the array with modifier classes.
 * </p>
 * 
 * <p>
 * Author: <a href="http://www.everydayflash.com">Bartek Drozdz</a>
 * </p>
 * <p>
 * Version: 0.1
 * </p>
 */
public class ModifierStack {

	// private Library3d lib3d;
	private MeshProxy baseMesh;
	private ArrayList<IModifier> stack;

	// private Class<?> MeshProxyClass;

	/**
	 * Creates a new instance of the class ModifierStack.
	 * 
	 * @param lib3d
	 *            instance of a class that extends the class com.java3dmod.plugin.Library3d for a particular 3D-engine.
	 * @param mesh
	 *            mesh, the geometry of which will the modifiers change. For example, to Rajawali you need to pass this parameter instance
	 *            com.java3dmod.plugin.rajawali.RajawaliMesh or one of its subclasses.
	 */
	public ModifierStack(Library3d lib3d, Object mesh) {
		// this.lib3d = lib3d;
		// MeshProxyClass = PluginFactory.getMeshProxyClass(lib3d);
		baseMesh = PluginFactory.getMeshProxy(lib3d);
		baseMesh.setMesh(mesh);
		baseMesh.analyzeGeometry();
		stack = new ArrayList<IModifier>();
	}

	/**
	 * Adds modifier to the stack.
	 * 
	 * @param mod
	 *            modifier. Modifiers are applied to the geometry of the mesh in the order they are added to the stack. That is, the first modifier
	 *            added to the stack will be applied to the geometry of the mesh as the first.
	 */
	public void addModifier(IModifier mod) {
		mod.setModifiable(baseMesh);
		stack.add(mod);
	}

	/**
	 * Removes the modifier from the stack. @ param mod modifier that should be removed from the stack.
	 */
	public void removeModifier(IModifier mod) {
		stack.remove(mod);// .splice(stack.indexOf(mod), 1);
	}

	/**
	 * Applies all current modifiers in the stack to the geometry of the mesh. Each time you call the <code> apply () </ code>, all the changes are
	 * applied to the geometry of the mesh previously cleared and modifiers apply again to the original geometry of the mesh. In order to replace the
	 * original geometry of the current mesh, call <code> collapse () </ code>.
	 * 
	 * @see #collapse()
	 */
	public void apply() {
		baseMesh.resetGeometry();
		int vl = stack.size();
		for (int i = 0; i < vl; i++)
			stack.get(i).apply();
		baseMesh.updateVertices();
	}

	/**
	 * Destroys the stack. Invoking this method will lead to the fact that all of the current modifiers are in the stack will be applied to the
	 * geometry of the mesh, and then removed from the stack. The initial geometry of the mesh will then be replaced by the current one, resulting in
	 * the application of modifiers.
	 */
	public void collapse() {
		apply();
		baseMesh.collapseGeometry();
		stack = new ArrayList<IModifier>();
	}

	/** Clears the stack. Removes all of the modifiers stack. */
	public void clear() {
		stack = new ArrayList<IModifier>();
	}

	/**
	 * An object that contains information about the modified mesh.
	 * 
	 * @see com.as3dmod.IMeshInfo
	 */
	public IMeshInfo getMeshInfo() {
		return baseMesh;
	}
}
