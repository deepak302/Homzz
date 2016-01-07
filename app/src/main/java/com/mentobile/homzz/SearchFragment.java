package com.mentobile.homzz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mentobile.utility.RangeSeekBar;
import com.mentobile.utility.RangeSeekBar_SQFT;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements View.OnClickListener {

    final String TAG = "SearchFragment";
    private Spinner spinnerCity;
    private EditText edSearchLocation;

    private Button btnSearch;

    private ArrayList<String> arrListFilter = new ArrayList<>();

    private Button btnBD1;
    private Button btnBD2;
    private Button btnBD3;
    private Button btnBD4;
    private Button btnBD5;
    private Button btnBD5Plus;

    private TextView tvPriceRange;

    private TextView tvListOwner;
    private TextView tvListAgent;
    private TextView tvListBuilder;

    EditText edSearchAny;
    EditText edSearchLocality;
    private ArrayAdapter<String> adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    String msg = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        tvPriceRange = (TextView) view.findViewById(R.id.search_tv_price_range);
        // Add seekBar for Price Range
        final RangeSeekBar<Integer> seekBarPrice = new RangeSeekBar<Integer>(getActivity());
        seekBarPrice.setRangeValues(1, 280);
        seekBarPrice.setSelectedMinValue(10);
        seekBarPrice.setSelectedMaxValue(150);
        seekBarPrice.setLabel_Min("Lacs");
        seekBarPrice.setLabel_Max("Cr");
        seekBarPrice.setNotifyWhileDragging(false);

        seekBarPrice.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, String minValue, String maxValue) {
                Log.d(TAG, ":::::::" + "Min Value " + minValue + " Max " + maxValue);
            }
        });

        // Add to layout
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.seekbar_price);
        layout.addView(seekBarPrice);

        // Add seekBar for Area ( Sq Ft)
        RangeSeekBar_SQFT<Integer> seekBarArea = new RangeSeekBar_SQFT<Integer>(getActivity());
        seekBarArea.setRangeValues(1, 100);
        seekBarArea.setSelectedMinValue(10);
        seekBarArea.setSelectedMaxValue(50);


        seekBarArea.setOnRangeSeekBar_SQFTChangeListener(new RangeSeekBar_SQFT.OnRangeSeekBar_SQFTChangeListener() {
            @Override
            public void onRangeSeekBar_SQFTValuesChanged(RangeSeekBar_SQFT<?> bar, String minValue, String maxValue) {
                Log.d(TAG, ":::::::" + "Min Value " + minValue + " Max " + maxValue);
            }
        });

        // Add to layout
        LinearLayout layout_Area = (LinearLayout) view.findViewById(R.id.seekbar_area);
        layout_Area.addView(seekBarArea);

        btnBD1 = (Button) view.findViewById(R.id.search_btn_bd1);
        btnBD1.setOnClickListener(this);

        btnBD2 = (Button) view.findViewById(R.id.search_btn_bd2);
        btnBD2.setOnClickListener(this);

        btnBD3 = (Button) view.findViewById(R.id.search_btn_bd3);
        btnBD3.setOnClickListener(this);

        btnBD4 = (Button) view.findViewById(R.id.search_btn_bd4);
        btnBD4.setOnClickListener(this);

        btnBD5 = (Button) view.findViewById(R.id.search_btn_bd5);
        btnBD5.setOnClickListener(this);

        btnBD5Plus = (Button) view.findViewById(R.id.search_btn_5plus);
        btnBD5Plus.setOnClickListener(this);

        edSearchAny = (EditText) view.findViewById(R.id.search_ed_anyType);
        edSearchAny.setOnClickListener(this);

        edSearchLocality = (EditText) view.findViewById(R.id.search_ed_locality);
        edSearchLocality.setOnClickListener(this);

        btnSearch = (Button) view.findViewById(R.id.search_btn_search);
        btnSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String id = "" + v.getId();
        if (v.isSelected()) {
            v.setSelected(false);
        } else {
            v.setSelected(true);
        }
        switch (v.getId()) {
//            case R.id.search_btn_bd1:
//                setTVButton(tvBDAny, id);
//                break;
//            case R.id.search_btn_bd2:
//                setTVButton(tvBD1, id);
//                break;
//            case R.id.search_btn_bd3:
//                setTVButton(tvBD2, id);
//                break;
//            case R.id.search_btn_bd4:
//                setTVButton(tvBD3, id);
//                break;
//            case R.id.search_btn_bd5:
//                setTVButton(tvBD4, id);
//                break;
//            case R.id.search_btn_5plus:
//                setTVButton(tvBD4Plus, id);
//                break;

            // EditText

            case R.id.search_ed_anyType:
                Intent intentSearch = new Intent(getActivity(), SearchActivity.class);
                startActivity(intentSearch);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.search_ed_locality:
                Intent intentLocality = new Intent(getActivity(), SearchLocalityActivity.class);
                startActivity(intentLocality);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

            // Button

            case R.id.search_btn_search:
                Intent intent = new Intent(getActivity(), ProjectListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;

        }
    }

    private void setTVButton(TextView textView, String id) {
        if (arrListFilter.contains(id)) {
            textView.setTextColor(getResources().getColor(R.color.bg_button_login));
            arrListFilter.remove(id);
        } else {
            arrListFilter.add(id);
            textView.setTextColor(getResources().getColor(R.color.bg_gradient_start));
        }
    }
}
