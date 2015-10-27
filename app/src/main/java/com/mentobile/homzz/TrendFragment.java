package com.mentobile.homzz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class TrendFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private TrendListAdapter trendListAdapter;
    private ArrayList<TrendItem> arrTrendItems = new ArrayList<>();

    public TrendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trend, container, false);
        listView = (ListView) view.findViewById(R.id.trend_listview);

        for (int i = 0; i < 10; i++) {
            TrendItem trendItem = new TrendItem(null, "Builder Name " + i, "Location " + i, "50" + i + "sq ft", "30" + i + " Lacs");
            arrTrendItems.add(trendItem);
        }
        trendListAdapter = new TrendListAdapter(getActivity(), R.layout.layout_trend_items, arrTrendItems);
        listView.setAdapter(trendListAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
