package com.mentobile.homzz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mentobile.utility.Utility;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TrendFragment extends Fragment implements AdapterView.OnItemClickListener, ProjectListAdapter.customButtonListener {

    private static final String TAG = "TrendFragment";
    private CProgressDialog cProgressDialog;
    private ArrayList<NameValuePair> listValue;
    private WebService webService;
    private ListView listView;
    private ProjectListAdapter projectListAdapter;
    private ArrayList<ProjectListItem> arrProjectListItems = new ArrayList<>();
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
        if (Utility.isNetworkAvailable(getActivity())) {
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
//            ProjectListItem trendItem = new ProjectListItem(null, "Builder Name " + i, "Location " + i, "50" + i + "sq ft", "30" + i + " Lacs");
//            arrProjectListItems.add(trendItem);
//        }

        projectListAdapter = new ProjectListAdapter(getActivity(), R.layout.layout_search_result, arrProjectListItems, null);
        listView.setAdapter(projectListAdapter);
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

    @Override
    public void onButtonClickListner(int position) {

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
            json = webService.makeHttpRequest(Application.URL_TRENDING, listValue);
            try {
                responseCode = json.getString("success");
                if (!responseCode.equals("0")) {
                    JSONArray jsonArray = json.getJSONArray("trending");
                    for (int i = 1; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String trend_id = jsonObject.getString("trend_id");
                        String prj_id = jsonObject.getString("prj_id");
                        String prj_name = jsonObject.getString("prj_name");
                        String dev_name = jsonObject.getString("dev_name");
                        String area = jsonObject.getString("area");
                        String location = jsonObject.getString("location");
                        String imageName = jsonObject.getString("prj_images");
                        int price = jsonObject.getInt("price");
                        ProjectListItem projectListItem = new ProjectListItem(prj_id, imageName, dev_name, location, "", area, "" + price, false);
                        arrProjectListItems.add(projectListItem);
                    }
                    return responseCode;
                } else {
                    responseMSG = json.getString("message");
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
            projectListAdapter.notifyDataSetChanged();
            if (s.length() > 3)
                Toast.makeText(getActivity(), "" + responseMSG, Toast.LENGTH_SHORT).show();
        }
    }
}
