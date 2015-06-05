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
    private List<Integer> itemList;
    private List<String> itemDesc;

    public ImageViewAdapter(Context context, List<Integer> item, List<String> itemDesc) {
        this.context = context;
        this.itemList = item;
        this.itemDesc = itemDesc;
    }

    public int getCount() {
        return itemList.size();
    }

    public Object getItem(int position) {
        return itemList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int i, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_item, null);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            imageView.setImageResource(itemList.get(i));

            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            textView.setText(itemDesc.get(i));
        } else {
            grid = convertView;
        }
        return grid;
    }
}
