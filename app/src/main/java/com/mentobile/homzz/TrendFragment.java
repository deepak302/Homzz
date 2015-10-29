package com.mentobile.homzz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TrendFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "TrendFragment";
    private CProgressDialog cProgressDialog;
    private ArrayList<NameValuePair> listValue;
    private WebService webService;
    private ListView listView;
    private TrendListAdapter trendListAdapter;
    private ArrayList<TrendItem> arrTrendItems = new ArrayList<>();
    private JSONObject json;

    private String responseCode = "";
    private String responseMSG = "";

    public TrendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listValue = new ArrayList<>();
        webService = new WebService();
        cProgressDialog = new CProgressDialog(getActivity());
        if(Application.isNetworkAvailable(getActivity())) {

            MyAsynchTask myAsynchTask = new MyAsynchTask();
            myAsynchTask.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trend, container, false);
        listView = (ListView) view.findViewById(R.id.trend_listview);

//        for (int i = 0; i < 10; i++) {
//            TrendItem trendItem = new TrendItem(null, "Builder Name " + i, "Location " + i, "50" + i + "sq ft", "30" + i + " Lacs");
//            arrTrendItems.add(trendItem);
//        }

        trendListAdapter = new TrendListAdapter(getActivity(), R.layout.layout_trend_items, arrTrendItems);
        listView.setAdapter(trendListAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.aplha);
//        view.startAnimation(animation);
        Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    private class MyAsynchTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            cProgressDialog.setMessage("Please wait...");
            cProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            json = webService.makeHttpRequest(Application.WS_TRENDING, listValue);
            try {
                responseCode = json.getString("responsecode");
                if (responseCode.equals("100")) {
                    JSONArray jsonArray = json.getJSONArray("description");
                    Log.d(TAG, "::::::JSon Array " + jsonArray.toString());
                    int length = jsonArray.length();
                    for (int i = 1; i < length; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String developerName = jsonObject.getString("developer");
                        String area = jsonObject.getString("plot_area");
                        String location = jsonObject.getString("location");
                        int price = jsonObject.getInt("price");

                        TrendItem trendItem = new TrendItem(null, developerName, location, area, "" + price);
                        arrTrendItems.add(trendItem);
                    }
                    return responseCode;
                } else {
                    responseMSG = json.getString("description");
                }
                return responseMSG;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "Invalid";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cProgressDialog.hide();
            trendListAdapter.notifyDataSetChanged();
            if (s.length() > 1)
                Toast.makeText(getActivity(), "" + responseMSG, Toast.LENGTH_SHORT).show();
        }
    }
}
