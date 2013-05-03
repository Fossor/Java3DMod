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

/**
 * Создает новый �?кземпл�?р кла�?�?а ModifierStack.
 * @param	lib3d 	�?кземпл�?р кла�?�?а, ра�?шир�?ющего кла�?�? com.as3dmod.plugins.Library3d дл�? конкретного 3D-движка.
 * @param	mesh 	меш, геометрию которого будут измен�?ть модификаторы. 
 * 			�?апример: дл�? PV3D вы должны передать в �?тот параметр �?кземпл�?р кла�?�?а com.as3dmod.plugins.pv3d.Pv3dMesh или одного из его подкла�?�?ов. 
 */
public ModifierStack(Library3d lib3d, Object mesh) {
//	this.lib3d = lib3d;
//	MeshProxyClass = PluginFactory.getMeshProxyClass(lib3d);
	baseMesh = PluginFactory.getMeshProxy(lib3d);
	baseMesh.setMesh(mesh);
	baseMesh.analyzeGeometry();
	stack = new ArrayList<IModifier>();
}

/**
 * Добавл�?ет модификатор в �?тек.
 * @param	mod модификатор. Модификаторы примен�?ют�?�? к геометрии меша в пор�?дке их добавлени�? в �?тек.
 * 			То е�?ть, первый добавленный модификатор в �?тек, будет применен к геометрии меша также первым.
 */
public void addModifier(IModifier mod) {
	mod.setModifiable(baseMesh);
	stack.add(mod);
}

/**
 * Удал�?ет модификатор из �?тека.
 * @param	mod модификатор, который �?ледует удалить из �?тека.
 */
public void removeModifier(IModifier mod) {
	stack.remove(mod);//.splice(stack.indexOf(mod), 1);
}

/**
 *  Примен�?ет в�?е текущие модификаторы наход�?щие�?�? в �?теке к геометрии меша. 
 *  При каждом вызове метода <code>apply()</code>, в�?е изменени�? примененные к геометрии меша
 *  ранее �?бра�?ывают�?�? и модификаторы примен�?ют�?�? �?нова к и�?ходной геометрии меша.
 *  Дл�? того чтобы заменить и�?ходную геометрию меша текущей, вызовите метод <code>collapse()</code>.
 * 
 * 	@see #collapse()
 */
public void apply() {
	baseMesh.resetGeometry();
	int vl = stack.size();
	for (int i = 0; i < vl; i++) stack.get(i).apply();
	baseMesh.updateVertices();
}

/**
 * 	Разрушает �?тек. 
 *  Вызов �?того метода приведет к тому, что в�?е текущие модификаторы наход�?щие�?�? в �?теке
 *  будут применены к геометрии меша, а затем удалены из �?тека. И�?ходна�? геометри�? меша
 *  при �?том будет заменена на текущую, полученную в результате применени�? модификаторов.
 */
public void collapse() {
	apply();
	baseMesh.collapseGeometry();
	stack = new ArrayList<IModifier>();
}

/** Очищает �?тек. Удал�?ет в�?е модификаторы из �?тека. */
public void clear() { stack = new ArrayList<IModifier>(); }

/**
 * Объект, �?одержащий информацию о модифицированном меше.
 * @see com.as3dmod.IMeshInfo
 */
public IMeshInfo getMeshInfo() { return baseMesh; }
}
