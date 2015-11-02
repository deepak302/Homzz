package com.mentobile.homzz;

import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mentobile.utility.UpdateNotification;

import java.util.ArrayList;

public class ProjectListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, ProjectListAdapter.customButtonListener {

    private ProjectListAdapter projectListAdapter;
    private ListView listView;
    private ArrayList<ProjectListItem> arrProjectList = new ArrayList<>();

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

        for (int i = 0; i < 10; i++) {
            ProjectListItem ProjectListItem = new ProjectListItem(1, null, "Builder Name " + i, "Location " + i, i + "BHK", "50" + i + "sq ft", "30" + i + " Lacs", false);
            arrProjectList.add(ProjectListItem);
        }
        projectListAdapter = new ProjectListAdapter(this, R.layout.layout_search_result, arrProjectList, this);
        listView.setAdapter(projectListAdapter);
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

    private int mNotificationCount = 5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_notifications);
        LayerDrawable layerDrawable = (LayerDrawable) item.getIcon();
        UpdateNotification.setBadgeCount(this, layerDrawable, mNotificationCount);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "List Item " + position, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(ProjectListActivity.this, ProjectDetailActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onButtonClickListner(int position) {
        Toast.makeText(getApplicationContext(), "Button Click " + position, Toast.LENGTH_SHORT).show();
        ProjectListItem projectListItem = arrProjectList.get(position);
        if (projectListItem.isLike()) {
            projectListItem.setIsLike(false);
        } else {
            projectListItem.setIsLike(true);
        }
        projectListAdapter.notifyDataSetChanged();
    }

}
