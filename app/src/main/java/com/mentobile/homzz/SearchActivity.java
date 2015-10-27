package com.mentobile.homzz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {

    private ArrayList<String> arrSearchAll = new ArrayList<>();
    private AutoCompleteTextView autoCompleteTextView;

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

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.search_actv_data);
        for (int i = 0; i < 100; i++) {
            arrSearchAll.add("Project Name " + i + "Builder " + i + "Sector " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrSearchAll);
        autoCompleteTextView.setAdapter(adapter);
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
}
