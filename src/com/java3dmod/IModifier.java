package com.java3dmod;


import com.java3dmod.core.MeshProxy;
/** Interface IModifier used to define modifiers classes */
public interface IModifier {
	/**
	 * Defines mesh, which geometry will be modified
	 * @param	mod	 mesh, which geometry will be modified
	 */
	void setModifiable(MeshProxy mod);
	/** Applies modifier to the mesh */
	void apply();
}
