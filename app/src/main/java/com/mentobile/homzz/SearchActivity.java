package com.mentobile.homzz;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mentobile.utility.WebService;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;
import com.plumillonforge.android.chipview.ChipViewAdapter;
import com.plumillonforge.android.chipview.OnChipClickListener;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SearchActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "SearchActivity";


    private ArrayList<String> arrSearchAll = new ArrayList<>();
    private List<Chip> listSelected = new ArrayList<>();
    private ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ChipView mTextChipLayout;
    WebService webService;

    private EditText editText;
    private ListView listView;
    private Button btnDone;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search Data");

//        // Adapter
        ChipViewAdapter adapterLayout = new MainChipViewAdapter(this);

        // Custom layout and background colors
        mTextChipLayout = (ChipView) findViewById(R.id.text_chip_layout);
        mTextChipLayout.setAdapter(adapterLayout);
        mTextChipLayout.setChipLayoutRes(R.layout.chip_close);
        mTextChipLayout.setChipBackgroundColor(getResources().getColor(R.color.white));
        mTextChipLayout.setChipBackgroundColorSelected(getResources().getColor(R.color.green));
        mTextChipLayout.setChipList(listSelected);
        mTextChipLayout.setOnChipClickListener(new OnChipClickListener() {
            @Override
            public void onChipClick(Chip chip) {
                mTextChipLayout.remove(chip);
            }
        });


        listView = (ListView) findViewById(R.id.search_listview);
        listView.setOnItemClickListener(this);

        editText = (EditText) findViewById(R.id.search_ed_any);

        //autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.search_actv_data);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrSearchAll);
        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Log.d(TAG, "Char " + s + "  Start " + start + " count " + count + " after " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d(TAG, "Char1 " + s + "  Start " + start + " before " + before + " count " + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                nameValuePairs.add(new BasicNameValuePair("value", "" + s.toString()));
                new MyAsyncTask().execute();
            }
        });

        btnDone = (Button) findViewById(R.id.search_btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Chip> listChip = mTextChipLayout.getChipList();
                StringBuffer stringBuffer = new StringBuffer();
                for (Chip chip : listChip) {
                    stringBuffer.append(chip.getText());
                    stringBuffer.append(", ");
                }
                stringBuffer.replace(stringBuffer.length() - 2, stringBuffer.length(), "");
                Log.d(TAG, ":::::Data " + stringBuffer.toString());
                Intent intent = new Intent();
                intent.putExtra("data", stringBuffer.toString());
                setResult(Activity.RESULT_OK, intent);
                onBackPressed();
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
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectValue = arrSearchAll.get(position);
        listSelected.add(new Tag(selectValue));
        mTextChipLayout.setChipList(listSelected);
        editText.setText("");
    }

    private class MyAsyncTask extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (webService != null)
                webService = null;
            webService = new WebService();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonObject = webService.makeHttpRequest(Application.URL_SEARCH_ANY, nameValuePairs);
            Log.d(TAG, "::::JsonData " + jsonObject.toString());
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            arrSearchAll.clear();
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("search");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String data = jsonObject1.getString("data");
                    Log.d(TAG, "::::Data " + data);
                    arrSearchAll.add(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Collections.sort(arrSearchAll);
            adapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, arrSearchAll);
            listView.setAdapter(adapter);
        }
    }
}
