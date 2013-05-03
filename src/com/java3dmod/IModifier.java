package com.java3dmod;

import com.java3dmod.core.MeshProxy;

public interface IModifier {
	void setModifiable(MeshProxy mod);
	
	void apply();
}
