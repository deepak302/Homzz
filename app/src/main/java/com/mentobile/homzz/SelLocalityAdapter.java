package com.mentobile.homzz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 11/2/2015.
 */
public class SelLocalityAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resourceID;
    private ArrayList<String> arrayList = new ArrayList<>();

    public SelLocalityAdapter(Context context, int resource, ArrayList<String> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resourceID = resource;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    Holder holder = null;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            // Inflate the view since it does not exist
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(resourceID, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
            holder = new Holder();

            holder.imgBtnDeleteLocality = (ImageButton) view.findViewById(R.id.selected_locality_imgbtn_delete);
            holder.tvLocalityName = (TextView) view.findViewById(R.id.selected_locality_tv_name);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.tvLocalityName.setText(arrayList.get(position));

        return view;
    }

    /**
     * View holder for the views we need access to
     */
    static class Holder {
        private ImageButton imgBtnDeleteLocality;
        private TextView tvLocalityName;
    }
}
