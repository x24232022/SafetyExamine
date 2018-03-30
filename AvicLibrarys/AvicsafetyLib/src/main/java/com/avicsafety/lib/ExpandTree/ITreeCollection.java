package com.avicsafety.lib.ExpandTree;

import java.io.Serializable;

public interface ITreeCollection  {
	int getTreeId();//唯一识别标识
	String getTreeText();//显示
	String getTreeLeaf();//层数标识  1为最后一次
	String getTreeValue();//值得
	String getTreeParent();//父类id
	String getTreeParam();//参数
}
