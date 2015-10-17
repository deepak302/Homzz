package com.mentobile.homzz;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mentobile.utility.RangeSeekBar;


public class SearchFragment extends Fragment {

    private Spinner spinnerCity;
    private EditText edSearchLocation;
    private RangeSeekBar rangeSeekBar1;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
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
        return view;
    }
}
