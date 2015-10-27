package com.mentobile.homzz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ProjectDetailActivity extends ActionBarActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private Button btnSendMSG;
    private FragmentManager fragmentManager;
    private Fragment fragmentSendMSG;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Project Detail");
        fragmentManager = getSupportFragmentManager();
        fragmentSendMSG = new SendMsgFragment();

        viewPager = (ViewPager) findViewById(R.id.prj_dtl_viewpager);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(R.mipmap.bg);
        }
        ImageSliderPageAdapter pageAdapter = new ImageSliderPageAdapter(this, arrayList);
        viewPager.setAdapter(pageAdapter);

        btnSendMSG = (Button) findViewById(R.id.prj_dtl_btn_send_msg);
        btnSendMSG.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prj_dtl_btn_send_msg:
                Intent intent = new Intent(this, SendMsgActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                break;
        }
    }
}
