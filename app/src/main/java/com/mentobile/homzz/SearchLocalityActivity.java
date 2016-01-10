package com.mentobile.homzz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mentobile.utility.GetDataUsingWService;
import com.mentobile.utility.WebService;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class SearchLocalityActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, GetDataUsingWService.GetWebServiceData {


    private static final String TAG = "SearchLocalityActivity";
    private ArrayList<String> arrSearchLocality = new ArrayList<>();
    private ArrayList<String> tempSearchLocality = new ArrayList<>();
    private ArrayList<String> arrSelectedLocality = new ArrayList<>();

    private EditText edLocation;
    private ListView listViewLocality;
    private ListView listViewSelLocality;
    private RelativeLayout relativeLayout;

    private ArrayAdapter<String> adapterLocality;
    private ArrayAdapter<String> adapterSelLocality;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_locality);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search Data");

        relativeLayout = (RelativeLayout) findViewById(R.id.search_rl_selected_locality);
        relativeLayout.setVisibility(View.GONE);

        edLocation = (EditText) findViewById(R.id.search_ed_locality);

        listViewLocality = (ListView) findViewById(R.id.search_lv_data);
        listViewLocality.setOnItemClickListener(this);
        adapterLocality = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, arrSearchLocality);
        listViewLocality.setAdapter(adapterLocality);

        listViewSelLocality = (ListView) findViewById(R.id.search_lv_select_locality);
        adapterSelLocality = new ArrayAdapter<String>(this, R.layout.layout_selected_locality, R.id.selected_locality_tv_name, arrSelectedLocality);
        listViewSelLocality.setAdapter(adapterSelLocality);
        listViewSelLocality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String location = arrSelectedLocality.get(position);

                tempSearchLocality.add(location);
                arrSearchLocality.add(location);
                arrSelectedLocality.remove(location);

                adapterLocality.notifyDataSetChanged();
                adapterSelLocality.notifyDataSetChanged();

                if (arrSelectedLocality.size() > 0) {
                    relativeLayout.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout.setVisibility(View.GONE);
                }
            }
        });

        edLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.d(TAG, "::::BeforeTextChanged " + s + " Start " + start + "   count " + count + "  after  " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Log.d(TAG, "::::Textchanged  " + s + " Start " + start + "  before " + before + "   count " + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().toLowerCase(Locale.getDefault());
                arrSearchLocality.clear();
                if (str.length() == 0) {
                    arrSearchLocality.addAll(tempSearchLocality);
                } else {
                    for (int i = 0; i < tempSearchLocality.size(); i++) {
                        String product = tempSearchLocality.get(i);
                        String pro = product.toLowerCase(Locale.getDefault());
                        if (pro.contains(str)) {
                            arrSearchLocality.add(product);
                        }
                    }
                }
                edLocation.setSelection(str.length());
                adapterLocality.notifyDataSetChanged();
            }
        });

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        GetDataUsingWService getDataUsingWService = new GetDataUsingWService(this, Application.URL_SEARCH_LOCATION, 0, nameValuePairs, true, this);
        Application.startAsyncTaskInParallel(getDataUsingWService);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String location = arrSearchLocality.get(position);

        tempSearchLocality.remove(location);
        arrSelectedLocality.add(location);

        adapterLocality.notifyDataSetChanged();
        adapterSelLocality.notifyDataSetChanged();

        edLocation.setText("");
        if (arrSelectedLocality.size() > 0) {
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            relativeLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void getWebServiceData_JSON(JSONObject jsonObject, int serviceCounter) {
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("locality");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String location = jsonObject1.getString("location");
                arrSearchLocality.add(location);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tempSearchLocality.addAll(arrSearchLocality);
        Collections.sort(arrSearchLocality);
        adapterLocality.notifyDataSetChanged();
    }
}
