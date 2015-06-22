package com.swproject.fi.swproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alex on 5.6.2015.
 */
public class ImageViewAdapter extends BaseAdapter{
    private Context context;
    private int resourceId;
    private List<Device> deviceList;
    private ItemHolder holder;

    public ImageViewAdapter(Context context, int resourceId, List<Device> deviceList) {
        this.context = context;
        this.resourceId = resourceId;
        this.deviceList = deviceList;
    }

    public int getCount() {
        return deviceList.size();
    }

    public Object getItem(int position) {
        return deviceList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ItemHolder();
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.grid_item, parent, false);

            holder.icon = (ImageView)convertView.findViewById(R.id.grid_image);
            holder.name = (TextView) convertView.findViewById(R.id.gridName);
            holder.ipAddress = (TextView) convertView.findViewById(R.id.gridIP);
            holder.network = (TextView) convertView.findViewById(R.id.gridNet);
            //holder.description.setText(itemDesc.get(i));
            convertView.setTag(holder);
        } else {
            holder = (ItemHolder) convertView.getTag();
        }


        Device device = (Device) getItem(i);
        holder.icon.setImageResource(device.getIcon());
        holder.name.setText(device.getName());
        holder.ipAddress.setText(device.getIpAddress());
        holder.network.setText(device.getMAC());

        return convertView;
    }

    public class ItemHolder{
        ImageView icon;
        TextView name;
        TextView ipAddress;
        TextView network;
    }
}
