package com.mentobile.homzz;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mentobile.utility.RangeSeekBar;
import com.mentobile.utility.RangeSeekBar_SQFT;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements View.OnClickListener {

    final String TAG = "SearchFragment";

    EditText edSearchAny;
    EditText edSearchLocality;

    private ImageButton imgBtnApartment;
    private ImageButton imgBtnBuilderFloor;
    private ImageButton imgBtnFarmHouse;
    private ImageButton imgBtnHouse;
    private ImageButton imgBtnLand;

    private Button btnBD1;
    private Button btnBD2;
    private Button btnBD3;
    private Button btnBD4;
    private Button btnBD5;
    private Button btnBD5Plus;

    private Button btnSearch;

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


        imgBtnApartment = (ImageButton) view.findViewById(R.id.search_img_apartment);
        imgBtnApartment.setOnClickListener(this);

        imgBtnBuilderFloor = (ImageButton) view.findViewById(R.id.search_img_builder_floor);
        imgBtnBuilderFloor.setOnClickListener(this);

        imgBtnFarmHouse = (ImageButton) view.findViewById(R.id.search_img_farm_house);
        imgBtnFarmHouse.setOnClickListener(this);

        imgBtnHouse = (ImageButton) view.findViewById(R.id.search_img_house);
        imgBtnHouse.setOnClickListener(this);

        imgBtnLand = (ImageButton) view.findViewById(R.id.search_img_land);
        imgBtnLand.setOnClickListener(this);

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
        Log.d(TAG, ":::::ID " + v.getId());
        switch (v.getId()) {
            case R.id.search_btn_bd1:
            case R.id.search_btn_bd2:
            case R.id.search_btn_bd3:
            case R.id.search_btn_bd4:
            case R.id.search_btn_bd5:
            case R.id.search_btn_5plus:
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    v.setSelected(true);
                }
                break;

            // EditText

            case R.id.search_ed_anyType:
                Intent intentSearch = new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(intentSearch, 0);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String selectData = data.getStringExtra("data");
            edSearchAny.setText(selectData);
        }
    }
}