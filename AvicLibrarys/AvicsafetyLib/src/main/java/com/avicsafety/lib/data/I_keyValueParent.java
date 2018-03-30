package com.avicsafety.lib.data;

public interface I_keyValueParent extends I_keyValue {
	String getParentId();
	String getLeaf();
	void setParentId(String ParentId);
	void setLeaf(String Leaf);
	
}
