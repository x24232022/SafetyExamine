package com.avicsafety.ShenYangTowerComService.yd.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

public class D_FeedBak {

	// 最终问题定位：positioningProblem
	public String[] getTypeValue(Activity activity, String pType,
                                 String pValue, String nType) {
		// TODO 自动生成的方法存根

		DBHelper dbHelper = new DBHelper(activity);
		SQLiteDatabase db = dbHelper.openDatabase(activity);

		Cursor cursor = db.query(true, "feedbackTMP", new String[] { nType },
				pType + "=?", new String[] { pValue }, null, null, null, null);

		List<String> list = new ArrayList<String>();

		while (cursor.moveToNext()) {
			String nValue = cursor.getString(cursor.getColumnIndex(nType));
			list.add(nValue);
		}

		cursor.close();
		db.close();

		String[] sA = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			sA[i] = list.get(i);
		}
		return sA;
	}
}
