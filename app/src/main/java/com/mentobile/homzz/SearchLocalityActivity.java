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

import java.util.ArrayList;
import java.util.Locale;


public class SearchLocalityActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {


    private static final String TAG = "SearchLocalityActivity";
    private ArrayList<String> arrSearchLocality = new ArrayList<>();
    private ArrayList<String> arrSelectedLocality = new ArrayList<>();

    private EditText edLocation;
    private ListView listViewLocality;
    private ListView listViewSelLocality;
    private RelativeLayout relativeLayout;

    private ArrayAdapter<String> adapterLocality;
    private ArrayAdapter<String> adapterSelLocality;

    String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
            "iPhone 4S", "Samsung Galaxy Note 800",
            "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro", "Sector 1", "Sector 2", "Sector 3", "Sector 4", "Sector 5",};

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

        for (String product : products) {
            arrSearchLocality.add(product);
        }

        edLocation = (EditText) findViewById(R.id.search_ed_locality);

        listViewLocality = (ListView) findViewById(R.id.search_lv_data);
        listViewLocality.setOnItemClickListener(this);
        adapterLocality = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, arrSearchLocality);
        listViewLocality.setAdapter(adapterLocality);

        listViewSelLocality = (ListView) findViewById(R.id.search_lv_select_locality);
        adapterSelLocality = new ArrayAdapter<String>(this, R.layout.layout_selected_locality, R.id.selected_locality_tv_name, arrSelectedLocality);
//        adapterSelLocality = new SelLocalityAdapter(getApplicationContext(), R.layout.layout_selected_locality, arrSelectedLocality, this);
        listViewSelLocality.setAdapter(adapterSelLocality);
        listViewSelLocality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String location = arrSelectedLocality.get(position);

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
                Log.d(TAG, "::::afterTextChanged " + s);
                // SearchLocalityActivity.this.adapterLocality.getFilter().filter(s);

                String str = s.toString().toLowerCase(Locale.getDefault());
                arrSearchLocality.clear();
                if (str.length() == 0) {
                    for (String product : products) {
                        if (!arrSelectedLocality.contains(product)) {
                            arrSearchLocality.add(product);
                        }
                    }
                } else {
                    for (String product : products) {
                        String pro = product.toLowerCase(Locale.getDefault());
                        if (pro.contains(str)) {
                            arrSearchLocality.add(product);
//                            Log.d(TAG, ":::::Array " + product);
                        }
                    }
                }
                edLocation.setSelection(str.length());
                adapterLocality.notifyDataSetChanged();
            }
        });
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
            case R.id.action_done:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String location = arrSearchLocality.get(position);

        arrSearchLocality.remove(location);
        arrSelectedLocality.add(location);

        adapterLocality.notifyDataSetChanged();
        adapterSelLocality.notifyDataSetChanged();

        edLocation.setText("");
        if (arrSelectedLocality.size() > 0) {
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            relativeLayout.setVisibility(View.GONE);
        }
        Log.d(TAG, ":::::Position " + arrSelectedLocality.size());
    }

}
