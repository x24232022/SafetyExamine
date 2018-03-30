package com.avicsafety.lib.Adapter;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.avicsafety.lib.R;
import com.avicsafety.lib.tools.GetPinYin;
import com.avicsafety.lib.tools.GetPinYin.Type;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class AutoTextListAdapter extends BaseAdapter implements Filterable {

	private ArrayFilter mFilter;
	private List<Common_Model> mList;
	private Context context;
	private ArrayList<Common_Model> mUnfilteredData;
	private List<Common_Model> mFliterData = new ArrayList<Common_Model>();

	public AutoTextListAdapter(List<Common_Model> mList, Context context) {
		this.mList = mList;
		this.context = context;
	}

	@Override
	public int getCount() {

		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if (convertView == null) {
			view = View.inflate(context, R.layout.item_lib_search, null);

			holder = new ViewHolder();
			holder.tv_name = (TextView) view.findViewById(R.id.lib_tv_name);
			holder.tv_other = (TextView) view.findViewById(R.id.lib_tv_other);

			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		Common_Model m = mList.get(position);

		holder.tv_name.setText(m.getCommon_Name());
		holder.tv_other.setText(m.getCommon_OtherName());

		return view;
	}

	static class ViewHolder {
		public TextView tv_name;
		public TextView tv_other;
	}

	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new ArrayFilter();
		}
		return mFilter;
	}

	public List<Common_Model> getmFliterData() {
		return mFliterData;
	}

	public Common_Model getmFliterItem(int position) {
		return mFliterData.get(position);
	}

	private class ArrayFilter extends Filter {
		GetPinYin gpy = new GetPinYin();

		@Override
		protected FilterResults performFiltering(CharSequence prefix) {
			FilterResults results = new FilterResults();

			if (mUnfilteredData == null) {
				mUnfilteredData = new ArrayList<Common_Model>(mList);
			}

			if (prefix == null || prefix.length() == 0) {
				ArrayList<Common_Model> list = mUnfilteredData;
				results.values = list;
				results.count = list.size();
			} else {
				String prefixString = prefix.toString().toLowerCase()
						.replace("v", "u:");
				String pinyin = "";
				try {
					pinyin = GetPinYin.toPinYin(prefixString, "", Type.LOWERCASE);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				ArrayList<Common_Model> unfilteredValues = mUnfilteredData;
				int count = unfilteredValues.size();

				ArrayList<Common_Model> newValues = new ArrayList<Common_Model>(
						count);

				for (int i = 0; i < count; i++) {
					Common_Model pc = unfilteredValues.get(i);

					if (pc != null) {

						if (pc.getCommon_PinyinName() != null) {
							if (pc.getCommon_PinyinName()
									.contains(prefixString)) {
								newValues.add(pc);
							} else

							if (pc.getCommon_PinyinName().contains(pinyin)) {
								newValues.add(pc);
							}

						}
					}
				}

				results.values = newValues;
				results.count = newValues.size();
				mFliterData = newValues;
			}

			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			// noinspection unchecked
			mList = (List<Common_Model>) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}

	}

}
