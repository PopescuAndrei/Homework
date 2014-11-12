package com.example.homework.home;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homework.R;

public class AdapterLinks extends BaseAdapter {

	List<ModelLink> linkList = new ArrayList<ModelLink>();
	Context context;

	public AdapterLinks() {
	}

	public AdapterLinks(Context context) {
		this.context = context;
	}

	public AdapterLinks(Context context, List<ModelLink> list) {
		this.linkList = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		if (linkList != null)
			return linkList.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {

		if (linkList != null)
			return linkList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	public void setItems(List<ModelLink> items) {
		linkList = items;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder viewHolder = new Holder();

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_link, null);

			viewHolder.tvLinkName = (TextView) convertView
					.findViewById(R.id.tvLinkName);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (Holder) convertView.getTag();
		}
		if (position < linkList.size()) {
			viewHolder.tvLinkName.setText(linkList.get(position).getName());
		}
		return convertView;
	}

	final class Holder {
		TextView tvLinkName;
	}
}