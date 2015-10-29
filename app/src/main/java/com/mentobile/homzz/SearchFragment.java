package com.mentobile.homzz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;

public class SearchFragment extends Fragment implements View.OnClickListener {

    final String TAG = "SearchFragment";
    private Spinner spinnerCity;
    private EditText edSearchLocation;

    private Button btnSearch;

    private ArrayList<String> arrListFilter = new ArrayList<>();
    private TextView tvBDAny;
    private TextView tvBD1;
    private TextView tvBD2;
    private TextView tvBD3;
    private TextView tvBD4;
    private TextView tvBD4Plus;

    private TextView tvSQFTAny;
    private TextView tvSQFT500;
    private TextView tvSQFT1K;
    private TextView tvSQFT2K;
    private TextView tvSQFT3K;
    private TextView tvSQFT4K;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Add seekBar for Price Range
        RangeSeekBar<Integer> seekBarPrice = new RangeSeekBar<Integer>(getActivity());
        seekBarPrice.setRangeValues(5, 100);
        seekBarPrice.setSelectedMinValue(20);
        seekBarPrice.setSelectedMaxValue(60);
        seekBarPrice.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Toast.makeText(getActivity(), "Min Value " + minValue + " Max " + maxValue, Toast.LENGTH_SHORT).show();
            }
        });

        // Add to layout
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.seekbar_price);
        layout.addView(seekBarPrice);


        // Add seekBar for Area ( Sq Ft)
        RangeSeekBar<Integer> seekBarArea = new RangeSeekBar<Integer>(getActivity());
        seekBarArea.setRangeValues(10, 10000);
        seekBarArea.setSelectedMinValue(500);
        seekBarArea.setSelectedMaxValue(6000);
        seekBarArea.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Toast.makeText(getActivity(), "Min Value " + minValue + " Max " + maxValue, Toast.LENGTH_SHORT).show();
            }
        });

        // Add to layout
        LinearLayout layout_Area = (LinearLayout) view.findViewById(R.id.seekbar_area);
        layout_Area.addView(seekBarArea);

        tvBDAny = (TextView) view.findViewById(R.id.search_tv_bd_any);
        tvBDAny.setOnClickListener(this);

        tvBD1 = (TextView) view.findViewById(R.id.search_tv_bd1);
        tvBD1.setOnClickListener(this);

        tvBD2 = (TextView) view.findViewById(R.id.search_tv_bd2);
        tvBD2.setOnClickListener(this);

        tvBD3 = (TextView) view.findViewById(R.id.search_tv_bd3);
        tvBD3.setOnClickListener(this);

        tvBD4 = (TextView) view.findViewById(R.id.search_tv_bd4);
        tvBD4.setOnClickListener(this);

        tvBD4Plus = (TextView) view.findViewById(R.id.search_tv_bd4plus);
        tvBD4Plus.setOnClickListener(this);


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
        switch (v.getId()) {
            case R.id.search_tv_bd_any:
                setTVButton(tvBDAny, id);
                break;
            case R.id.search_tv_bd1:
                setTVButton(tvBD1, id);
                break;
            case R.id.search_tv_bd2:
                setTVButton(tvBD2, id);
                break;
            case R.id.search_tv_bd3:
                setTVButton(tvBD3, id);
                break;
            case R.id.search_tv_bd4:
                setTVButton(tvBD4, id);
                break;
            case R.id.search_tv_bd4plus:
                setTVButton(tvBD4Plus, id);
                break;

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
