package com.mentobile.homzz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ProjectListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private TrendListAdapter trendListAdapter;
    private ListView listView;
    private ArrayList<TrendItem> arrProjectList = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search Result");

        listView = (ListView) findViewById(R.id.project_list_view);

        for (int i = 0; i < 50; i++) {
            TrendItem trendItem = new TrendItem(null, "Builder Name " + i, "Location " + i, "50" + i + "sq ft", "30" + i + " Lacs");
            arrProjectList.add(trendItem);
        }
        trendListAdapter = new TrendListAdapter(this, R.layout.layout_search_result, arrProjectList);
        listView.setAdapter(trendListAdapter);
        listView.setOnItemClickListener(this);
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

        Intent intent = new Intent(ProjectListActivity.this, ProjectDetailActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}
