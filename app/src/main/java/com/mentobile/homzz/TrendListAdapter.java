package com.mentobile.homzz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 10/19/2015.
 */
public class TrendListAdapter extends ArrayAdapter<TrendItem> {

    private Context context;
    private int resourceID;
    private ArrayList<TrendItem> arrayList = new ArrayList<>();

    public TrendListAdapter(Context context, int resource, ArrayList<TrendItem> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resourceID = resource;
        this.arrayList = arrayList;
        Log.d("ListAdapter ", "::::::Adapter " + arrayList.size());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public TrendItem getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    Holder holder = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // Inflate the view since it does not exist
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(resourceID, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
            holder = new Holder();

            holder.imageView = (ImageView) view.findViewById(R.id.trend_imgv_banner);
            holder.tvBuilderName = (TextView) view.findViewById(R.id.trend_tv_builder_name);
            holder.tvLocation = (TextView) view.findViewById(R.id.trend_tv_location);
            holder.tvArea = (TextView) view.findViewById(R.id.trend_tv_sqft);
            holder.tvPrice = (TextView) view.findViewById(R.id.trend_tv_price);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        // Populate the text
        TrendItem TrendItem = arrayList.get(position);
        holder.imageView.setBackgroundResource(R.mipmap.bg);
        holder.tvBuilderName.setText(TrendItem.getBuilderName());
        holder.tvLocation.setText(TrendItem.getLocation());
        holder.tvArea.setText(TrendItem.getArea());
        holder.tvPrice.setText(TrendItem.getPrice());

        return view;
    }

    /**
     * View holder for the views we need access to
     */
    static class Holder {
        private ImageView imageView;
        private TextView tvBuilderName;
        private TextView tvLocation;
        private TextView tvArea;
        private TextView tvPrice;
    }
}
