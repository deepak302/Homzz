package com.mentobile.homzz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 10/30/2015.
 */

public class ProjectListAdapter extends ArrayAdapter<ProjectListItem>  {

    private Context context;
    private int resourceID;
    private ArrayList<ProjectListItem> arrayList = new ArrayList<>();
    private URI uri;

    customButtonListener customListner;


    public interface customButtonListener {
        void onButtonClickListner(int position);
    }

    public ProjectListAdapter(Context context, int resource, ArrayList<ProjectListItem> arrayList,customButtonListener customButtonListener) {
        super(context, resource, arrayList);
        this.context = context;
        this.resourceID = resource;
        this.arrayList = arrayList;
        this.customListner = customButtonListener ;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public ProjectListItem getItem(int position) {
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

            holder.imgProject = (ImageView) view.findViewById(R.id.trend_imgv_banner);
            holder.imgBtnHeart = (ImageButton) view.findViewById(R.id.trend_imgbtn_like);
            holder.tvBuilderName = (TextView) view.findViewById(R.id.trend_tv_builder_name);
            holder.tvLocation = (TextView) view.findViewById(R.id.trend_tv_location);
            holder.tvArea = (TextView) view.findViewById(R.id.trend_tv_area);
            holder.tvPrice = (TextView) view.findViewById(R.id.trend_tv_price_sqft);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        // Populate the text
        final ProjectListItem projectListItem = arrayList.get(position);

        try {
            URL url = new URL(Application.PATH_IMAGE_FOLDER + projectListItem.getImageName());
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(context)
                .load(uri.toString())
                .error(R.mipmap.bg)
                .fit()
                .into(holder.imgProject);
        holder.tvBuilderName.setText(projectListItem.getBuilderName());
        holder.tvLocation.setText(projectListItem.getLocation());
        holder.tvArea.setText(projectListItem.getArea());
        holder.tvPrice.setText(projectListItem.getPrice());

        holder.imgBtnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null)
                    customListner.onButtonClickListner(position);
            }
        });

        if (projectListItem.isLike()) {
            holder.imgBtnHeart.setBackgroundResource(R.mipmap.heart_press);
        } else {
            holder.imgBtnHeart.setBackgroundResource(R.mipmap.heart);
        }
        return view;
    }

    /**
     * View holder for the views we need access to
     */
    static class Holder {
        private ImageView imgProject;
        private ImageButton imgBtnHeart;
        private TextView tvBuilderName;
        private TextView tvLocation;
        private TextView tvArea;
        private TextView tvPrice;
    }
}
