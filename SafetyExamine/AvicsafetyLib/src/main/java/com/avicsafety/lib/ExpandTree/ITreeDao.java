package com.avicsafety.lib.ExpandTree;

import java.util.List;

public interface ITreeDao {
	List<ITreeCollection> getList(String parent, String leaf, String param, String text);
}
