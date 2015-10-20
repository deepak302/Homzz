package com.mentobile.homzz;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mentobile.utility.HorizontalListView;

public class NnDrawerActivity extends Activity implements AdapterView.OnItemClickListener {

    private HorizontalListView mHorizontal;

    private CustomData[] mCustomData = new CustomData[]{
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nn_drawer);
//        mHorizontal = (HorizontalListView) findViewById(R.id.horizontal);
//        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(this, mCustomData);
//        mHorizontal.setAdapter(customArrayAdapter);
//        mHorizontal.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Item Id " + id, Toast.LENGTH_SHORT).show();

    }
}