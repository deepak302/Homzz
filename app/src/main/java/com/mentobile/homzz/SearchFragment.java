package com.mentobile.homzz;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mentobile.utility.RangeSeekBar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements View.OnClickListener {

    final String TAG = "SearchFragment";
    private Spinner spinnerCity;
    private EditText edSearchLocation;
    private RangeSeekBar rangeSeekBar1;

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

    AutoCompleteTextView textView=null;
    private ArrayAdapter<String> adapter;

    //These values show in autocomplete
    String item[]={
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

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
        RangeSeekBar<Integer> rangeSeekBar = new RangeSeekBar<Integer>(getActivity());
        rangeSeekBar.setRangeValues(15, 90);
        rangeSeekBar.setSelectedMinValue(20);
        rangeSeekBar.setSelectedMaxValue(88);
        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Toast.makeText(getActivity(), "Min Value " + minValue + " Max " + maxValue, Toast.LENGTH_SHORT).show();
            }
        });

        // Add to layout
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.seekbar_testing);
        layout.addView(rangeSeekBar);

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

        // Sqft

        tvSQFTAny = (TextView) view.findViewById(R.id.search_tv_sqftAny);
        tvSQFTAny.setOnClickListener(this);

        tvSQFT500 = (TextView) view.findViewById(R.id.search_tv_sqft500);
        tvSQFT500.setOnClickListener(this);

        tvSQFT1K = (TextView) view.findViewById(R.id.search_tv_sqft1K);
        tvSQFT1K.setOnClickListener(this);

        tvSQFT2K = (TextView) view.findViewById(R.id.search_tv_sqft2K);
        tvSQFT2K.setOnClickListener(this);

        tvSQFT3K = (TextView) view.findViewById(R.id.search_tv_sqft3K);
        tvSQFT3K.setOnClickListener(this);

        tvSQFT4K = (TextView) view.findViewById(R.id.search_tv_sqft4K);
        tvSQFT4K.setOnClickListener(this);

        // Listed By

        tvListOwner = (TextView) view.findViewById(R.id.search_tv_list_owner);
        tvListOwner.setOnClickListener(this);

        tvListAgent = (TextView) view.findViewById(R.id.search_tv_list_agent);
        tvListAgent.setOnClickListener(this);

        tvListBuilder = (TextView) view.findViewById(R.id.search_tv_list_builder);
        tvListBuilder.setOnClickListener(this);

        textView = (AutoCompleteTextView)view.findViewById(R.id.Months);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, item);
        textView.setAdapter(adapter);

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

            // Sqft

            case R.id.search_tv_sqftAny:
                setTVButton(tvSQFTAny, id);
                break;
            case R.id.search_tv_sqft500:
                setTVButton(tvSQFT500, id);
                break;
            case R.id.search_tv_sqft1K:
                setTVButton(tvSQFT1K, id);
                break;
            case R.id.search_tv_sqft2K:
                setTVButton(tvSQFT2K, id);
                break;
            case R.id.search_tv_sqft3K:
                setTVButton(tvSQFT3K, id);
                break;
            case R.id.search_tv_sqft4K:
                setTVButton(tvSQFT4K, id);
                break;

            // Listed by

            case R.id.search_tv_list_owner:
                setTVButton(tvListOwner, id);
                break;

            case R.id.search_tv_list_agent:
                setTVButton(tvListAgent, id);
                break;

            case R.id.search_tv_list_builder:
                setTVButton(tvListBuilder, id);
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
